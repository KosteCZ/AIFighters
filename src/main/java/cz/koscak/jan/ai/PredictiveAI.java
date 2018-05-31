package cz.koscak.jan.ai;

import java.util.ArrayList;
import java.util.List;

import cz.koscak.jan.ai.model.AIEnemy;
import cz.koscak.jan.ai.model.AIFighter;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.model.Direction;

public class PredictiveAI implements Inteligence {
	
	List<PredictiveAI.Enemy> listOfEnemies = new ArrayList<PredictiveAI.Enemy>();
	
	public class Position {
		
		private double x;
		private double y;
		
		public Position(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		public double getX() {
			return x;
		}
		
		public double getY() {
			return y;
		}
		
	}
	
	public class Enemy {
		
		private static final int BULLET_STARTING_POSITION_COMPENSATION = 21;
		private boolean alive;
		private double x_old;
		private double x_new;
		private double y_old;
		private double y_new;
		
		public Enemy(boolean alive, double x, double y) {
			this.alive = alive;
			x_old = x;
			x_new = x;
			y_old = y;
			y_new = y;
		}
		
		public boolean isAlive() {
			return alive;
		}
		
		public void setAlive(boolean alive) {
			this.alive = alive;
		}
		
		public Direction getDirection() {
			return new Direction(x_new - x_old, y_new - y_old);
		}
		
		public void newPosition(double x, double y) {
			x_old = x_new;
			y_old = y_new;
			x_new = x;
			y_new = y;
		}
		
		public double getDistanceTo(double x, double y) {
			return Math.sqrt(((x_new - x) * (x_new - x)) + ((y_new - y) * (y_new - y)));
		}
		
		public double getBulletTimeTo(double x, double y) {
			return (getDistanceTo(x, y) / AIScope.BULLET_SPEED) - BULLET_STARTING_POSITION_COMPENSATION;
		}
		
		public Position getFuturePositionForPosition(double x, double y) {
			return new Position((x_new + (getBulletTimeTo(x, y) * getDirection().getX())), (y_new + (getBulletTimeTo(x, y) * getDirection().getY())));
		}
		public Direction getPredictedPosition(double x, double y) {
			Position futureEnemyPosition = getFuturePositionForPosition(x, y);
			
			/*System.out.println("x, y:               " + x + ", " + y);
			System.out.println("new_x, new_y:       " + x_new + ", " + y_new);
			System.out.println("future_x, future_y: " + futureEnemyPosition.getX() + ", " + futureEnemyPosition.getY());
			System.out.println("result_x, result_y: " + (futureEnemyPosition.getX() - x) + ", " + (futureEnemyPosition.getY() - y));*/
			
			return new Direction(futureEnemyPosition.getX() - x, futureEnemyPosition.getY() - y);
		}
		
	}

	public void doAction(AIScope scope, int round) {
		
		if (round == 1) {
			scope.getMyTeam().get(0).moveInfinitelyInDirection(new Direction(-1, 1));
			scope.getMyTeam().get(1).moveInfinitelyInDirection(new Direction(-1, 1));
			scope.getMyTeam().get(2).moveInfinitelyInDirection(new Direction(-1, 1));
			scope.getMyTeam().get(3).moveInfinitelyInDirection(new Direction(1, -1));
			scope.getMyTeam().get(4).moveInfinitelyInDirection(new Direction(1, -1));
			
			for (int i = 0; i < scope.getEnemyTeam().size(); i++) {
				listOfEnemies.add(new Enemy(scope.getEnemyTeam().get(i).isAlive(), scope.getEnemyTeam().get(i).getX(), scope.getEnemyTeam().get(i).getY()));
			}
		}
		
		if (round > 1) {
			for (int i = 0; i < scope.getEnemyTeam().size(); i++) {
				Enemy enemy = listOfEnemies.get(i);
				AIEnemy aiEnemy = scope.getEnemyTeam().get(i);
				enemy.newPosition(aiEnemy.getX(), aiEnemy.getY());
			}
		}
			
		
		if (round == 2 || round % 50 == 0) {
			
			List<Direction> listOfTargets = new ArrayList<Direction>();
			
			if (scope.getAliveEnemiesCount() > 0) {
				int enemyCount = scope.getEnemyTeam().size();
				int currentEnemyPointer = 0;
				for (AIFighter fighter : scope.getMyTeam()) {
					if (fighter.isAlive()) {
						boolean targetFound = false;
						while (targetFound == false) {
							AIEnemy targetAIEnemy = scope.getEnemyTeam().get(currentEnemyPointer);
							Enemy targetEnemy = listOfEnemies.get(currentEnemyPointer);
							if (targetAIEnemy.isAlive()) {
								listOfTargets.add(targetEnemy.getPredictedPosition(fighter.getX(), fighter.getY()));								
								targetFound = true;
							}
							currentEnemyPointer = (currentEnemyPointer + 1) % enemyCount;
						}
					} else {
						listOfTargets.add(null);
					}
					//System.out.println("----------");
				}
				for (int index = 0; index < scope.getMyTeam().size(); index++) {
					scope.getMyTeam().get(index).shootTo(listOfTargets.get(index));
				}
			}			
			
			for (AIFighter fighter : scope.getMyTeam()) {
				//fighter.shootTo(new Direction((Math.random() * 2) - 1, (Math.random() * 2) - 1));
				fighter.moveInfinitelyInDirection(new Direction(((int) (Math.random() * 3)) - 1, ((int) (Math.random() * 3)) - 1));			
			}
			
		}
		
		if (round % 25 == 0) {
			scope.getMyTeam().get(0).doNotMove();
		}
		
	}
	
}

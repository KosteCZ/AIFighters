package cz.koscak.jan.ai;

import java.util.ArrayList;
import java.util.List;

import cz.koscak.jan.ai.model.AIEnemy;
import cz.koscak.jan.ai.model.AIFighter;
import cz.koscak.jan.ai.model.AIScope;
import cz.koscak.jan.model.Direction;

public class NormalAI implements Inteligence {
	
	public void doAction(AIScope scope, int round) {
		
		if (round == 1) {
			AIFighter fighter1 = scope.getMyTeam().get(0);
			AIFighter fighter2 = scope.getMyTeam().get(1);
			AIFighter fighter3 = scope.getMyTeam().get(2);
			AIFighter fighter4 = scope.getMyTeam().get(3);
			AIFighter fighter5 = scope.getMyTeam().get(4);

			fighter1.moveInfinitelyInDirection(new Direction(-1, 1));			
			fighter2.moveInfinitelyInDirection(new Direction(-1, 1));			
			fighter3.moveInfinitelyInDirection(new Direction(-1, 1));			
			fighter4.moveInfinitelyInDirection(new Direction(1, -1));			
			fighter5.moveInfinitelyInDirection(new Direction(1, -1));			
		}
		
		if (round == 1 || round % 50 == 0) {
			
			List<Direction> listOfTargets = new ArrayList<Direction>();
			
			if (scope.getAliveEnemiesCount() > 0) {
				int enemyCount = scope.getEnemyTeam().size();
				int currentEnemyPointer = 0;
				for (AIFighter fighter : scope.getMyTeam()) {
					if (fighter.isAlive()) {
						boolean targetFound = false;
						while (targetFound == false) {
							AIEnemy targetEnemy = scope.getEnemyTeam().get(currentEnemyPointer);
							if (targetEnemy.isAlive()) {
								listOfTargets.add(new Direction(targetEnemy.getX() - fighter.getX(), targetEnemy.getY() - fighter.getY()));
								targetFound = true;
							}
							currentEnemyPointer = (currentEnemyPointer + 1) % enemyCount;
						}
					} else {
						listOfTargets.add(null);
					}
				}
				for (int index = 0; index < scope.getMyTeam().size(); index++) {
					scope.getMyTeam().get(index).shootTo(listOfTargets.get(index));
				}
			}			
			
			for (AIFighter fighter : scope.getMyTeam()) {
				//				fighter.shootTo(new Direction((Math.random() * 2) - 1, (Math.random() * 2) - 1));
				fighter.moveInfinitelyInDirection(new Direction(((int) (Math.random() * 3)) - 1, ((int) (Math.random() * 3)) - 1));			
			}
			
		}
		
		if (round % 25 == 0) {
			scope.getMyTeam().get(0).doNotMove();
		}
		
	}
	
}

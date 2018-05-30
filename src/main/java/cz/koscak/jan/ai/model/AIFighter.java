package cz.koscak.jan.ai.model;

import cz.koscak.jan.model.Direction;
import cz.koscak.jan.model.Fighter;

public class AIFighter {

	private int id;
	private int teamId;
	private Fighter fighter;
	private ShootingAction shootingAction;
	private MovingAction movingAction;

	public AIFighter(Fighter fighter, int id, int teamId) {
		this.fighter = fighter;
		this.id = id;
		this.teamId = teamId;
	}
	
	public double getX() {
		return fighter.getX();
	}
	
	public double getY() {
		return fighter.getY();
	}
	
	public boolean isAlive() {
		return fighter.isAlive();
	}
	
	public void moveOnce(Direction direction) {
		this.movingAction = new MovingAction(direction, true);
	}
	
	public void moveTo(Direction direction) {
		this.movingAction = new MovingAction(direction, false);
	}
	
	public void moveInfinitelyInDirection(Direction direction) {
		this.movingAction = new MovingAction(direction, false);
	}
	
	public void doNotMove() {
		this.movingAction = null;
	}
	
	public boolean isMoving() {
		return (movingAction != null);
	}
	
	public void shootTo(Direction direction) {
		this.shootingAction = new ShootingAction(direction);
	}
	
	public void doNotShoot() {
		this.shootingAction = null;
	}
	
	public boolean isShooting() {
		return (shootingAction != null);
	}
	
	public ShootingAction getShootingAction() {
		return shootingAction;
	}

	public MovingAction getMovingAction() {
		return movingAction;
	}

	public int getId() {
		return id;
	}

	public int getTeamId() {
		return teamId;
	}

	public class ShootingAction {
		
		private Direction direction;

		public ShootingAction(Direction direction) {
			this.direction = direction;
		}

		public Direction getDirection() {
			return direction;
		}
		
	}
	
	public class MovingAction {
		
		private Direction direction;
		private boolean doOnlyOnce;

		public MovingAction(Direction direction, boolean doOnlyOnce) {
			this.direction = direction;
			this.doOnlyOnce = doOnlyOnce;
		}

		public Direction getDirection() {
			return direction;
		}
		
		public boolean isDoOnlyOnce() {
			return doOnlyOnce;
		}
		
	}
	
}

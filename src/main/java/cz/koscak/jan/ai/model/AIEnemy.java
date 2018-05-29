package cz.koscak.jan.ai.model;

import cz.koscak.jan.model.Fighter;

public class AIEnemy {
	
	private int id;
	private Fighter fighter;

	public AIEnemy(Fighter fighter, int id) {
		this.fighter = fighter;
		this.id = id;
	}
	
	public double getId() {
		return id;
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

}

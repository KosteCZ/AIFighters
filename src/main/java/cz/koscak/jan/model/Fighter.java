package cz.koscak.jan.model;

import java.awt.Graphics;

import cz.koscak.jan.core.Team;

public class Fighter extends AbstactDrawableModel {

	private Team team;
	private boolean isAlive = true;
	
	public Fighter(Team team, double x, double y) {
		super(x, y);
		this.team = team;
	}
	
	public Bullet shoot(Direction direction) {
		// PoC TEST, change it
		return new Bullet(getX() + 10, getY() + 10, direction, team.getColor());
	}

	@Override
	public void print(Graphics g) {
		g.setColor(team.getColor());
		if (isAlive) {
			g.fillOval((int) Math.round(getX()), (int) Math.round(getY()), 8, 8);
		} else {
			g.drawOval((int) Math.round(getX()), (int) Math.round(getY()), 8, 8);
		}
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void die() {
		this.isAlive = false;
	}

}

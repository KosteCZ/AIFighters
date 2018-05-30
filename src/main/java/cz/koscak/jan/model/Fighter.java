package cz.koscak.jan.model;

import java.awt.Graphics;

import cz.koscak.jan.core.Team;

public class Fighter extends AbstactDrawableModel {

	private Team team;
	private boolean isAlive = true;
	
	public static final int RADIUS = 4;
	public static final int DIAMETER = RADIUS * 2;
	
	public Fighter(Team team, double x, double y) {
		super(x, y);
		this.team = team;
	}
	
	public Bullet shoot(Direction direction) {
		// PoC TEST, change it
		return new Bullet(getX() + RADIUS + (direction.getX() * 7), getY() + RADIUS + (direction.getY() * 7), direction, team.getColor());
	}

	public void move(Direction direction) {
		
		setX(getX() + direction.getX());
		setY(getY() + direction.getY());
		//System.out.println("Bullet moved: " + getX() + ", " + getY());
		
		if ((getX() - Arena.ARENA_X) < 0) {
			setX(Arena.ARENA_X);
		}
		if ((getY() - Arena.ARENA_Y) < 0) {
			setY(Arena.ARENA_Y);
		}
		if ((getX() - (Arena.ARENA_X + Arena.ARENA_WIDTH) + (DIAMETER)) > 0) {
			setX(Arena.ARENA_X + Arena.ARENA_WIDTH - DIAMETER);
		}
		if ((getY() - (Arena.ARENA_Y + Arena.ARENA_HEIGHT) + (DIAMETER)) > 0) {
			setY(Arena.ARENA_Y + Arena.ARENA_HEIGHT - DIAMETER);
		}
		
	}
	@Override
	public void print(Graphics g) {
		g.setColor(team.getColor());
		if (isAlive) {
			g.fillOval((int) Math.round(getX()), (int) Math.round(getY()), DIAMETER, DIAMETER);
		} else {
			g.drawOval((int) Math.round(getX()), (int) Math.round(getY()), DIAMETER, DIAMETER);
		}
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void die() {
		this.isAlive = false;
	}

}

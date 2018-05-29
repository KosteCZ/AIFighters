package cz.koscak.jan.model;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends AbstactDrawableModel {

	private Color color;
	private Direction direction;
	
	public Bullet(double x, double y, Direction direction, Color color) {
		
		super(x, y);
		this.color = color;
		this.direction = direction;
		
	}

	public void move() {
		
		setX(getX() + direction.getX());
		setY(getY() + direction.getY());
		//System.out.println("Bullet moved: " + getX() + ", " + getY());
		
	}
	
	@Override
	public void print(Graphics g) {
		
		g.setColor(color);
		g.fillOval((int) Math.round(getX()), (int) Math.round(getY()), 3, 3);
		g.setColor(Color.GRAY);
		g.drawOval((int) Math.round(getX()), (int) Math.round(getY()), 3, 3);
		
		// TODO: DELETE THIS:
		g.setColor(color);
		drawLineTest(g, new Direction(0, 1));
		drawLineTest(g, new Direction(1, 0));
		for (int x = 1; x < 10; x++) {
			for (int y = 1; y < 10; y++) {
				drawLineTest(g, new Direction(x, y));
			}
		}
		
	}

	// TODO: DELETE THIS:
	private void drawLineTest(Graphics g, Direction direction) {
		g.drawLine(100, 200, 100 + (int) (direction.getX() * 30), 200 + (int) (direction.getY() * 30));
	}

}

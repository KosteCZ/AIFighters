package cz.koscak.jan.model;

import java.awt.Graphics;

public abstract class AbstactDrawableModel {

	private double x;
	private double y;

	public AbstactDrawableModel(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void print(Graphics g) {
		g.drawOval((int) Math.round(getX()), (int) Math.round(getY()), 6, 6);
	}

}

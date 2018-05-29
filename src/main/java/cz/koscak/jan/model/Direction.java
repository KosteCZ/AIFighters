package cz.koscak.jan.model;

public class Direction {
	
	private double x = 0;
	private double y = 0;

	public Direction(double x, double y) {
		double z = Math.sqrt((x * x) + (y * y));
		if (z != 0) {
			this.x = x / z;
			this.y = y / z;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
}

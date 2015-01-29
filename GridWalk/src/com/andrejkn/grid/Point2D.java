package com.andrejkn.grid;

public class Point2D {
	private int x;
	private int y;
	
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

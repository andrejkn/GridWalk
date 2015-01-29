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
	
	@Override
	public boolean equals(Object p) {
		if(!(p instanceof Point2D)) {
			return false;
		}
		
		if( ((Point2D)p).getX() == this.x && ((Point2D)p).getY() == this.y) {
			return true;
		} else {
			return false;
		}
	}
	
}

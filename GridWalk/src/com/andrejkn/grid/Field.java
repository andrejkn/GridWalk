package com.andrejkn.grid;

enum FieldType {
	Walkable, Obstacle
}

enum FieldStatus {
	Unvisited, Visiting, Visited
}

public class Field extends Point2D {
	FieldType type;
	FieldStatus status;
	
	public Field(FieldType ft, int x, int y) {
		super(x, y);
		this.type = ft;
		this.status = FieldStatus.Unvisited;
	}

	public FieldType type() {
		return type;
	}

}

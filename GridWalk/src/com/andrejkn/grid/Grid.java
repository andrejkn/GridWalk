package com.andrejkn.grid;

import java.util.LinkedList;
import java.util.List;

public class Grid {
	Field[][] gridFieldMatrix;
	private int width;
	private int height;
	
	public Grid(int[][] grid) {
		
		this.width = grid[0].length;
		this.height = grid.length;
		
		gridFieldMatrix = new Field[height][width];
		
		for(int y = 0; y < height; y += 1) {
			for(int x = 0; x < width; x += 1) {
				if(grid[y][x] == 1) {
					gridFieldMatrix[y][x] = new Field(FieldType.Obstacle, x, y);
				} else {
					gridFieldMatrix[y][x] = new Field(FieldType.Walkable, x, y);
				}
			}
		}
	}
	
	public List<Field> adjacents(Point2D gridPoint) {
		int startX = (gridPoint.getX() > 0) ? gridPoint.getX() - 1 : 0;
		int endX = (gridPoint.getX() <= width - 1) ? gridPoint.getX() + 1 : width - 1;
		int startY = (gridPoint.getY() > 0) ? gridPoint.getY() - 1 : 0;
		int endY = (gridPoint.getY() <= height - 1) ? gridPoint.getY() + 1 : height - 1;
		
		List<Field> adjacents = new LinkedList<Field>();
		
		for(int y = startY; y <= endY; y += 1) {
			for(int x = startX; x <= endX; x += 1) {
				if(gridPoint.getX() != x && gridPoint.getY() != y) {
					adjacents.add(this.getField(x, y));
				}
			}
		}
		return null;
	}
	
	private Field getField(int x, int y) {
		Field selected = null;
		
		try {
			selected = this.gridFieldMatrix[y][x];
		} catch(ArrayIndexOutOfBoundsException ex) {
			System.err.println(ex.getStackTrace());
		}
		return selected;
	}

	public String toString() {
		String line = "";
		for(int i = 0; i < width; i += 1) {
			if(i == 0) line += "|";
			line += "=";
		}
		line += "|\n";
		
		String toRet = "";
		
		for(int i = 0; i < height; i += 1) {
			for(int j = 0; j < width; j += 1) {
				if(j == 0) toRet += "|";
				if(gridFieldMatrix[i][j].type() == FieldType.Walkable) {
					toRet += " ";
				} else {
					toRet += "X";
				}
			}
			toRet += "|\n";
		}
		
		return line + toRet + line;
	}
}

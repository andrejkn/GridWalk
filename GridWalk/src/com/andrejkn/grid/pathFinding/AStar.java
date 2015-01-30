package com.andrejkn.grid.pathFinding;

import java.util.HashMap;

import com.andrejkn.grid.Field;
import com.andrejkn.grid.Grid;
import com.andrejkn.grid.Point2D;

public class AStar {

	public static void findShortestPath(Grid grid, Point2D start, Point2D goal) {
		HashMap<Field, Integer> fieldToGValueMap = new HashMap<Field, Integer>();
		
		fieldToGValueMap.put(grid.getField(start), 2000);
		
		System.out.println((fieldToGValueMap.get(grid.getField(start))));
	}
}

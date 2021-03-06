package com.andrejkn.grid.pathFinding;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.andrejkn.grid.Field;
import com.andrejkn.grid.Grid;
import com.andrejkn.grid.Point2D;

public class AStar {

	private static HashMap<Field, Integer> gValue = new HashMap<Field, Integer>();
	private static HashMap<Field, Integer> hValue = new HashMap<Field, Integer>();
	private static HashMap<Field, Integer> fValue = new HashMap<Field, Integer>();

	private static HashMap<Field, Field> parentFields = new HashMap<Field, Field>();
	
	public static void findShortestPath(Grid grid, Point2D start, Point2D goal) {
		List<Field> open = new LinkedList<Field>();
		List<Field> closed = new LinkedList<Field>();
		
		Field startField = grid.getField(start);
		Field goalField = grid.getField(goal);
		
		gValue.put(startField, 0);
		hValue.put(startField, calculateHValue(startField, goalField));
		fValue.put(startField, calculateFValue(startField));
		
		open.add(grid.getField(start));
		
		while(!open.isEmpty()) {
			Field current = chooseFieldWithGreatestFValue(open);
			
			if(current.equals(goalField)) {
				System.out.println("Reached the goal " + goalField);
				break;
			}
			
			open.clear();
			
			List<Field> adjacents = grid.adjacents(current);
			
			boolean hasOpenAdjacent = false;
			for(Field adj : adjacents) {
				if(!closed.contains(adj)) {
					hasOpenAdjacent = true;
					break;
				}
			}
					
			if(adjacents.isEmpty() || !hasOpenAdjacent) {
				current = findParentWithAdjacents(grid, current);
				adjacents = grid.adjacents(current);
			}			
			
//			System.out.println("Current = " + current);
//			System.out.print("Adjacents = ");

			for(Field adj : adjacents) {
				if(!closed.contains(adj)) {
//					System.out.print(adj + ", ");

					parentFields.put(adj, current);
					gValue.put(adj, calculateGValue(current, adj));
					hValue.put(adj, calculateHValue(adj, goalField));
					fValue.put(adj, calculateFValue(adj));
					open.add(adj);
				}
			}
//			System.out.println();
			
			closed.add(current);
			current.visit();
		}
		
	}
	
	private static int calculateGValue(Field parent, Field child) {
		double gValOfParent = gValue.get(parent);
		
		// Euclidean distance from parent to child
		int xDist = child.getX() - parent.getX();
		int yDist = child.getY() - parent.getY();
		
		double childDist = Math.sqrt(Math.pow(xDist, 2.0) + Math.pow(yDist, 2.0));
		return (int) (childDist + gValOfParent);
	}
	
	private static int calculateHValue(Field a, Field b) {
		// Calculate the heuristic distance from field a to b
		//  the amount of fields horizontally and vertically
		int xDiff = Math.abs(b.getX() - a.getX());
		int yDiff = Math.abs(b.getY() - a.getY());
		return xDiff + yDiff;
	}
	
	private static int calculateFValue(Field field) {
		int g = (int) gValue.get(field);
		int h = hValue.get(field);
		return g + h;
	}
	
	private static Field chooseFieldWithGreatestFValue(List<Field> fields) {
		Field chosen = null;
		
		for(Field current : fields) {
			if(chosen == null) {
				chosen = current;
			} else if(fValue.get(chosen) >= fValue.get(current)){
				chosen = current;
			}
		}
		
		return chosen;
	}
	
	private static Field findParentWithAdjacents(Grid grid, Field current) {
		List<Field> adjacents = new LinkedList<Field>();
		Field cField = current;
		
		while(adjacents.isEmpty()) {
			adjacents = grid.adjacents(cField);
			cField = parentFields.get(cField);
		}

		return cField;
	}
}

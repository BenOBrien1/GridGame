// Adam Nolan  	16160037
// Ben O'Brien 	 16187555
// Brian Kummer   16190319
// Dillon Rosenkranz 16159144

//Tile class included in the email. Please have both files in the same directory!

import javax.swing.*; 
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

public class is16160037{
	static JFrame root = new JFrame();
	static JButton [] buts = new JButton [9];
	
	public static void main(String [] args){
		Tile [] startState;
		Tile [] goalState;
		String input = "";
		boolean validateCheck = false;
		while(!validateCheck){
			input = JOptionPane.showInputDialog(null,"Enter The Start State. Use a space to seperate each number.");
			validateCheck = validate(input);
		}
		startState = createArray(input);
		
		String input2 = "";
		validateCheck = false;
		while(!validateCheck){
			input2 = JOptionPane.showInputDialog(null,"Enter The Goal State. Use a space to seperate each number.");
			validateCheck = validate(input2);
		}
		goalState = createArray(input2);
		
		Tile zeroTile = new Tile(0, 0);
		
		for(int j = 0; j < startState.length; j++) {
			if(startState[j].getValue() == 0) {
				zeroTile = startState[j];
			}
		}
		
		//Print out h value
		for(int i = 0; i < startState.length; i++) {
			startState[i].setMovable(checkIsMovable(startState[i], zeroTile));
			startState[i].setHValue(getTileHValue(startState[i], goalState));
			if(startState[i].getMovable()) {
				System.out.println("H Value is " + startState[i].getHValue());
			}
		}
		System.out.println("\nH Function of movable tiles = " + getTotalHValue(startState, goalState));
		
		root.setSize(300,300);  
		root.setVisible(true); 
		root.setLocation(10, 200);
    	root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	root.setTitle("8buzz");
    	
		printPuzzle(startState, true);
		
		ArrayList<Tile[]> open = new ArrayList<Tile[]>();
		ArrayList<Integer> openGValues = new ArrayList<Integer>();
		ArrayList<Tile[]> closed = new ArrayList<Tile[]>();
		ArrayList<Tile[]> printer = new ArrayList<Tile[]>();
		Tile[] currentState = startState;
		int gValueCounter = 0;
		
		int gValue = 1;
		
		//for(int test = 0; test < 10; test++){ // test loop
		
		while(!(compareTileArrays(currentState, goalState))) {
			int[] hValues;
			/*for(int i = 0; i < currentState.length; i++) {
				currentState[i].setMovable(checkIsMovable(currentState[i], zeroTile));
			}*/
			

			//Created open AL with all possible states.
			for(int x = 0; x < currentState.length; x++) {
				if(currentState[x].getMovable()) {
					
					Tile zeroTiler = new Tile(0,0);
					Tile[] temp = new Tile[9];
					for(int j = 0; j < 9; j++){
						int cVal = currentState[j].getValue();
						int cLoc = currentState[j].getLocation();
						boolean cMov = currentState[j].getMovable();
						if(cVal == 0){
							zeroTiler.setValue(cVal);
							zeroTiler.setLocation(cLoc);
						}
						Tile newTile = new Tile(cVal, cLoc);
						newTile.setMovable(cMov);
						temp[j] = newTile;
					}
					
					
					temp = swapTiles(temp[x], zeroTiler, temp);
					
					
					Tile [] temper = new Tile[9];
					for(int o = 0; o < 9; o++){
						
						int cVal = temp[o].getValue();
						int cLoc = temp[o].getLocation();
						boolean cMov = temp[o].getMovable();
						Tile newTile2 = new Tile(cVal, cLoc);
						newTile2.setMovable(cMov);
						temper[o] = newTile2;
					}
					boolean check = true;
					
					for(int iter = 0; iter < closed.size() && check; iter++){
						if(compareTileArrays(temper, closed.get(iter)) || compareTileArrays(temper, currentState)){
								check = false;
						}
					}
					if(check){
						open.add(temper);
						openGValues.add(gValueCounter);
					}
				}
			}
			hValues = new int[open.size()];
			int[] fValues = new int[open.size()];
			for(int y = 0; y < open.size(); y ++) {
				
					hValues[y] = getTotalHValue(open.get(y), goalState);
					fValues[y] = (hValues[y] + openGValues.get(y));

			}
			int lowestFValueLocation = getMinValueLocation(fValues);
			
			
			//now open.get(lowestHValueLocation) is the most efficient next state.
			
			closed.add(currentState);
			printer.add(currentState);
			
			
			//Not working. Need to print the shortest path. When we go back to a previous state,
			//remove all the states after it,(only in printer), then print them to console at the end. 
			for(int yy = 0; yy < printer.size(); yy++){
				if(compareTileArrays(printer.get(yy), currentState)){
					System.out.println("ln: " + printer.size());
					for(int zz = printer.size()-1; zz > yy; zz--){
						printer.remove(zz);
						System.out.println(zz);
					}
					System.out.println("ln: " + printer.size());
					yy = printer.size();
				}
			}
			
			currentState = open.get(lowestFValueLocation);
			
			
			open.remove(lowestFValueLocation);
			openGValues.remove(lowestFValueLocation);
			
			gValueCounter++;
		}

		printClosedStates(printer);
		
		
		printClosedStates(currentState);
		//printClosedStates(closed);

	}
	//Takes in arrayList and prints the grid
	public static void printClosedStates(ArrayList<Tile[]> closed){

        for(int i = 0 ; i < closed.size() ; i++){
            for(int j = 0; j < 9; j++){
                if((j % 3) == 0){
                    System.out.print("\n");
				}
                System.out.print((closed.get(i))[j].getValue() + " ");
            }
            System.out.println("");
        }
    }
	public static void printClosedStates(Tile[] closed){

            for(int j = 0; j < 9; j++){
                if((j % 3) == 0){
                    System.out.print("\n");
				}
                System.out.print(closed[j].getValue() + " ");
            }
            System.out.println("");
    }
	
	
	//Takes in 2 arrays of tiles and checks if there are the same
	public static boolean compareTileArrays(Tile [] a, Tile [] b){
        boolean flag = true;
        for(int i = 0; i < a.length ; i++){
                if(a[i].getValue() != b[i].getValue()){
                    flag = false;
				}
        }
        return flag;
    }
	
	//Takes in a strin and validates it, returning a boolean.
	public static boolean validate(String input){
		
			boolean flag = true;
			String pattern1 = "^[0-8] (?:[0-8] ){7}[0-8]$";
			
			if(!(input.matches(pattern1))){
				JOptionPane.showMessageDialog(null, "Please use numbers between 0-8 seperated by a space.", "Failure", JOptionPane.ERROR_MESSAGE);
				flag = false;
			}
			return flag;
		}
		
		public static Tile [] createArray(String input){
			Tile [] tiles;
			String [] array1 = input.split(" ");
			tiles = new Tile[array1.length];
			for(int i = 0; i < tiles.length; i++){
				tiles[i] = new Tile(Integer.parseInt(array1[i]), i);
			}
				
			for(int i = 0; i < tiles.length; i++){
				for(int j = i + 1; j < tiles.length; j++){
					if(tiles[i].getValue() == tiles[j].getValue()){
						JOptionPane.showMessageDialog(null, "Please do enter the same number multiple times.", "Failure", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			return tiles;
		}
		
	//Takes in an array or tile objects and prints a grid of tiles to a pane.
	private static Tile[] printPuzzle(Tile[] tiles, boolean visible)
	{
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(3,3)); 
		Tile temp;

		for(int i=0;i < buts.length;i++)
		{
				temp = tiles[i];
				if(tiles[i].getValue() > 0){
					buts[i] = new JButton(Integer.toString(temp.getValue()));
				} else {
					buts[i] = new JButton("");
				}

				buts[i].setPreferredSize(new Dimension(100, 100));
				pane.add(buts[i]);
		}
		
		pane.setVisible(visible); 
		root.getContentPane().add(pane);
		root.setVisible(visible); 
		return tiles;
	}
	
	//Takes in a tile and the empty tile, then compares them and checks if 
	// the tile can be moved. Returns a boolean.
	public static boolean checkIsMovable(Tile tile, Tile zeroTile) {
		boolean isMovable = false;
		String direction = "";
		int tileLoc = tile.getLocation();
		
		if(tile.getValue() == 0){
			return false;
		}
		
		if(tileLoc -1 == zeroTile.getLocation() && tileLoc != 3 && tileLoc != 6) { 
			isMovable = true;
			direction = "west. ";
		}
		else if(tileLoc + 1 == zeroTile.getLocation() && tileLoc != 2 && tileLoc != 5) {
			isMovable = true;
			direction = "east. ";
		}
		else if(tileLoc - 3 == zeroTile.getLocation()) {
			isMovable = true;
			direction = "north.";
		}
		else if(tileLoc + 3 == zeroTile.getLocation()){
			isMovable = true;
			direction = "south.";
		}
		
		//if(isMovable) {
		//	System.out.print("" + tile.getValue() + " can move to the " + direction + "\t");
		//}
		
		
		return isMovable;
	}
	
	//Takes in a tile and the goal state, gets the h-value of the tile.
	public static int getTileHValue(Tile tile, Tile[] goalState) {
		int tileRow;
		int goalStateRow;
		int hValue = 100;
		tileRow = getTileRow(tile);
		boolean foward = false;
		
		for(int j = 0; j < 9; j++) {
			if(tile.getValue() == goalState[j].getValue()) {
				if(tile.getLocation() < goalState[j].getLocation()) {
					foward = true;
				}
				goalStateRow = getTileRow(goalState[j]);
				if(tileRow == goalStateRow) {
					if(foward) {
						hValue = (goalState[j].getLocation() - tile.getLocation());
					}
					else {
						hValue = (tile.getLocation() - goalState[j].getLocation());
					}
				}
				else if((tileRow - goalStateRow) < 2 && (tileRow - goalStateRow) > -2) {
					if(foward) {
						hValue = ((goalState[j].getLocation() + 1) - (tile.getLocation() + 3));
					}
					else {
						hValue = (((tile.getLocation() - 3) -(goalState[j].getLocation())) + 1);
					}
				}
				else if((tileRow - goalStateRow) < 3 && (tileRow - goalStateRow) > -3) {
					if(foward) {
						hValue = ((goalState[j].getLocation() + 2) - (tile.getLocation() + 6));
					}
					else {
						hValue = (((tile.getLocation() - 6) - (goalState[j].getLocation()) + 2));
					}
				}
			}
		}
		return hValue;
	}
	
	public static int getTotalHValue(Tile[] tiles, Tile[] goalState) {
		int totalH = 0;
		for(int i = 0; i < tiles.length; i++) {
			if(tiles[i].getMovable()) {
				totalH += getTileHValue(tiles[i], goalState);
			}
		}
		return totalH;
	}
	
	
	//Takes in a tile and gets it's row in the grid.
	public static int getTileRow(Tile tile) {
		int tileRow = 0;
		if(tile.getLocation() == 0 || tile.getLocation() == 1 || tile.getLocation() == 2) {
			tileRow = 1;
		}
		else if (tile.getLocation() == 3 || tile.getLocation() == 4 || tile.getLocation() == 5) {
			tileRow = 2;
		}
		else if (tile.getLocation() == 6 || tile.getLocation() == 7 || tile.getLocation() == 8) {
			tileRow = 3;
		}
		return tileRow;
	}
	
	//Takes in a array of tiles and the tile to swap, swaps tile with zero tile and returns new array.
	public static Tile[] swapTiles(Tile tileToSwap, Tile zeroTile, Tile [] temp){
        int zeroLocation = zeroTile.getLocation();
        int tileLocation = tileToSwap.getLocation();
		tileToSwap.setLocation(zeroLocation);
        temp[zeroLocation] = tileToSwap;
		zeroTile.setLocation(tileLocation);
        temp[tileLocation] = zeroTile;

		for(int i = 0; i < temp.length; i++) {
				temp[i].setMovable(checkIsMovable(temp[i], zeroTile));
			}
			
		return temp;
	}
	
	//Takes in an array of ints and return the minimum value's location
	public static int getMinValueLocation(int[] numbers) {
		int minVal = numbers[0];
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] < minVal) {
				minVal = numbers[i];
			}
		}
		for(int x = 0; x < numbers.length; x++) {
			if(numbers[x] == minVal) {
				return x;
			}
		}
		return -1;
		
	}
	
}

import javax.swing.*; 
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;

public class Project{
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
		int hFunction = 0;
		
		for(int i = 0; i < startState.length; i++) {
			startState[i].setMovable(checkIsMovable(startState[i], zeroTile));
			startState[i].setHValue(getTileHValue(startState[i], goalState));
			if(startState[i].getMovable()) {
				System.out.println("H Value is " + startState[i].getHValue());
				hFunction = (hFunction + startState[i].getHValue());
			}
		}
		System.out.println("\nH Function = " + hFunction);
		
		root.setSize(300,300);  
		root.setVisible(true); 
		root.setLocation(10, 200);
    		root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		root.setTitle("8buzz");
   
		printPuzzle(startState);
		
		
	}
	
	//Takes in a string and validates it.
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
	//Takes in an array of tile objects and outputs a grid.
	private static Tile[] printPuzzle(Tile[] tiles)
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
				//System.out.println(temp.getValue());
				buts[i].setPreferredSize(new Dimension(100, 100));
				pane.add(buts[i]);
		}

		buts[0].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
						if(tiles[0].getValue() > 0){
							if(tiles[0].getMovable()) {
								//System.out.println("Click");
								root.remove(pane);
								isClicked(tiles, tiles[0]);
								//System.out.println(tiles[0].getValue());
							}
						}  
				    }
				});
		buts[1].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
						if(tiles[1].getValue() > 0){
							if(tiles[1].getMovable()) {
								//System.out.println("Click");
								root.remove(pane);
								isClicked(tiles, tiles[1]);
								//System.out.println(tiles[1].getValue());
							}
						}  
				    }
				});
		buts[2].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
						if(tiles[2].getValue() > 0){
							if(tiles[2].getMovable()) {
								//System.out.println("Click");
								root.remove(pane);
								isClicked(tiles, tiles[2]);
								//System.out.println(tiles[2].getValue());
							}
						}  
				    }
				});
		buts[3].addActionListener(new ActionListener() {
				    @Override
					public void actionPerformed(ActionEvent e) {
						if(tiles[3].getValue() > 0){
							if(tiles[3].getMovable()) {
								//System.out.println("Click");
								root.remove(pane);
								isClicked(tiles, tiles[3]);
								//System.out.println(tiles[3].getValue());
							}
						}  
				    }
				});
		buts[4].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
						if(tiles[4].getValue() > 0){
							if(tiles[4].getMovable()) {
								//System.out.println("Click");
								root.remove(pane);
								isClicked(tiles, tiles[4]);
								//System.out.println(tiles[4].getValue());
							}
						}  
				    }
				});
		buts[5].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
						if(tiles[5].getValue() > 0){
							if(tiles[5].getMovable()) {
								//System.out.println("Click");
								root.remove(pane);
								isClicked(tiles, tiles[5]);
								//System.out.println(tiles[5].getValue());
							}
						}  
				    }
				});
		buts[6].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
						if(tiles[6].getValue() > 0){
							if(tiles[6].getMovable()) {
								//System.out.println("Click");
								root.remove(pane);
								isClicked(tiles, tiles[6]);
								//System.out.println(tiles[6].getValue());
							}
						}  
				    }
				});
		buts[7].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
						if(tiles[7].getValue() > 0){
							if(tiles[7].getMovable()) {
								//System.out.println("Click");
								root.remove(pane);
								isClicked(tiles, tiles[7]);
								//System.out.println(tiles[7].getValue());
							}
						}  
				    }
				});
		buts[8].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
						if(tiles[8].getValue() > 0){
							if(tiles[8].getMovable()) {
								//System.out.println("Click");
								root.remove(pane);
								isClicked(tiles, tiles[8]);
								//System.out.println(tiles[8].getValue());
							}
						}  
				    }
				});
		pane.setVisible(true); 
		root.getContentPane().add(pane);
		root.setVisible(true); 
		return tiles;
	}
	//Takes in an array of tile and a single clicked tile and swaps the clicked tile with the zero tile in the array.
	private static Tile[] isClicked(Tile [] tiles, Tile clicked)
	{
		Tile temp = new Tile(0,0);
		for(int i = 0; i < tiles.length; i++) {
			if(tiles[i].getValue() == 0) {
				temp = tiles[i];
				tiles[i] = clicked;
			}
			if(clicked.getLocation() == i) {
				tiles[i] = temp;
			}
		}

		//printPuzzle(tiles);
		
		return tiles;
	}
	
	
	
	//Takes in a tile and the zero tile, compares them and checks if the tile can be moved.
	public static boolean checkIsMovable(Tile tile, Tile zeroTile) {
		boolean isMovable = false;
		String direction = "";
		int tileLoc = tile.getLocation();
		
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
		
		if(isMovable) {
			System.out.print("" + tile.getValue() + " can move to the " + direction + "\t");
		}
		
		return isMovable;
	}
	//Takes in a tile and the goal state, gets the h value of the tile.
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
					hValue = (goalState[j].getLocation() - tile.getLocation());
				}
				else if((tileRow - goalStateRow) < 2 && (tileRow - goalStateRow) > -2) {
					if(foward) {
						hValue = ((tile.getLocation() + 3) - goalState[j].getLocation() + 1);
					}
					else {
						hValue = ((tile.getLocation() - 3) - goalState[j].getLocation() + 1);
					}
				}
				else if((tileRow - goalStateRow) < 3 && (tileRow - goalStateRow) > -3) {
					if(foward) {
						hValue = ((tile.getLocation() + 6) - goalState[j].getLocation() + 2);
					}
					else {
						hValue = ((tile.getLocation() - 6) - goalState[j].getLocation() + 2);
					}
				}
			}
		}
		return hValue;
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
		
		
}

import javax.swing.*; 
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;

public class Project{
	//static JFrame root = new JFrame();
	//static JButton [] buts = new JButton [9];
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
		
	public static void printPuzzle(Tile[] tiles)
	{
		JFrame root = new JFrame();
		root.setTitle("8buzz");
		Tile temp;

		JButton [] buts = new JButton [9];
		for(int i=0;i < buts.length;i++)
		{
				temp = tiles[i];
				String t = Integer.toString(temp.getValue());
				if(tiles[i].getValue() > 0){
					buts[i] = new JButton(Integer.toString(temp.getValue()));
				} else {
					buts[i] = new JButton("");
				}
				System.out.println(temp.getValue());
				t = Integer.toString(tiles[i].getValue());
				buts[i].setPreferredSize(new Dimension(100, 100));
				root.add(buts[i]);
		}

		buts[0].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
					if(tiles[0].getValue() > 0){
						root.dispose();
						isClicked(tiles, tiles[0]);
						System.out.println(tiles[0].getValue());
					}  
				    }
				});
		buts[1].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
					if(tiles[1].getValue() > 0){
						root.dispose();
						isClicked(tiles, tiles[1]);
						System.out.println(tiles[1].getValue());
					}  
				    }
				});
		buts[2].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
					if(tiles[2].getValue() > 0){
						root.dispose();
						isClicked(tiles, tiles[2]);
						System.out.println(tiles[2].getValue());
					}  
				    }
				});
		buts[3].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
					if(tiles[3].getValue() > 0){
						root.dispose();
						isClicked(tiles, tiles[3]);
						System.out.println(tiles[3].getValue());
					}  
				    }
				});
		buts[4].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
					if(tiles[4].getValue() > 0){
						root.dispose();
						isClicked(tiles, tiles[4]);
						System.out.println(tiles[4].getValue());
					}  
				    }
				});
		buts[5].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
					if(tiles[5].getValue() > 0){
						root.dispose();
						isClicked(tiles, tiles[5]);
						System.out.println(tiles[5].getValue());
					}  
				    }
				});
		buts[6].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
					if(tiles[6].getValue() > 0){
						root.dispose();
						isClicked(tiles, tiles[6]);
						System.out.println(tiles[6].getValue());
					}  
				    }
				});
		buts[7].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
					if(tiles[7].getValue() > 0){
						root.dispose();
						isClicked(tiles, tiles[7]);
						System.out.println(tiles[7].getValue());
					}  
				    }
				});
		buts[8].addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
					if(tiles[8].getValue() > 0){
						root.dispose();
						isClicked(tiles, tiles[8]);
						System.out.println(tiles[8].getValue());
					}  
				    }
				});

		root.setLayout(new GridLayout(3,3));  
		root.setSize(300,300);  
		root.setVisible(true); 
		}
	
	
	
	public static void main(String [] args){
		Tile [] startState;
		Tile [] goalState;
		String input = JOptionPane.showInputDialog(null,"Enter The Start State. Use a space to seperate each number.");
		startState = createArray(input);
			
		while(!validate(input)){
			input = JOptionPane.showInputDialog(null,"Enter The Start State. Use a space to seperate each number.");
			startState = createArray(input);
		}
		
		String input2 = JOptionPane.showInputDialog(null,"Enter The Goal State. Use a Space to seperate each number.");
		goalState = createArray(input2);
		
		while(!validate(input2)){
			input = JOptionPane.showInputDialog(null,"Enter The Goal State. Use a space to seperate each number.");
			goalState = createArray(input);
		}
		
		printPuzzle(startState);
	}
	
	public static boolean checkIsMovable(Tile tile, Tile zeroTile) {
		boolean isMovable = false;
		String direction = "";
		int tileLoc = tile.getLocation();
		
		if(tileLoc -1 == zeroTile.getLocation() && tileLoc != 3 && tileLoc != 6) { 
			isMovable = true;
			direction = "west";
		}
		else if(tileLoc + 1 == zeroTile.getLocation() && tileLoc != 2 && tileLoc != 5) {
			isMovable = true;
			direction = "east";
		}
		else if(tileLoc - 3 == zeroTile.getLocation()) {
			isMovable = true;
			direction = "north";
		}
		else if(tileLoc + 3 == zeroTile.getLocation()){
			isMovable = true;
			direction = "south";
		}
		
		if(isMovable) {
			System.out.println("" + tile.getValue() + " to the " + direction);
		}
		
		return isMovable;
	}
	
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

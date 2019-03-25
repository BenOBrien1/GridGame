import javax.swing.*; 
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.*;

public class Project{
	static JFrame root = new JFrame();
	static JButton [] buts = new JButton [9];
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
		
	public static void printPuzzle(Tile[] tiles){
		root.revalidate();
		root.repaint();
		root.setTitle("8buzz");
		Tile temp;

        //JButton [] buts = new JButton [9];
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
						root.removeAll();
			       		isClicked(tiles, tiles[0]);
			        	System.out.println(tiles[0].getValue());
			       	}  
			    }
			});
        buts[1].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        if(tiles[1].getValue() > 0){
						root.removeAll();
			       		isClicked(tiles, tiles[1]);
			        	System.out.println(tiles[1].getValue());
			       	}  
			    }
			});
        buts[2].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        if(tiles[2].getValue() > 0){
						root.removeAll();
			       		isClicked(tiles, tiles[2]);
			        	System.out.println(tiles[2].getValue());
			       	}  
			    }
			});
        buts[3].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        if(tiles[3].getValue() > 0){
			       		isClicked(tiles, tiles[3]);
			        	System.out.println(tiles[3].getValue());
			       	}  
			    }
			});
        buts[4].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        if(tiles[4].getValue() > 0){
			       		isClicked(tiles, tiles[4]);
			        	System.out.println(tiles[4].getValue());
			       	}  
			    }
			});
        buts[5].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        if(tiles[5].getValue() > 0){
			       		isClicked(tiles, tiles[5]);
			        	System.out.println(tiles[5].getValue());
			       	}  
			    }
			});
        buts[6].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        if(tiles[6].getValue() > 0){
			       		isClicked(tiles, tiles[6]);
			        	System.out.println(tiles[6].getValue());
			       	}  
			    }
			});
        buts[7].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        if(tiles[7].getValue() > 0){
			       		isClicked(tiles, tiles[7]);
			        	System.out.println(tiles[7].getValue());
			       	}  
			    }
			});
        buts[8].addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        if(tiles[8].getValue() > 0){
			       		isClicked(tiles, tiles[8]);
			        	System.out.println(tiles[8].getValue());
			       	}  
			    }
			});
       
        root.setLayout(new GridLayout(3,3));  
    	root.setSize(300,300);  
    	root.setVisible(true); 
		//root.removeAll();
	}
	
	public static void isClicked(Tile [] tilesArr, Tile tile){
        if( tile.isMovable ){
            for( int t = 0; t < tilesArr.length; t++){
                if( tilesArr[t].getValue() == 0 ){
                    Tile temp = tilesArr[t];
                    tilesArr[tile.getLocation()] = temp;
                    tilesArr[t] = tile;
                }
            }
        }
		root.removeAll();
		printPuzzle(tilesArr);
    }	
	
	
	
	public static void main(String [] args){
		Tile [] tiles;
		String input = JOptionPane.showInputDialog(null,"Enter Numbers. Use a space to seperate each number.");
		tiles = createArray(input);
			
		while(!validate(input)){
			input = JOptionPane.showInputDialog(null,"Enter Numbers. Use a space to seperate each number.");
			tiles = createArray(input);
		}
		
		printPuzzle(tiles);
	}
		
}


public class Tile {
	int value;
	boolean isMovable;;
	int hValue;
	int location;
	
	public Tile(int val, int loc ) {
		this.value = val;
		this.location = loc;
	}
	
	public Tile(int val, boolean isMov, int h, int loc) {
		this.value = val;
		this.isMovable = isMov;
		this.hValue = h;
		this.location = loc;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean getMovable() {
		return isMovable;
	}

	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	public int getHValue() {
		return hValue;
	}

	public void setHValue(int hValue) {
		this.hValue = hValue;
	}
	
	public int getLocation() {
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}

}

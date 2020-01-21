package Model;

public class BodyPart {
	// instance variables
	private int locationX;
	private int locationY;

	//constructor
	public BodyPart(int locationX, int locationY) {
		this.locationX = locationX;
		this.locationY = locationY;
	}

	//returns the x coordinate of a bodypart
	public int getLocationX() {
		return locationX;
	}
	
	//sets the x coordinate of a bodypart
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}
	
	//returns the y coordinate of a bodypart
	public int getLocationY() {
		return locationY;
	}

	//sets the y coordinate of a bodypart
	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}
}

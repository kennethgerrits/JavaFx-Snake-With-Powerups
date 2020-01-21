package Model;

import java.util.Random;

public class Spot {
	// instance variables
	private Marker marker;
	private Random random;
	private int xPos;
	private int yPos;

	// constructor
	public Spot() {
		random = new Random();
		generateRandomPosition();
		// sets marker to a random value of BEAR, FIRE, MOUSE.
		marker = Marker.values()[random.nextInt(Marker.values().length)];
	}

	// creates a random number based on the rows and cols, then multiplies it by 40
	// (size of a tile).
	private void generateRandomPosition() {
		xPos = random.nextInt((19) + 1) * 40;
		yPos = random.nextInt((15) + 1) * 40;
	}

	// returns the marker value (BEAR, FIRE, MOUSE).
	public Marker getMarker() {
		return marker;
	}

	//returns the x coordinate of a spot.
	public int getxPos() {
		return xPos;
	}
	
	//returns the y coordinate of a spot.
	public int getyPos() {
		return yPos;
	}
}

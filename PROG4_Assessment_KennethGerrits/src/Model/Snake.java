package Model;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Snake {
	// constants
	private final int snakeDia = 40;
	private final int leftMinValue = 0;
	private final int topMinValue = 0;
	private final int rightMinValue = 760;
	private final int downMinValue = 600;
	private final int snakeStartSize = 5;

	// instance variables
	private ArrayList<BodyPart> bodyParts;
	private Game game;
	private Direction direction;

	// properties
	private IntegerProperty snakeX = new SimpleIntegerProperty(this, "snakeX");
	private IntegerProperty snakeY = new SimpleIntegerProperty(this, "snakeY");

	// constructor
	public Snake(int x, int y, Game game) {
		this.game = game;
		snakeX.set(x);
		snakeY.set(y);
		bodyParts = new ArrayList<>();

		// give the snake 4 bodyParts to start with.
		for (int i = 1; i < snakeStartSize; i++) {
			bodyParts.add(new BodyPart(getSnakeX() - snakeDia * i, getSnakeY()));
		}
	}

	// add a bodyPart at the end of the snake.
	public void addBodyPart() {
		bodyParts.add(new BodyPart(bodyParts.get(bodyParts.size() - 1).getLocationX(),
				bodyParts.get(bodyParts.size() - 1).getLocationY()));
	}

	// returns the ArrayList bodyParts.
	public ArrayList<BodyPart> getBodyParts() {
		return bodyParts;
	}

	// gets the snakeX coordinate.
	public int getSnakeX() {
		return snakeX.get();
	}

	// gets the snakeY coordinate.
	public int getSnakeY() {
		return snakeY.get();
	}

	// sets the snakeX coordinate
	public void setSnakeX(int newVal) {
		snakeX.set(newVal);
	}

	// sets the snakeY coordinate
	public void setSnakeY(int newVal) {
		snakeY.set(newVal);
	}

	// returns the direction of the snake
	public Direction getDirection() {
		return direction;
	}

	// sets the direction of the snake
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	// returns the IntegerProperty of snakeX
	public IntegerProperty snakeXProperty() {
		return snakeX;
	}

	// returns the IntegerProperty of snakeY
	public IntegerProperty snakeYProperty() {
		return snakeY;
	}

	// sets the direction of the snake to up.
	public void moveUp() {
		// checks if the snake hits the top of the screen, if so set gameOver true.
		if (getSnakeY() - snakeDia < topMinValue) {
			game.setGameOver();
		}
		moveBodyParts(getSnakeX(), getSnakeY());
		this.setSnakeY(getSnakeY() - snakeDia);
	}

	// sets the direction of the snake to right.
	public void moveRight() {
		// checks if the snake hits the right of the screen, if so set gameOver true.
		if (getSnakeX() + snakeDia >= rightMinValue) {
			game.setGameOver();
		}
		moveBodyParts(getSnakeX(), getSnakeY());
		this.setSnakeX(getSnakeX() + snakeDia);
	}

	// sets the direction of the snake to down.
	public void moveDown() {
		// checks if the snake hits the bottom of the screen, if so set gameOver true.
		if (getSnakeY() + snakeDia >= downMinValue) {
			game.setGameOver();
		}
		moveBodyParts(getSnakeX(), getSnakeY());
		this.setSnakeY(getSnakeY() + snakeDia);
	}

	// sets the direction of the snake to left.
	public void moveLeft() {
		// checks if the snake hits the left of the screen, if so set gameOver true.
		if (getSnakeX() - snakeDia < leftMinValue) {
			game.setGameOver();
		}
		moveBodyParts(getSnakeX(), getSnakeY());
		this.setSnakeX(getSnakeX() - snakeDia);
	}

	// relocates the bodyParts of the snake so it follows the head.
	private void moveBodyParts(int lastHeadX, int lastHeadY) {
		ArrayList<BodyPart> newBodyPartList = new ArrayList<>();
		BodyPart lastBP = bodyParts.get(bodyParts.size() - 1);
		lastBP.setLocationX(lastHeadX);
		lastBP.setLocationY(lastHeadY);
		newBodyPartList.add(lastBP);

		for (int i = 0; i < bodyParts.size() - 1; i++) {
			newBodyPartList.add(bodyParts.get(i));
		}
		bodyParts = newBodyPartList;
	}

	// removes the last 5 bodyParts, used when the snake hits a BEAR spot.
	public void removeBodyParts() {
		int removeable = Math.round(bodyParts.size() / 2);
		for (int i = 0; i <= removeable; i++) {
			bodyParts.remove(bodyParts.size() - 1);
		}
	}
}

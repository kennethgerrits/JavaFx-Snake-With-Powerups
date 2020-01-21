package Model;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	// constants
	private final int snakeStartPositionX = 280;
	private final int snakeStartPositionY = 80;

	// instance variables
	private Snake snake;
	private ArrayList<Spot> spots;
	private boolean gameOver = false;
	private Random random;

	// constructor
	public Game() {
		snake = new Snake(snakeStartPositionX, snakeStartPositionY, this);
		spots = new ArrayList<>();
		random = new Random();
	}

	// returns the snake.
	public Snake returnSnake() {
		return snake;
	}

	// creates a new spot and adds it to the spots ArrayList.
	public void createSpots() {
		if (random.nextInt(4) == 1) {
		spots.add(new Spot());
		}
	}

	// returns the spots ArrayList.
	public ArrayList<Spot> getSpots() {
		return spots;
	}

	// adds a bodyPart to the snake.
	public void addBodyPart() {
		snake.addBodyPart();
	}

	// sets gameOver to true.
	public void setGameOver() {
		gameOver = true;
	}

	// returns gameOver.
	public boolean isGameOver() {
		return gameOver;
	}

	// checks if the snake hits either itself or a spot. I
	public void checkUnitCollision() {
		// checks if the snake hits a spot, if so it consumes it and triggers the method
		// handleSpotColossion.
		for (int i = 0; i < spots.size(); i++) {
			if (spots.get(i).getxPos() == snake.getSnakeX() && spots.get(i).getyPos() == snake.getSnakeY()) {
				handleSpotColission(spots.get(i));
				spots.remove(spots.get(i));
			}

			// checks if the snake hits itself, if so set gameOver true.
			for (BodyPart bp : snake.getBodyParts()) {
				if (bp.getLocationX() == snake.getSnakeX() && bp.getLocationY() == snake.getSnakeY()) {
					gameOver = true;
				}
			}
		}
	}

	// calls the associated method based on the spot type of enum.
	private void handleSpotColission(Spot spot) {
		switch (spot.getMarker()) {
		case BEAR:
			handleBear();
			break;

		case FIRE:
			handleFire();
			break;

		case MOUSE:
			handleMouse();
			break;

		default:
			break;
		}
	}

	// add 5 bodyParts to the snake.
	private void handleMouse() {
		for (int i = 0; i < 5; i++) {
			snake.addBodyPart();
		}
	}

	// set gameOver to true.
	private void handleFire() {
		gameOver = true;
	}

	// halfs the snake in bodyParts, rounded down. If the snake has 4 or less
	// bodyParts, set gameOver true.
	private void handleBear() {
		snake.removeBodyParts();
		if (snake.getBodyParts().size() < 4) {
			gameOver = true;
		}
	}
}

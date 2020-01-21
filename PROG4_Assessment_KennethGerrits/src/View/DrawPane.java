package View;

import java.util.ArrayList;
import Model.BodyPart;
import Model.Marker;
import Model.Snake;
import Model.Spot;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DrawPane extends Pane {
	// constants
	private final int WIDTH = 760;
	private final int HEIGHT = 600;
	private final int snakeDiameter = 40;
	private final int tileSize = 40;
	private final Color black = Color.BLACK;
	private final Color blackOne = Color.rgb(30, 30, 30);
	private final Color blackTwo = Color.rgb(40, 40, 40);
	private final Color snakeHeadColor = Color.RED;
	private final Color bodyPartColor = Color.ORANGE;

	// instance variables
	private GridPane field;
	private Canvas canvas;
	private Snake snake;
	private ArrayList<Spot> spots;

	// constructor
	public DrawPane(Snake snake) {
		this.snake = snake;
		spots = new ArrayList<>();
		field = new GridPane();

		// sets the start scenario for the drawPane.
		setPrefHeight(HEIGHT);
		setPrefWidth(WIDTH);
		setGrid();
		getChildren().add(field);
		draw();

		// LISTENERS
		snake.snakeYProperty().addListener((observable, oldValue, newValue) -> draw());
		snake.snakeXProperty().addListener((observable, oldValue, newValue) -> draw());
	}

	// sets the spots ArrayList.
	public void setSpots(ArrayList<Spot> arrayList) {
		spots = arrayList;
	}

	// draws the snake and spots.
	private void draw() {
		canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext g = canvas.getGraphicsContext2D();

		// draws the snakeHead.
		g.setFill(snakeHeadColor);
		g.fillOval(snake.getSnakeX(), snake.getSnakeY(), snakeDiameter, snakeDiameter);

		// draws the bodyParts of the snake.
		for (BodyPart bp : snake.getBodyParts()) {
			g.setFill(bodyPartColor);
			g.fillOval(bp.getLocationX(), bp.getLocationY(), snakeDiameter, snakeDiameter);
		}

		// draws the spots.
		for (Spot spot : spots) {
			if (spot.getMarker() == Marker.MOUSE) {
				g.drawImage(new Image(getClass().getResourceAsStream("/mouse.png")), (double) spot.getxPos(),
						(double) spot.getyPos(), tileSize, tileSize);
			} else if (spot.getMarker() == Marker.FIRE) {
				g.drawImage(new Image(getClass().getResourceAsStream("/fire.png")), (double) spot.getxPos(),
						(double) spot.getyPos(), tileSize, tileSize);
			} else if (spot.getMarker() == Marker.BEAR) {
				g.drawImage(new Image(getClass().getResourceAsStream("/bear.png")), (double) spot.getxPos(),
						(double) spot.getyPos(), tileSize, tileSize);
			}

		}
		// removes a bodyPart to create the illusion of movement.
		if (this.getChildren().size() == 2) {
			this.getChildren().remove(1);
		}
		// adds the canvas to the screen.
		this.getChildren().add(canvas);
	}

	// creates a 19x15 grid.
	private void setGrid() {
		for (int x = 0; x < 19; x++) {
			for (int y = 0; y < 15; y++) {
				if (checkForEvenNumber(x) && checkForEvenNumber(y)) {
					field.add(new Rectangle(tileSize, tileSize, black), x, y);
				} else if (checkForEvenNumber(x)) {
					field.add(new Rectangle(tileSize, tileSize, blackOne), x, y);
				} else if (checkForEvenNumber(y)) {
					field.add(new Rectangle(tileSize, tileSize, blackOne), x, y);
				} else if (!checkForEvenNumber(x)) {
					field.add(new Rectangle(tileSize, tileSize, blackTwo), x, y);
				} else if (!checkForEvenNumber(y)) {
					field.add(new Rectangle(tileSize, tileSize, blackOne), x, y);
				}
			}
		}
	}

	// Checks for even numbers. If the leftover equals 0, return true.
	private boolean checkForEvenNumber(int number) {
		if ((number % 2) == 0) {
			return true;
		} else {
			return false;
		}
	}
}

package Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import Model.Direction;
import Model.Game;
import Model.Snake;
import View.DrawPane;
import View.GameOverScene;
import View.GameScene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
	// instance variables
	private GameScene scene;
	private GameOverScene gameOverScene;
	private DrawPane drawPane;
	private Game game;
	private Snake snake;
	private Timeline snakeMovementTimeline, snakeSpeed, timerTimeline, createSpots, growSnake;
	private Direction direction;
	private int speed;
	private Stage stage;
	private Date newTime, oldTime, time;
	private DateFormat formatter;

	// constructor
	public Controller(Stage stage) {
		// creates and assigns objects
		this.stage = stage;
		game = new Game();
		this.snake = game.returnSnake();
		drawPane = new DrawPane(snake);
		scene = new GameScene(this);
		gameOverScene = new GameOverScene();
		oldTime = new Date();

		// sets default values
		direction = Direction.RIGHT;
		speed = 1;
		formatter = new SimpleDateFormat("mm:ss.SSS");

		// sets the stage
		stage.setTitle("PROG4 ASS Snake - Kenneth Gerrits");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}

	// this methods gets called, once the user clicks the "Start" button in the
	// DashBoard. Sets all Timelines and calls associated method attached.
	public void run(boolean playing) {
		if (playing) {
			snakeMovementTimeline = new Timeline();
			snakeMovementTimeline.setCycleCount(Animation.INDEFINITE);
			snakeMovementTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> handleSnakeMovement()));
			snakeMovementTimeline.play();

			snakeSpeed = new Timeline();
			snakeSpeed.setCycleCount(Animation.INDEFINITE);
			snakeSpeed.getKeyFrames().add(new KeyFrame(Duration.millis(3000), e -> handleSnakeSpeed()));
			snakeSpeed.play();

			timerTimeline = new Timeline();
			timerTimeline.setCycleCount(Animation.INDEFINITE);
			timerTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(1), e -> runTimer()));
			timerTimeline.play();

			createSpots = new Timeline();
			createSpots.setCycleCount(Animation.INDEFINITE);
			createSpots.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> createSpots()));
			createSpots.play();

			growSnake = new Timeline();
			growSnake.setCycleCount(Animation.INDEFINITE);
			growSnake.getKeyFrames().add(new KeyFrame(Duration.millis(3000), e -> growSnake()));
			growSnake.play();

		} else if (!playing) {
			// game is paused.
			stopAllTimelines();
		}
	}

	// handles the movement of the snake.
	private void handleSnakeMovement() {
		// checks if the snake hits itself or a spot.
		game.checkUnitCollision();

		// sets the direction of the snake
		if (direction == Direction.UP) {
			snake.moveUp();
		} else if (direction == Direction.RIGHT) {
			snake.moveRight();
		} else if (direction == Direction.DOWN) {
			snake.moveDown();
		} else if (direction == Direction.LEFT) {
			snake.moveLeft();
		}
	}

	// increases the playing speed of the snake while also adjusting the
	// speedSlider.
	private void handleSnakeSpeed() {
		if (speed < 12) {
			speed++;
		}
		snakeMovementTimeline.setRate(speed);
		scene.setSlider(speed);
	}

	// keeps track of the time while the game is running, also checks if the user
	// crashed and sets the time.
	private void runTimer() {
		System.out.println("run");
		newTime = new Date();
		time = new Date(newTime.getTime() - oldTime.getTime());
		scene.setLabelText(formatter.format(time));

		// if the snake crashed, stop the timer and send the user to the gameOverScene.
		if (game.isGameOver()) {
			stopAllTimelines();
			setGameOverScreen();
			gameOverScene.setGameOverLabel(formatter.format(time));
		}
	}

	// stops all timelines, triggers
	private void stopAllTimelines() {
		System.out.println("pause");
		snakeMovementTimeline.stop();
		snakeSpeed.stop();
		createSpots.stop();
		growSnake.stop();
		timerTimeline.stop();
	}
	
	// creates random spots on the map.
	private void createSpots() {
		game.createSpots();
		getSpots();
	}

	// makes the snake grow.
	private void growSnake() {
		game.addBodyPart();
	}

	// returns the drawPane.
	public DrawPane getDrawpane() {
		return drawPane;
	}

	// sets the direction of the snake.
	public void setDirection(Direction dir) {
		this.direction = dir;
	}

	// gets the spots.
	public void getSpots() {
		drawPane.setSpots(game.getSpots());
	}

	// sets gameOverScene.
	private void setGameOverScreen() {
		stage.setScene(gameOverScene);
	}
}

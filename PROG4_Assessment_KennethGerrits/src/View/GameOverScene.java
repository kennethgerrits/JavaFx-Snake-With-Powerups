package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverScene extends Scene {
	// constants
	public final static int WIDTH = 760;
	public final static int HEIGHT = 600;
	private final Color BACKGROUNDCOLOR = Color.RED;
	private final Color textColor = Color.BLACK;
	private final Color timeColor = Color.WHITE;
	private final Font FONT = Font.font(50);
	private final String gameOverText = "Game Over"; 

	// instance variables
	private Label gameOver;
	private Label playtimeLabel;
	private VBox labelAlignment;

	// constructor
	public GameOverScene() {
		super(new Pane(), WIDTH, HEIGHT);
		labelAlignment = new VBox();
		createLabels();
		setRoot(labelAlignment);
		labelAlignment.setBackground(new Background(new BackgroundFill(BACKGROUNDCOLOR, null, null)));
		labelAlignment.getChildren().addAll(gameOver, playtimeLabel);
		labelAlignment.setAlignment(Pos.CENTER);
	}

	// creates and sets the labels.
	private void createLabels() {
		gameOver = new Label(gameOverText);
		gameOver.setFont(FONT);
		gameOver.setTextFill(textColor);

		playtimeLabel = new Label();
		playtimeLabel.setFont(FONT);
		playtimeLabel.setTextFill(timeColor);
	}

	// sets the timerLabel.
	public void setGameOverLabel(String text) {
		playtimeLabel.setText(text);
	}
}

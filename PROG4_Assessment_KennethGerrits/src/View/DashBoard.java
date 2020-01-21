package View;

import Controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DashBoard extends HBox {
	// constants
	private final int buttonWidth = 100;
	private final int buttonHeight = 30;
	private final int height = 40;
	private final int spacing = 30;
	private final int sliderMinValue = 1;
	private final int sliderMaxValue = 12;
	private final int sliderTick = 1;
	private final int sliderWidth = 200;
	private final int labelWidth = 100;
	private final int labelHeight = 30;
	private final Color UIBackgroundColor = Color.LIGHTGRAY;
	private final Color backgroundColor = Color.DARKGRAY;
	private final String startButtonText = "Start";
	private final String pauseButtonText = "Pause";
	private final String exitButtonText = "Exit";
	private final String labelStartText = "00:00.000";

	// instance variables
	private Button pauseButton, exitButton;
	private Slider speedSlider;
	private Label playtimeLabel;
	private Controller controller;

	// constructor
	public DashBoard(Controller controller) {
		this.controller = controller;
		setButtons();
		setSliderProperties();
		setTimeLabel();

		this.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
		this.setPrefHeight(height);
		this.getChildren().addAll(pauseButton, exitButton, speedSlider, playtimeLabel);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(spacing);
	}

	// sets the buttons.
	private void setButtons() {
		// sets start and pause button
		pauseButton = new Button(startButtonText);
		pauseButton.setMinSize(buttonWidth, buttonHeight);
		pauseButton.setOnAction(e -> handleStartPauseButton());
		pauseButton.setFocusTraversable(false);

		// sets exit button
		exitButton = new Button(exitButtonText);
		exitButton.setMinSize(buttonWidth, buttonHeight);
		exitButton.setOnAction(e -> handleExitButton());
		exitButton.setFocusTraversable(false);
	}

	// closes the application when clicked on the "Exit" button.
	private void handleExitButton() {
		Platform.exit();
	}

	// switches the button text between Start and Pause. Tells the controller what
	// to do in which state.
	private void handleStartPauseButton() {
		boolean playing = false;
		if (pauseButton.getText().equals(startButtonText)) {
			// do start
			playing = true;
			controller.run(playing);
			pauseButton.setText(pauseButtonText);
		} else {
			// do pause
			playing = false;
			controller.run(playing);
			pauseButton.setText(startButtonText);
		}
	}

	// sets the slider.
	private void setSliderProperties() {
		speedSlider = new Slider();
		speedSlider.setMin(sliderMinValue);
		speedSlider.setMax(sliderMaxValue);
		speedSlider.setShowTickLabels(true);
		speedSlider.setShowTickMarks(true);
		speedSlider.setMajorTickUnit(sliderTick);
		speedSlider.setMinorTickCount(sliderTick);
		speedSlider.setBlockIncrement(sliderTick);
		speedSlider.setPrefWidth(sliderWidth);
		speedSlider.setMouseTransparent(true);
		speedSlider.setBackground(new Background(new BackgroundFill(UIBackgroundColor, null, null)));
		speedSlider.setFocusTraversable(false);
	}

	// sets the timerLabel.
	private void setTimeLabel() {
		playtimeLabel = new Label(labelStartText);
		playtimeLabel.setBackground(new Background(new BackgroundFill(UIBackgroundColor, null, null)));
		playtimeLabel.setMinSize(labelWidth, labelHeight);
		playtimeLabel.setAlignment(Pos.CENTER);
	}

	// sets the timerLabelText.
	public void setLabelText(String text) {
		playtimeLabel.setText(text);
	}

	// sets the speedSlider value.
	public void setSlider(double speed) {
		speedSlider.setValue((int) speed);
	}
}

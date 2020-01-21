package View;

import Controller.Controller;
import Model.Direction;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
	// constants
	public final static int WIDTH = 760;
	public final static int HEIGHT = 640;

	// instance variables
	private DrawPane drawPane;
	private DashBoard dashBoard;
	private BorderPane layout;

	// constructor
	public GameScene(Controller controller) {
		super(new Pane(), WIDTH, HEIGHT);
		drawPane = controller.getDrawpane();
		dashBoard = new DashBoard(controller);
		layout = new BorderPane();

		// reads input control and assigns direction value in the Controller.
		this.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if (key.getCode() == KeyCode.UP) {
				controller.setDirection(Direction.UP);
			} else if (key.getCode() == KeyCode.RIGHT) {
				controller.setDirection(Direction.RIGHT);
			} else if (key.getCode() == KeyCode.DOWN) {
				controller.setDirection(Direction.DOWN);
			} else if (key.getCode() == KeyCode.LEFT) {
				controller.setDirection(Direction.LEFT);
			}
		});

		setRoot(layout);
		layout.setBottom(dashBoard);
		layout.setCenter(drawPane);
	}

	// sets the playtimeLabel in DashBoard.
	public void setLabelText(String text) {
		dashBoard.setLabelText(text);
	}

	// sets the sliderValue in DashBoard.
	public void setSlider(double speed) {
		dashBoard.setSlider(speed);
	}
}

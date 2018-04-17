package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartScene {
	private Scene scene;

	public StartScene(Scene scene) {
		this.scene = scene;
	}
	public StartScene() {
	}
	public Scene getScene() {
		Label label = new Label("START");
		label.setFont(new Font(60));

		Label infoLabel = new Label("PRESS ENTER");
		infoLabel.setFont(new Font(40));

		VBox vBox = new VBox();
		vBox.getChildren().addAll(label, infoLabel);
		vBox.setAlignment(Pos.CENTER);

		scene = new Scene(vBox);
		scene.setOnKeyPressed(event -> keyEvent(event));
		return scene;
	}
	public void setStart() {
		Label label = new Label("START");
		label.setFont(new Font(60));

		Label infoLabel = new Label("PRESS ENTER");
		infoLabel.setFont(new Font(40));

		VBox vBox = new VBox();
		vBox.getChildren().addAll(label, infoLabel);
		vBox.setAlignment(Pos.CENTER);

		scene.setRoot(vBox);
		scene.setOnKeyPressed(event -> keyEvent(event));
	}

	public void keyEvent(KeyEvent event) {
		String s = event.getCode().toString();
		if(s.equals("ENTER")) {
			scene.setOnKeyPressed(null);
			GameScene gs = new GameScene(scene);
			gs.startGameScene();
		}
	}
}

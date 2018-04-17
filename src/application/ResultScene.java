package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ResultScene {
	private Scene scene;
	private Label timeLabel;

	public ResultScene(Scene scene, Label timeLabel) {
		this.scene = scene;
		this.timeLabel = timeLabel;
	}

	public void startResult() {
		Label label = new Label("RESULT");
		label.setFont(new Font(60));
		Label infoLabel = new Label("PRESS ENTER");
		infoLabel.setFont(new Font(40));
		Label label1 = new Label("残り時間: ");
		label1.setFont(new Font(40));
		timeLabel.setFont(new Font(40));
		timeLabel.setTextFill(Color.BLUE);
		HBox hBox = new HBox();
		hBox.getChildren().addAll(label1, timeLabel);
		hBox.setAlignment(Pos.CENTER);
		VBox vBox = new VBox();
		vBox.getChildren().addAll(label, hBox, infoLabel);
		vBox.setAlignment(Pos.CENTER);
		scene.setRoot(vBox);
		scene.setOnKeyPressed(event -> keyEvent(event));
	}

	public void keyEvent(KeyEvent event) {
		String s = event.getCode().toString();
		if(s.equals("ENTER")) {
			scene.setOnKeyPressed(null);
			StartScene sc = new StartScene(scene);
			sc.setStart();
		}
	}
}

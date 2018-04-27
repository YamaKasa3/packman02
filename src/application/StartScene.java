package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartScene {
	private Scene scene;
	private Label label1;
	private Label label2;
	private Border border1;
	public StartScene(Scene scene) {
		this.scene = scene;
	}
	public StartScene() {
	}
	public Scene getScene() {
		label1 = new Label("START");
		label1.setFont(new Font(60));
		BorderStroke bs = new BorderStroke(Color.BLUE,
		        BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(5));
		border1 = new Border(bs);

		label1.setBorder(border1);

		label2 = new Label("HIGH SCORE");
		label2.setFont(new Font(60));

		Label infoLabel = new Label("PRESS ENTER");
		infoLabel.setFont(new Font(40));

		VBox vBox = new VBox();
		vBox.getChildren().addAll(label1, label2, infoLabel);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10.0);

		scene = new Scene(vBox);
		scene.setOnKeyPressed(event -> keyEvent(event));
		return scene;
	}
	public void setStart() {
//		Label label = new Label("START");
//		label.setFont(new Font(60));
//
//		Label infoLabel = new Label("PRESS ENTER");
//		infoLabel.setFont(new Font(40));
//
//		VBox vBox = new VBox();
//		vBox.getChildren().addAll(label, infoLabel);
//		vBox.setAlignment(Pos.CENTER);
//
//		scene.setRoot(vBox);
//		scene.setOnKeyPressed(event -> keyEvent(event));
		label1 = new Label("START");
		label1.setFont(new Font(60));
		BorderStroke bs = new BorderStroke(Color.BLUE,
		        BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(5));
		border1 = new Border(bs);

		label1.setBorder(border1);

		label2 = new Label("HIGH SCORE");
		label2.setFont(new Font(60));

		Label infoLabel = new Label("PRESS ENTER");
		infoLabel.setFont(new Font(40));

		VBox vBox = new VBox();
		vBox.getChildren().addAll(label1, label2, infoLabel);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10.0);

		scene.setRoot(vBox);
		scene.setOnKeyPressed(event -> keyEvent(event));
	}

	public void keyEvent(KeyEvent event) {
		String s = event.getCode().toString();
		if(s.equals("DOWN")) {
			if(label1.getBorder() == null) {
				label1.setBorder(border1);
				label2.setBorder(null);
			}else {
				label2.setBorder(border1);
				label1.setBorder(null);
			}
		}else if(s.equals("UP")) {
			if(label1.getBorder() == null) {
				label1.setBorder(border1);
				label2.setBorder(null);
			}else {
				label2.setBorder(border1);
				label1.setBorder(null);
			}
		}
		if(s.equals("ENTER") && label1.getBorder() != null) {
			scene.setOnKeyPressed(null);
			GameScene gs = new GameScene(scene);
			gs.startGameScene();
		}
		if(s.equals("ENTER") && label2.getBorder() != null) {
			scene.setOnKeyPressed(null);
			HighScoreScene hss = new HighScoreScene(scene);
			hss.startHighScoreScene();
		}
	}
}

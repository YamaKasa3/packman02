package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class GameScene {
	private Scene scene;
	public GameScene(Scene scene) {
		this.scene = scene;
	}
	public void startGameScene() {
		Player player = new Player();
		ImageView playerImgView = new ImageView(player.getplayerImg()[0]);
		playerImgView.setLayoutX(32);
		playerImgView.setLayoutY(32);

		int timeLimit = 50;

		Map map = new Map();
		ImageView mapImgView = new ImageView(map.getMapImg());

		Label label1 = new Label("得点: ");
		label1.setFont(new Font(20));
		Label pointLabel = new Label();
		pointLabel.setText("0");
		pointLabel.setFont(new Font(20));
		HBox hBox = new HBox();
		hBox.getChildren().addAll(label1, pointLabel);

		Label label2 = new Label("時間: ");
		label2.setFont(new Font(20));
		Label timeLabel = new Label(Integer.toString(timeLimit));
		timeLabel.setFont(new Font(20));

		HBox hBox2 = new HBox();
		hBox2.getChildren().addAll(label2, timeLabel);

		Label label3 = new Label("接触回数: ");
		label3.setFont(new Font(20));
		Label damageLabel = new Label("0");
		damageLabel.setFont(new Font(20));

		HBox hBox3 = new HBox();
		hBox3.getChildren().addAll(label3, damageLabel);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(hBox, hBox2, hBox3);
		vBox.setLayoutX(490);
		vBox.setLayoutY(20);

		Monster monster = new Monster();
		ImageView monsImgView = new ImageView(
				monster.getMonsterImage()[0]);
		monsImgView.setLayoutX(32*13);
		monsImgView.setLayoutY(32*13);
		monster.setTimer(timeLimit);
		monster.setPlayerImgView(playerImgView);
		monster.setMapData(map.getMapData());
		monster.setMonsterImgView(monsImgView);
		monster.setPointLabel(pointLabel);
		monster.setDamageLabel(damageLabel);

		Group pointGroup = map.getCircle(map.getMapData());

		Group root = new Group();
		root.getChildren().addAll(mapImgView, pointGroup,
				playerImgView, vBox, monsImgView);
		scene.setRoot(root);

		Timer timer = new Timer(timeLimit);
		timer.setTimeLabel(timeLabel);
		timer.setTimer(scene);
		MoveEvent moveEvent = new MoveEvent();

		scene.setOnKeyPressed(event ->
			moveEvent.setMoveEvent(event, playerImgView, pointGroup,
						pointLabel, timer, damageLabel, monsImgView, scene));
		scene.setOnKeyReleased(event ->
		moveEvent.setMoveEvent(event, playerImgView, pointGroup,
				pointLabel, timer, damageLabel, monsImgView, scene));
	}
}



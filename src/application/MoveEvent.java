package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class MoveEvent {
	private KeyEvent event;
	private int[][] mapData = new int[15][15];
	private Image []playerImg = new Image[2];
	private ImageView playerImgView;
	private Group pointGroup;
	private Scene scene;
	private Label pointLabel;
	private Timer timer;
	private int []keyState = {0, 0, 0, 0};
	public MoveEvent() {
		Map map = new Map();
		Player player = new Player();
		this.mapData = map.getMapData();
		this.playerImg = player.getplayerImg();
	}
	public void setMoveEvent(KeyEvent event, ImageView playerImgView,
			Group pointGroup,Label pointLabel,
			Timer timer, Scene scene) {
		this.event = event;
		this.playerImgView = playerImgView;
		this.pointGroup = pointGroup;
		this.pointLabel = pointLabel;
		this.timer = timer;
		this.scene = scene;

		playerMove();
		pointEvent();
	}
	public void playerMove() {
		String s = "";
		if(event.getEventType() == KeyEvent.KEY_PRESSED) {
			s = event.getCode().toString();
			if(s.equals("DOWN")) {
				keyState[0] = 1;
			}else if(s.equals("UP")) {
				keyState[1] = 1;
			}else if(s.equals("RIGHT")) {
				keyState[2] = 1;
			}else if(s.equals("LEFT")) {
				keyState[3] = 1;
			}
		}else if(event.getEventType() == KeyEvent.KEY_RELEASED) {
			s = event.getCode().toString();
			if(s.equals("DOWN")) {
				keyState[0] = 0;
			}else if(s.equals("UP")) {
				keyState[1] = 0;
			}else if(s.equals("RIGHT")) {
				keyState[2] = 0;
			}else if(s.equals("LEFT")) {
				keyState[3] = 0;
			}
		}
		int i = keyState[0] - keyState[1];
		int j = keyState[2] - keyState[3];
		if(j == 1) {
			playerImgView.setImage(playerImg[1]);
		}else if(j == -1) {
			playerImgView.setImage(playerImg[0]);
		}
		if(isCheckMove(i, j)) {
			playerImgView.setLayoutX(playerImgView.getLayoutX() + 32 * j);
			playerImgView.setLayoutY(playerImgView.getLayoutY() + 32 * i);
		}
	}
//		String s = event.getCode().toString();
//		if(s.equals("DOWN")) {
//			if( isCheckMove(1, 0)) {
//				playerImgView.setLayoutY(playerImgView.getLayoutY() + 32);
//			}
//		}else if(s.equals("UP")) {
//			if( isCheckMove(-1, 0)) {
//				playerImgView.setLayoutY(playerImgView.getLayoutY() - 32);
//			}
//		}else if(s.equals("LEFT")) {
//			playerImgView.setImage(playerImg[0]);
//			if( isCheckMove(0, -1)) {
//				playerImgView.setLayoutX(playerImgView.getLayoutX() - 32);
//			}
//		}else if(s.equals("RIGHT")) {
//			playerImgView.setImage(playerImg[1]);
//			if( isCheckMove(0, 1)) {
//				playerImgView.setLayoutX(playerImgView.getLayoutX() + 32);
//			}
//		}
//	}
	public boolean isCheckMove(int i, int j) {
		int col = (int)playerImgView.getLayoutX()/32;
		int row = (int)playerImgView.getLayoutY()/32;
		if(mapData[row + i][col + j] == 257) {
			return true;
		}else {
			return false;
		}
	}
	public void pointEvent() {
		int playerX = (int)playerImgView.getLayoutX();
		int playerY = (int)playerImgView.getLayoutY();
		int circleGroupSize = pointGroup.getChildren().size();
		for(int i = 0; i < circleGroupSize; i++) {
			int pointX = (int)pointGroup.getChildren().get(i).getLayoutX() - 16;
			int pointY = (int)pointGroup.getChildren().get(i).getLayoutY() - 16;
			if(playerX == pointX && playerY == pointY) {
				pointGroup.getChildren().remove(i);
				int point = Integer.parseInt(pointLabel.getText().toString());
				point = point + 1;
				pointLabel.setText(String.valueOf(point));
				if(point == 50) {
					timer.stopTimer();
					Label timeLabel = timer.getTimeLabel();
					scene.setOnKeyPressed(null);
					ResultScene rs = new ResultScene(scene, timeLabel);
					rs.startResult();
				}
				break;
			}
		}
	}
}

package application;

import java.awt.Point;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Queue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

public class Monster {
	private Image[] monsMoveImg = new Image[4];
	private Timeline timer;
	private ImageView playerImgView;
	private int[][] mapData;
	private ImageView monsImgView;
	private Label pointLabel;
	public Monster() {
		setMonsterImage();
	}

	public void setPointLabel(Label pointLabel) {
		this.pointLabel = pointLabel;
	}
	public void setMapData(int[][] mapData) {
		this.mapData = mapData;
	}
	public void setPlayerImgView(ImageView playerImgView) {
		this.playerImgView = playerImgView;
	}
	public void setMonsterImgView(ImageView monsImgView) {
		this.monsImgView = monsImgView;
	}
	public void setMonsterImage() {
		Path monsterPath = Paths.get("image/pipo-mons.png");
		Image monsterImg = new Image(monsterPath.toUri().toString());
		int width = 32;
		int height = 32;
		for(int i = 0; i < 4; i++) {
			monsMoveImg[i] = new WritableImage(monsterImg.getPixelReader()
					,32, height * i, width, height);
		}
	}
	public Image []getMonsterImage() {
		return monsMoveImg;
	}

	public void setTimer(int timeLimit) {
		int []col = {1, -1, 0, 0, 0};
		int []row = {0, 0, 1, -1, 0};
		timer = new Timeline(new KeyFrame(Duration.seconds(0.5),
				new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event) {

		    	int playerCol = (int)playerImgView.getLayoutX()/32;
				int playerRow = (int)playerImgView.getLayoutY()/32;
				int monsCol = (int)monsImgView.getLayoutX()/32;
				int monsRow = (int)monsImgView.getLayoutY()/32;
//				int length;
//				int index = 0;
//				int minLength = 10000;
//				for(int i = 0; i < 5; i++) {
//					if(isCheckMove(row[i], col[i])) {
//						length = (playerCol - (monsCol + col[i])) * (playerCol - (monsCol + col[i]))
//								+ (playerRow - (monsRow + row[i])) * (playerRow - (monsRow + row[i]));
//						if(length <= minLength) {
//							minLength = length;
//							index = i;
//						}
//					}
//				}
		    	int index = getIndex(playerCol, playerRow, monsCol, monsRow);
				int point = Integer.parseInt(pointLabel.getText().toString());
				if(point == 50) {
					timerStop();
				}
				if(point != 50) {
					monsImgView.setLayoutX(monsImgView.getLayoutX() + 32 * col[index]);
					monsImgView.setLayoutY(monsImgView.getLayoutY() + 32 * row[index]);
				}

		    }
		}));
		timer.setCycleCount(timeLimit * 2);
		timer.play();
	}
	public void timerStop() {
		timer.stop();
	}
	public int getIndex(int playerCol, int playerRow,
			int monsCol, int monsRow) {
		int [][]state = new int[15][15];
		int [][]d = new int[15][15];
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				state[i][j] = 0;
				d[i][j] = 0;
			}
		}
		boolean flag = true;
		Queue<Point> queue = new ArrayDeque<Point>();
		Point start = new Point(monsCol, monsRow);
		queue.add(start);

		int []dx = {1, -1, 0, 0};
		int []dy = {0, 0,  1, -1};
		int cnt = 0;
		int step = 0;
		if(playerCol == monsCol && playerRow == monsRow) {
			flag = false;
			return 4;
		}
		while(flag) {
			cnt++;
			Point temp = queue.poll();
			int tempX = (int)temp.getX();
			int tempY = (int)temp.getY();
			for(int i = 0; i < 4; i++) {
				int nextX = tempX + dx[i];
				int nextY = tempY + dy[i];
				if(nextX >= 0 && nextX < 15 && nextY >= 0 && nextY < 15) {
					if(mapData[nextY][nextX] == 257
							&& state[nextY][nextX] == 0) {
						state[nextY][nextX] = 1;
						queue.add(new Point(nextX, nextY));
						d[nextY][nextX] = d[tempY][tempX] + 1;
						if(nextX == playerCol && nextY == playerRow) {
							flag = false;
							step = d[nextY][nextX];
							break;
						}
					}
				}
			}
			if(cnt > 10000) {
				System.out.println("error");
				break;
			}
		}
		int tempR = playerRow;
		int tempC = playerCol;
		int []index = new int[step];
		for(int i = 0; i < step - 1; i++) {
			for(int j = 0; j < 4; j++) {
				int nextR = tempR - dy[j];
				int nextC = tempC - dx[j];
				if(d[nextR][nextC] == step - i - 1) {
					index[i] = j;
					tempR = nextR;
					tempC = nextC;
					break;
				}
			}
		}
		for(int i = 0; i < 4; i++) {
			int nextR = tempR - dy[i];
			int nextC = tempC - dx[i];
			if(nextR == monsRow && nextC == monsCol) {
				index[step - 1] = i;
			}
		}
		return index[step - 1];
	}
	public boolean isCheckMove(int i, int j) {
		int col = (int)monsImgView.getLayoutX()/32;
		int row = (int)monsImgView.getLayoutY()/32;
		if(mapData[row + i][col + j] == 257) {
			return true;
		}else {
			return false;
		}
	}
}


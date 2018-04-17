package application;

import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.image.Image;

public class Player {
	public Image []getplayerImg() {
		Image []playerImg = new Image[2];
		Path playerPath1 = Paths.get("image/chara1.png");
		Image playerImg1 = new Image(playerPath1.toUri().toString());

		Path playerPath2 = Paths.get("image/chara2.png");
		Image playerImg2 = new Image(playerPath2.toUri().toString());
		playerImg[0] = playerImg1;
		playerImg[1] = playerImg2;
		return playerImg;
	}
}

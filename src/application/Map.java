package application;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Map {

	public Image getMapImg() {
		Path mapPath = Paths.get("image/field.png");
		Image mapImg = new Image(mapPath.toUri().toString());
		return mapImg;
	}
	public int[][] getMapData() {
		int [][]mapData = new int[15][15];
		Path path = Paths.get("maptext/field.txt");
		File file = path.toFile();
		try {
			FileReader filereader = new FileReader(file);
			BufferedReader br = new BufferedReader(filereader);
			String str;
			int i = 0;
			while((str = br.readLine()) != null) {
				String[] array = str.split(",");
				for(int j = 0; j < array.length; j++) {
					mapData[i][j] = Integer.parseInt(array[j]);
				}
				i++;
			}
		br.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}catch (IOException e) {
			System.out.println(e);
		}
		return mapData;
	}

	public Group getCircle(int[][] mapData) {
		Group circleGroup = new Group();
		ArrayList<Point> point = new ArrayList<Point>();
		// ポイントを置ける座標を取得し、番号を振る
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				if(mapData[i][j] == 257) {
					point.add(new Point(j, i));
				}
			}
		}
		Collections.shuffle(point);

		// 50個のポイントを配置する。
		Circle []circle = new Circle[50];
		for(int i = 0; i < 50; i++) {
			circle[i] = new Circle(5);
			circle[i].setFill(Color.YELLOW);
			circle[i].setLayoutX(point.get(i).x * 32 + 16);
			circle[i].setLayoutY(point.get(i).y * 32 + 16);
			circleGroup.getChildren().add(circle[i]);
		}
		return circleGroup;
	}
}

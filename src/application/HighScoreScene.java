package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class HighScoreScene {
	private Scene scene;
	public HighScoreScene(Scene scene) {
		this.scene = scene;
	}
	public void startHighScoreScene() {
		int []score = getScore();
		VBox vBox = new VBox();

		Label label = new Label("ハイスコア");
		label.setFont(new Font(30));
		vBox.getChildren().add(label);

		Label []scoreLabel = new Label[10];
		for(int i = 0; i < 10; i++) {
			scoreLabel[i] = new Label((i + 1) + "位: " +Integer.toString(score[9 - i]));
			scoreLabel[i].setFont(new Font(25));
			vBox.getChildren().add(scoreLabel[i]);
		}
		vBox.setAlignment(Pos.CENTER);
		scene.setRoot(vBox);
		scene.setOnKeyPressed(event -> keyEvent(event));
	}
	public void saveScore(Label timeLabel) {
		int []score = getScore();
		int temp = Integer.parseInt(timeLabel.getText());
		boolean flag = false;
		for(int i = 0; i < 10; i++) {
			if(temp > score[i]) {
				score[i] = temp;
				flag = true;
				break;
			}
		}
		if(flag) {
			try{
				Path path = Paths.get("highscore/data.txt");
				File file = path.toFile();

				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				for(int i = 0; i < 10; i++) {
					bw.write(Integer.toString(score[i]));
					bw.newLine();
				}
				bw.close();
				Arrays.sort(score);

			}catch(FileNotFoundException e){
				  System.out.println(e);
			}catch(IOException e){
				  System.out.println(e);
			}
		}
	}
	public int[] getScore() {
		int score[] = new int[10];
		try{
			Path path = Paths.get("highscore/data.txt");
			File file = path.toFile();

			BufferedReader br = new BufferedReader(new FileReader(file));

			for(int i = 0; i < 10; i++) {
				String str = br.readLine();
				score[i] = Integer.parseInt(str);
			}
			br.close();
			Arrays.sort(score);

		}catch(FileNotFoundException e){
			  System.out.println(e);
		}catch(IOException e){
			  System.out.println(e);
		}
		return score;
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

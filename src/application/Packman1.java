package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Packman1 extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception{
		stage.setTitle("ThePackman");
		stage.setWidth(600);
		stage.setHeight(518);
		StartScene sc = new StartScene();
		Scene scene = sc.getScene();
		stage.setScene(scene);
		stage.show();
	}
}


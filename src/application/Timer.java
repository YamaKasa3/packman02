package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer {
	private int timeLimit;
	private Timeline timer;
	private Label timeLabel;
	public Timer(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public void setTimeLabel(Label timeLabel) {
		this.timeLabel = timeLabel;
	}
	public Label getTimeLabel() {
		return timeLabel;
	}
	public void setTimer(Scene scene) {
		timer = new Timeline(new KeyFrame(Duration.seconds(1),
				new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event) {
		    	timeLabel.setText(String.valueOf(
		    			Integer.parseInt(timeLabel.getText()) - 1));
		        if(Integer.parseInt(timeLabel.getText()) == 0) {
		        	scene.setOnKeyPressed(null);
		        }
		    }
		}));
		timer.setCycleCount(timeLimit);
		timer.play();
	}
	public void stopTimer() {
		timer.stop();
	}
}


package ru.home.Weather.frontend;

import java.net.URI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class RunApplication extends Application
{
    public static void main( String[] args )
    {
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/seamples/headSeample.fxml"));
		primaryStage.setScene(new Scene(fxmlLoader.load()));
		primaryStage.show();
	}
}

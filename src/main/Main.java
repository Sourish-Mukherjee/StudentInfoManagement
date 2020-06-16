package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent pr = FXMLLoader.load(getClass().getResource("/fxmlPackage/LoginFXML.fxml"));
        Scene scene = new Scene(pr);
        scene.getStylesheets().add(getClass().getResource("/cssPackage/loginPage.css").toExternalForm());
        primaryStage.setTitle("Login Portal!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }

}

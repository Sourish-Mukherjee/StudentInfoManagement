package main;

/*
    Author: Sourish Mukherjee
    Link: https://github.com/Sourish-Mukherjee/StudentInfoManagement
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // Start Function for the Project <--- Application Loads
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

    // Main Method for the entire project
    public static void main(String args[]) {
        launch(args);
    }

}

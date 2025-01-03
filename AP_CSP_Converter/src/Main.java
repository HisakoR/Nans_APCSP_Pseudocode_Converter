import gui.controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import scripts.config;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller.setStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("gui/main.fxml"));
        config conter = new config();
        conter.initialize();
        primaryStage.setTitle("Nan's APCSP Pseudocode Converter");
        if (conter.getLanguage() == 1) {
            primaryStage.setTitle("楠z的APCSP编码转换器");
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("gui/icon.png"));
        primaryStage.show();
    }
    //主要方法
    public static void main(String[] args) {
        launch(args);
    }
}
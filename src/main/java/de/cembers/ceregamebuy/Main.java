package de.cembers.ceregamebuy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Startpunkt des Programms. Keine anderen Aufgaben.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game-administration-layout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 750);
        stage.setTitle("Re:GameBuy");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
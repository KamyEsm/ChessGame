package com.example.chessgame;

import Map.MapLoader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MapLoader mapLoader =new MapLoader(stage);
        ChessController controller =new ChessController(mapLoader);
        mapLoader.setController(controller);
        mapLoader.loadMap();
    }

    public static void main(String[] args) {
        launch();
    }
}
package invaders;

import javafx.application.Application;
import javafx.stage.Stage;
import invaders.engine.GameEngine;
import invaders.engine.GameWindow;
import invaders.configuration.ConfigReader;

import java.util.Map;

public class App extends Application {
    private static ConfigReader config = new ConfigReader("src/main/resources/config.json");
    private static int screenWidth = config.getGameConfig().getWidth();
    private static int screenHeight = config.getGameConfig().getHeight();
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Map<String, String> params = getParameters().getNamed();

        // GameEngine model = new GameEngine("/resources/config.json");
        GameEngine model = new GameEngine();
        // Original width: 640, height: 400
        GameWindow window = new GameWindow(model, screenWidth, screenHeight);
        window.run();

        primaryStage.setTitle("Space Invaders");
        primaryStage.setScene(window.getScene());
        primaryStage.show();

        window.run();
    }

    public static ConfigReader getConfig() {
        return config;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }
}

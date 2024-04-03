package invaders.entities.state;

import javafx.scene.image.Image;
import java.io.File;

public class RedState implements BunkerState {
    @Override
    public Image setImage(double width, double height) {
        return new Image(new File("src/main/resources/bunker-red.png").toURI().toString(), width, height, true, true);
    }
}
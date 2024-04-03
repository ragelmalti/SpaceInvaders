package invaders.entities.state;

import javafx.scene.image.Image;

public interface BunkerState {
    // This state interface will be used to change the image of the bunker when it takes damage.

    public Image setImage(double width, double height); // Used to change the image of the bunker.
}

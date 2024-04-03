package invaders.entities.builder;

import invaders.physics.Vector2D;
import invaders.entities.Bunker;
import invaders.entities.state.*;

public class BunkerBuilder implements BBuilderInterface {
    private Bunker bunker;
    
    /* Initialise the builder and start a new build */
    public BunkerBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.bunker = new Bunker();
    }

    @Override
    public void setPosition(Vector2D position) {
        this.bunker.setPosition(position);
    }

    @Override
    public void setWidth(double width) {
        this.bunker.setWidth(width);
    }

    @Override
    public void setHeight(double height) {
        this.bunker.setHeight(height);
    }

    @Override 
    public void setState(BunkerState state) {
        this.bunker.setState(state);
    }

    @Override
    public Bunker finaliseBuild() {
        this.bunker.setImage();
        Bunker newBunker = this.bunker;
        this.reset();
        return newBunker;

    }
}

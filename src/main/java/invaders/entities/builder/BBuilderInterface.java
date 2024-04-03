package invaders.entities.builder;

import invaders.physics.Vector2D;
import invaders.entities.Bunker;
import invaders.entities.state.*;

public interface BBuilderInterface {
    //private Bunker bunker;
    //private Vector2D bunkerPosition;
    public void reset();
    public void setPosition(Vector2D position);
    public void setWidth(double width);
    public void setHeight(double height);
    public void setState(BunkerState state);
    public Bunker finaliseBuild(); 

}

package invaders.entities.builder;

import invaders.physics.Vector2D;
import invaders.entities.Enemy;
import invaders.entities.strategy.*;

public interface EBuilderInterface {
    //private Vector2D bunkerPosition;
    public void reset();
    public void setPosition(Vector2D position);
    public void setStrategy(ProjectileStrategy strategy);
    public Enemy finaliseBuild(); 

}

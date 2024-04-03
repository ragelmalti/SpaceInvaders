package invaders.entities.factory;

import invaders.entities.Projectile;
import invaders.physics.Vector2D;
import invaders.entities.strategy.*;

public interface ProjectileCreator {
    public Projectile createProjectile(Vector2D position, ProjectileStrategy strategy);
}

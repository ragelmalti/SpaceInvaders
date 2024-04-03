package invaders.entities.factory;

import invaders.entities.strategy.*;
import invaders.physics.Vector2D;
import invaders.entities.Projectile;

public class ConcreateProjectileCreator implements ProjectileCreator {
    @Override
    public Projectile createProjectile(Vector2D position, ProjectileStrategy strategy) {
        return new Projectile(position, strategy);
    }
}

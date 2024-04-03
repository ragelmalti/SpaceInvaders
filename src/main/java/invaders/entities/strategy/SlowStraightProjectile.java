package invaders.entities.strategy;
import invaders.entities.Projectile;
import invaders.physics.Vector2D;

public class SlowStraightProjectile implements ProjectileStrategy {
    @Override
    // As this projectile strategy is for an enemy, it will not move up. 
    public void up(Projectile projectile) {
        return;
    }
    @Override
    public void down(Projectile projectile) {
        Vector2D position = projectile.getPosition();
        projectile.getPosition().setY(position.getY() + 2);
    }

    @Override
    public void move(Projectile projectile) {
        this.down(projectile);
    }
}

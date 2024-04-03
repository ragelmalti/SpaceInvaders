package invaders.entities.strategy;
import invaders.entities.Projectile;
import invaders.physics.Vector2D;

public class FastStraightProjectile implements ProjectileStrategy {
    @Override
    // As this projectile strategy is for an enemy, it will not move up. 
    public void up(Projectile projectile) {
        return;
    }
    @Override
    public void down(Projectile projectile) {
        Vector2D position = projectile.getPosition();
        projectile.getPosition().setY(position.getY() + 4); // Changed speed to 4, because fast lol
    }

    @Override
    public void move(Projectile projectile) {
        this.down(projectile);
    }
}
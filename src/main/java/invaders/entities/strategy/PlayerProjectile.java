package invaders.entities.strategy;

import invaders.physics.Vector2D;
import invaders.entities.Projectile;

public class PlayerProjectile implements ProjectileStrategy {
    @Override
    // As this projectile strategy is for a player, it will not move down. 
    public void up(Projectile projectile) {
        Vector2D position = projectile.getPosition();
        projectile.getPosition().setY(position.getY() - 2);
    }
    @Override
    public void down(Projectile projectile) {
        return;
    }

    @Override
    public void move(Projectile projectile) {
        this.up(projectile);
    }
}

package invaders.entities.strategy;

import invaders.entities.Projectile;

public interface ProjectileStrategy {
    public void up(Projectile projectile);
    public void down(Projectile projectile);
    public void move(Projectile projectile);
}

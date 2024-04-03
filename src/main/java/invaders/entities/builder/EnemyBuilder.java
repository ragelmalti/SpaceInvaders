package invaders.entities.builder;

import invaders.physics.Vector2D;

import java.util.Random;

import invaders.entities.Enemy;
import invaders.entities.strategy.*;

public class EnemyBuilder implements EBuilderInterface {
    private Enemy enemy;
    
    /* Initialise the builder and start a new build */
    public EnemyBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.enemy = new Enemy();
    }

    @Override
    public void setPosition(Vector2D position) {
        this.enemy.setPosition(position);
    }

    @Override
    public void setStrategy(ProjectileStrategy strategy) {
        this.enemy.setStrategy(strategy);
    }

    @Override
    public Enemy finaliseBuild() {
        Enemy newEnemy = this.enemy;
        this.reset();
        return newEnemy;

    }
}

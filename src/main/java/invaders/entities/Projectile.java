package invaders.entities;

import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.strategy.*;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class Projectile implements Moveable, Damagable, Renderable, Collider {

    private final Vector2D position;
    private final Animator anim = null;
    private double health = 100;
    private boolean isDelete = false;
    // Original values: W: 25, H: 30
    private final double width = 20;
    private final double height = 25;
    private final Image image;
    private ProjectileStrategy strategy;

    public Projectile(Vector2D position, ProjectileStrategy strategy){
        this.image = new Image(new File("src/main/resources/projectile.png").toURI().toString(), width, height, true, true);
        this.position = position;
        this.strategy = strategy;
    }

    public void setStrategy(ProjectileStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void takeDamage(double amount) {
        this.health -= amount;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    public void move() {
        strategy.move(this);
    }

    @Override
    public void up() {
        //this.position.setY(this.position.getY() - 1);
        strategy.up(this);
    }

    @Override
    public void down() {
        strategy.down(this);
        //this.position.setY(this.position.getY() + 1);
    }

    @Override
    public void left() {
        return;
    }

    @Override
    public void right() {
        return;
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    @Override
    public void markForDeletion() {
        this.isDelete = true;
    }

    @Override
    public boolean getDeletion() {
        return this.isDelete;
    }

    @Override
    public void handleCollision(Collider external) {
        if(external instanceof Bunker) {
            this.markForDeletion();
        }
        else if(external instanceof Enemy) {
            this.markForDeletion();
        }
        else if(external instanceof Projectile) {
            this.markForDeletion();
        }
        else if(external instanceof Player) {
            this.markForDeletion();
        }
    }
}

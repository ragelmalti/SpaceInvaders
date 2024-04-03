package invaders.entities;

import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.MoveableSpeed;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.strategy.ProjectileStrategy;

import javafx.scene.image.Image;

import java.io.File;

public class Enemy implements Collider, MoveableSpeed, Damagable, Renderable {

    // Removed final from variables. Hopefully it doesn't break anything lol.
    private Vector2D position;
    private Animator anim = null;
    private double health = 3; // Health is 3, as the bunker can only take 3 hits.
    private boolean isDelete = false;
    private ProjectileStrategy strategy;

    private double width = 39;
    private double height = 24;
    private Image image;

    // I think you can have two initalisers in Java. Not sure lol.
    public Enemy() {
        // I scalled the image size by X5. Might change later.
        this.image = new Image(new File("src/main/resources/enemy.png").toURI().toString(), this.width, this.height, true, true);
    }

    @Override
    public void up(double speed) {
        return;
    }

    @Override
    public void down(double speed) {
        this.position.setY(this.position.getY() + speed);
    }

    @Override
    public void left(double speed) {
        this.position.setX(this.position.getX() - speed);
    }

    @Override
    public void right(double speed) {
        this.position.setX(this.position.getX() + speed);
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    @Override
    public void takeDamage(double ammount) {
        this.health -= ammount;
        if(!this.isAlive()) {
            this.markForDeletion();
        }
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
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
        if(external instanceof Projectile) {
            this.takeDamage(101);
            //System.out.println(getHealth());
        }
        else if(external instanceof Player) {
            this.takeDamage(101);
            //System.out.println(getHealth());
        }
    }

    public void setStrategy(ProjectileStrategy strategy) {
        this.strategy = strategy;
    }

    public ProjectileStrategy getStrategy() {
        return this.strategy;
    }
}

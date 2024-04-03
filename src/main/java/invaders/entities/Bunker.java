package invaders.entities;

import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.entities.state.*;

import javafx.scene.image.Image;

import java.io.File;

public class Bunker implements Damagable, Renderable, Collider {

    // Removed final from variables. Hopefully it doesn't break anything lol.
    private Vector2D position;
    private BunkerState state;
    private Animator anim = null;
    private double health = 4; // Health is 4, as the bunker can only take 3 hits.
    private boolean isDelete = false;

    private double width;
    private double height;
    private Image image;

    // I think you can have two initalisers in Java. Not sure lol.
    public Bunker() {
        //this.image = new Image(new File("src/main/resources/bunker.png").toURI().toString(), width, height, true, true);
    }
    
    public Bunker(Vector2D position, double width, double height){
        this.image = new Image(new File("src/main/resources/bunker.png").toURI().toString(), width, height, true, true);
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public void setState(BunkerState state) {
        this.state = state;
    }

    public void setImage() {
        //this.image = new Image(new File("src/main/resources/bunker.png").toURI().toString(), width, height, true, true);
        this.image = this.state.setImage(this.width, this.height);
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void takeDamage(double ammount) {
        this.health -= ammount;
        if(this.health == 2) {
            this.setState(new YellowState());
            this.setImage();
        }
        else if(this.health == 1) {
            this.setState(new RedState());
            this.setImage();
        }
        else if(this.health < 0) {
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
            this.takeDamage(1);
        }
        if(external instanceof Enemy) {
            this.takeDamage(5); // Enemy will completely remove the bunker from the game.
        }
    }

}

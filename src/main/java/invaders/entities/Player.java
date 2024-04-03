package invaders.entities;

import invaders.logic.Damagable;
import invaders.physics.Collider;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.rendering.Animator;
import invaders.rendering.Renderable;
import invaders.engine.GameEngine;

import javafx.scene.image.Image;

import java.io.File;

public class Player implements Collider, Moveable, Damagable, Renderable {

    private final Vector2D position;
    private final Animator anim = null;
    private double health = 4; // Health is 4, as the player can only take 3 hits.
    private boolean isDelete = false;

    private final double width = 25;
    private final double height = 30;
    private final Image image;

    public Player(Vector2D position){
        this.image = new Image(new File("src/main/resources/player.png").toURI().toString(), width, height, true, true);
        this.position = position;
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

    @Override
    public void up() {
        return;
    }

    @Override
    public void down() {
        return;
    }

    @Override
    public void left() {
        this.position.setX(this.position.getX() - 1);
    }

    @Override
    public void right() {
        this.position.setX(this.position.getX() + 1);
    }

    public void shoot(){
        // todo
        //GameEngine.getRenderables().add(projectile);
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
            this.takeDamage(0.5); // For some reason, projectiles run this function twice when colliding.
            System.out.println("Player lives: " + getHealth());
        }
        else if(external instanceof Enemy) {
            this.takeDamage(5); // Completely remove player, if colliding with enemy.
        }
    }
}

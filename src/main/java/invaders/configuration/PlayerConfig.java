package invaders.configuration;

public class PlayerConfig {
    private String colour;
    private int speed;
    private int lives;
    private double positionX;
    private double positionY;

    public PlayerConfig(String colour, int speed, int lives, double positionX, double positionY) {
        this.colour = colour;
        this.speed = speed;
        this.lives = lives;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getColour() {
        return this.colour;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getLives() {
        return this.lives;
    }

    public double getPositionX() {
        return this.positionX;
    }

    public double getPositionY() {
        return this.positionY;
    }
}

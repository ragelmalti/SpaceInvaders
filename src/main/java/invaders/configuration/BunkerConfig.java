package invaders.configuration;

public class BunkerConfig {
    private double positionX;
    private double positionY;
    private double sizeX;
    private double sizeY;

    public BunkerConfig(double positionX, double positionY, double sizeX, double sizeY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public double getPositionX() {
        return this.positionX;
    }

    public double getPositionY() {
        return this.positionY;
    }

    public double getSizeX() {
        return this.sizeX;
    }

    public double getSizeY() {
        return this.sizeY;
    }
}

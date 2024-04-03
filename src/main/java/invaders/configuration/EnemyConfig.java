package invaders.configuration;

public class EnemyConfig {
    private double positionX;
    private double positionY;
    private String projectile;

    public EnemyConfig(double positionX, double positionY, String projectile) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.projectile = projectile;
    }
    
    public double getPositionX() {
        return this.positionX;
    }

    public double getPositionY() {
        return this.positionY;
    }

    public String getProjectile() {
        return this.projectile;
    }
}

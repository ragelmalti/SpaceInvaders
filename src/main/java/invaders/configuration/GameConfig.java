package invaders.configuration;

public class GameConfig {
    private int width;
    private int height;

    public GameConfig(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}

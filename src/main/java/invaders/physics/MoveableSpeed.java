package invaders.physics;

// represents something that can move up, down, left, right
// Has the ability to adjust the speed.
public interface MoveableSpeed {
	public void up(double speed);
	public void down(double speed);
	public void left(double speed);
	public void right(double speed);
}

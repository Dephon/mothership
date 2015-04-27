package game2D.Collision;

public abstract class Collision {

	public static final int BLOCKED = 0;
	public static final int DAMAGING = 1;
	public static final int TRANSPORTING = 2;

	public abstract int getID();
}

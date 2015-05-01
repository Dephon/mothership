package game2D;

import org.newdawn.slick.*;

public abstract class Movable extends Entity {

	public Movable(String ref) throws SlickException {
		super(ref);
		// TODO Auto-generated constructor stub
	}

	public Movable(String ref, float x, float y) throws SlickException {
		super(ref, x, y);
		// TODO Auto-generated constructor stub
	}

	public Movable(String ref, int x, int y) throws SlickException {
		super(ref, x, y);
		// TODO Auto-generated constructor stub
	}

	public int health;

}

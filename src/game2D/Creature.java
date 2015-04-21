package game2D;

import org.newdawn.slick.*;

public class Creature extends Entity {

	public Creature(String ref) throws SlickException {
		super(ref);
		// TODO Auto-generated constructor stub
	}

	public Creature(String ref, float x, float y) throws SlickException {
		super(ref, x, y);
		// TODO Auto-generated constructor stub
	}

	public Creature(String ref, int x, int y) throws SlickException {
		super(ref, x, y);
		// TODO Auto-generated constructor stub
	}

	int health;

	@Override
	public void handleCollisions() {

	}

}

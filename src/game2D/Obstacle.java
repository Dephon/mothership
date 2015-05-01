package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Obstacle extends Entity {

	public Obstacle(String ref) throws SlickException {
		super(ref);
		// TODO Auto-generated constructor stub
	}

	public Obstacle(String ref, float x, float y) throws SlickException {
		super(ref, x, y);
		// TODO Auto-generated constructor stub
	}

	public Obstacle(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleCollisions() {
		// TODO Auto-generated method stub

	}

}

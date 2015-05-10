package game2D.abstracts;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Immovable extends Entity {

	public Immovable() {
		super();
	}

	public Immovable(String ref) throws SlickException {
		super(ref);
	}

	public Immovable(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
	}

	public Immovable(float locX, float locY, float width, float height)
			throws SlickException {
		super(locX, locY, width, height);
	}

	@Override
	public void handleCollision(int collisionEnum) {
		// TODO Auto-generated method stub
	}
}
package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Immovable extends Entity {

	public Immovable(String ref) throws SlickException {
		super(ref);
	}

	public Immovable(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
	}

	@Override
	public void handleCollision() {
		// TODO Auto-generated method stub

	}
}
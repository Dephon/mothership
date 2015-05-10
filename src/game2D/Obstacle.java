package game2D;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Obstacle extends Immovable {
	public Obstacle() {
		super();
	}

	public Obstacle(String ref) throws SlickException {
		super(ref);
	}

	public Obstacle(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
	}

	public Obstacle(float locX, float locY, float width, float height)
			throws SlickException {
		super(locX, locY, width, height);
		location.set(locX, locY);
		makeBox(locX, locY, width, height);
		invisible = true;
		dead = false;
	}

	public void convertToInvObject(float locX, float locY, float width,
			float height) {
		location.set(locX, locY);
		makeBox(locX, locY, width, height);
		invisible = true;
		dead = false;
	}
}

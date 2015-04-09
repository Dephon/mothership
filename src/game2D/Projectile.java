package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Projectile extends Entity {
	public Projectile(String ref) throws SlickException {
		super(ref);
	}

	Vector2f velocity;
}

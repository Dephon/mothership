package game2D.Projectile;

import game2D.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Ammo extends Entity {
	public Ammo(String ref) throws SlickException {
		super(ref);
		// TODO Auto-generated constructor stub
	}

	Vector2f velocity;
}

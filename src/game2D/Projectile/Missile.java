package game2D.Projectile;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Missile extends Ammo {
	public Missile() throws SlickException {
		super("data/Missile.png");
		this.jerk = .00001f;
	}

	public Missile(Vector2f loc) throws SlickException {
		super("data/Missile.png", loc);
		this.jerk = .00001f;
	}

	public Missile(Vector2f loc, Vector2f dir) throws SlickException {
		super("data/Missile.png", loc, dir);
		this.jerk = .00001f;
	}

	@Override
	public void handleCollisions() {
		// TODO Auto-generated method stub

	}
}
package game2D.Projectile;

import game2D.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Missile extends Ammo {
	public Missile() throws SlickException {
		super("data/Missile.png");
		jerk = .00001f;
	}

	public Missile(Vector2f loc) throws SlickException {
		super("data/Missile.png", loc);
		jerk = .00001f;
	}

	public Missile(Vector2f loc, Vector2f dir) throws SlickException {
		super("data/Missile.png", loc, dir);
		jerk = .00001f;
	}

	@Override
	public void create() {
		if (dead) {
			jerk = .00001f;
		}
	}

	@Override
	public void destroy() {
		if (!dead) {
			jerk = 0;
			super.destroy();
		}
	}

	@Override
	public boolean displace(Entity rhs) {
		boolean displaced = super.displace(rhs);
		if (displaced) {
			jerk = 0;
		}
		return displaced;
	}

	@Override
	public void handleCollisions() {
		// TODO Auto-generated method stub

	}
}
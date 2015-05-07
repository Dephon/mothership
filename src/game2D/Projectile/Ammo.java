package game2D.Projectile;

import game2D.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Ammo extends Entity {
	public Ammo(String ref) throws SlickException {
		super(ref);
		acceleration = 0;
		jerk = 0;
	}

	public Ammo(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		acceleration = 0;
		jerk = 0;
	}

	public Ammo(String ref, Vector2f loc, Vector2f dir) throws SlickException {
		super(ref, loc, dir);
		acceleration = 0;
		jerk = 0;
	}

	public void update(int dt) {
		acceleration += jerk * dt;
		speed += acceleration * dt;
		super.update(dt);
	}

	@Override
	public void setDirection(Vector2f dir) {
		super.setDirection(dir);
		rotate(false);
	}

	public float getAcceleration() {
		return acceleration;
	}

	@Override
	public void create(Vector2f loc, Vector2f dir) {
		super.create(loc, dir);
		rotate(false);
	}

	@Override
	public void destroy() {
		if (!dead) {
			acceleration = 0;
			rotate(true);
			super.destroy();
		}
	}

	@Override
	public boolean displace(Entity rhs) {
		boolean displaced = super.displace(rhs);
		if (displaced) {
			// speed = speed;
		}
		return displaced;
	}

	protected float acceleration;
	protected float jerk;
}
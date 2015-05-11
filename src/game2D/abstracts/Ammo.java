package game2D.abstracts;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Ammo extends Entity {
	public Ammo() {
		super();
		init();
	}

	public Ammo(String ref) throws SlickException {
		super(ref);
		init();
	}

	public Ammo(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		init();
	}

	public Ammo(String ref, Vector2f loc, Vector2f dir) throws SlickException {
		super(ref, loc, dir);
		init();
	}

	private void init() {
		acceleration = 0;
	}

	@Override
	public void update(int dt) {
		if (!dead) {
			TimeUntilDeath(getDeathTimer(), dt);
			speed += acceleration * dt;
			super.update(dt);
		}
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

	protected abstract int getDeathTimer();

	protected float acceleration;
}
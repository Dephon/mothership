package game2D.Projectile;

import game2D.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Ammo extends Entity {
	public Ammo(String ref) throws SlickException {
		super(ref);
		direction = new Vector2f();
		speed = 0;
		acceleration = 0;
		jerk = 0;
	}

	public Ammo(String ref, float x, float y) throws SlickException {
		super(ref, x, y);
		direction = new Vector2f();
		speed = 0;
		acceleration = 0;
		jerk = 0;
	}

	public Ammo(String ref, float x, float y, Vector2f dir)
			throws SlickException {
		super(ref, x, y);
		direction = new Vector2f();
		speed = 0;
		acceleration = 0;
		jerk = 0;
		setDirection(dir);
	}

	public Ammo(String ref, Vector2f loc, Vector2f dir) throws SlickException {
		super(ref, loc);
		direction = new Vector2f();
		speed = 0;
		acceleration = 0;
		jerk = 0;
		setDirection(dir);
	}

	public void update(int dt) {
		Vector2f dv = new Vector2f();
		acceleration += jerk * dt;
		speed += acceleration * dt;
		dv.x += direction.x * speed * dt;
		dv.y += direction.y * speed * dt;
		super.update(dv);
	}

	public Vector2f getDirection() {
		return direction;
	}

	public void setDirection(Vector2f dir) {
		direction.set(dir);
		rotate();
	}

	public float getSpeed() {
		return speed;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void create(Vector2f loc, Vector2f dir) {
		dead = false;
		setLoc(loc);
		direction.set(dir);
		rotate();
	}

	@Override
	public void destroy() {
		if (!dead) {
			reverseRotate();
			acceleration = 0;
			speed = 0;
			direction.x = 0;
			direction.y = 0;
			super.destroy();
		}
	}

	protected void rotate() {
		sprite.rotate((float) direction.getTheta());
	}

	protected void reverseRotate() {
		double theta = 360 - direction.getTheta();
		sprite.rotate((float) theta);

	}

	protected Vector2f direction;
	protected float speed;
	protected float acceleration;
	protected float jerk;
}
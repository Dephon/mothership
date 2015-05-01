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
		rotate(false);
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
		rotate(false);
	}

	@Override
	public void destroy() {
		if (!dead) {
			rotate(true);
			acceleration = 0;
			speed = 0;
			direction.x = 0;
			direction.y = 0;
			super.destroy();
		}
	}

	/**
	 * Rotates both the Image and Polygon with respect to the x axis
	 * 
	 * @param reverse
	 *            if true the rotation is counter clockwise, otherwise clockwise
	 *
	 **/
	protected void rotate(boolean reverse) {
		float theta;

		if (reverse)
			theta = (float) (360 - direction.getTheta());
		else
			theta = (float) direction.getTheta();

		sprite.rotate(theta);
		box.rotate(theta);
	}

	protected Vector2f direction;
	protected float speed;
	protected float acceleration;
	protected float jerk;
}
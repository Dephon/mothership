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

	public void debugDraw(Graphics graphics) {
		if (!dead) {
			graphics.draw(box);
			sprite.draw(box.getX(), box.getY());
		}
	}

	protected void rotate(boolean reverse) {
		double theta;
		float rad;

		if (reverse)
			theta = 360 - direction.getTheta();
		else
			theta = direction.getTheta();
		rad = (float) Math.toRadians(theta);
		sprite.rotate((float) theta);
		box.setX(0); // Can't believe I have to do this
		box.setY(0); // Slick2d blows
		box = (Polygon) box.transform(Transform.createRotateTransform(rad,
				box.getCenterX(), box.getCenterY()));
		box.setLocation(location);
	}

	protected Vector2f direction;
	protected float speed;
	protected float acceleration;
	protected float jerk;
}
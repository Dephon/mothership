package game2D.Projectile;

import game2D.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Ammo extends Entity {
	public Ammo(String ref) throws SlickException {
		super(ref);
		this.direction = new Vector2f();
		this.speed = 0;
		this.acceleration = 0;
		this.jerk = 0;
		this.dead = true;
	}

	public Ammo(String ref, float x, float y) throws SlickException {
		super(ref, x, y);
		this.direction = new Vector2f();
		this.speed = 0;
		this.acceleration = 0;
		this.jerk = 0;
		this.dead = true;
	}

	public Ammo(String ref, float x, float y, Vector2f direction)
			throws SlickException {
		super(ref, x, y);
		this.direction = new Vector2f();
		this.speed = 0;
		this.acceleration = 0;
		this.jerk = 0;
		this.dead = true;
		setDirection(direction);
	}

	public Ammo(String ref, Vector2f location, Vector2f direction)
			throws SlickException {
		super(ref, location);
		this.direction = new Vector2f();
		this.speed = 0;
		this.acceleration = 0;
		this.jerk = 0;
		this.dead = true;
		setLoc(location);
		setDirection(direction);
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

	public void setDirection(Vector2f direction) {
		this.direction.set(direction);
		this.rotate(direction);
	}

	public float getSpeed() {
		return speed;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void rotate(Vector2f direction) {
		double theta = Math.atan2(direction.y, direction.x) * 180 / Math.PI;
		sprite.rotate((float) theta);
	}

	public void revive(Vector2f location, Vector2f direction) {
		if (dead) {
			dead = false;
			this.location.set(location);
			this.direction.set(direction);
			rotate(direction);
		}
	}

	protected Vector2f direction;
	protected float speed;
	protected float acceleration;
	protected float jerk;
}

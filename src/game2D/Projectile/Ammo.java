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
	}

	public Ammo(String ref, float x, float y) throws SlickException {
		super(ref, x, y);
		direction = new Vector2f();
		speed = 0;
		acceleration = 0;
	}

	public Ammo(String ref, int x, int y) throws SlickException {
		super(ref, x, y);
		direction = new Vector2f();
		speed = 0;
		acceleration = 0;
	}

	public void update(int dt) {
		Vector2f dv = new Vector2f();
		acceleration = jerk * dt;
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
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

	Vector2f direction;
	float speed;
	float acceleration;
	float jerk;
}

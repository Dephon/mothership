package game2D.Projectile;

import game2D.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Ammo extends Entity {
	public Ammo(String ref) throws SlickException {
		super(ref);
		velocity = new Vector2f();
		acceleration = new Vector2f();
	}

	public void update(int dt) {
		Vector2f dv = new Vector2f();
		velocity.x += acceleration.x * dt;
		velocity.y += acceleration.y * dt;
		dv.x = velocity.x * dt;
		dv.y = velocity.y * dt;
		super.update(dv);
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity.set(velocity);
	}

	public Vector2f getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2f acceleration) {
		this.acceleration = acceleration;
	}

	Vector2f velocity;
	Vector2f acceleration;
}

package game2D.Projectile;

import game2D.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Missile extends Ammo {
	public Missile() throws SlickException {
		super();
		Image temp = new Image("data/Missile.png");
		trailingFire = new Animation();
		scale = 1.5f;
		currentAnimation.addFrame(temp.getSubImage(2, 4, 50, 14), 1000);
		box = makeBox(0, 0, currentAnimation.getWidth(),
				currentAnimation.getHeight());
		trailingFire.addFrame(
				temp.getSubImage(53, 6, 42, 22).getScaledCopy(scale), 100);
		trailingFire.addFrame(
				temp.getSubImage(98, 12, 42, 22).getScaledCopy(scale), 100);
		deathAnimation.addFrame(
				temp.getSubImage(142, 6, 32, 32).getScaledCopy(scale), 100);
		deathAnimation.addFrame(
				temp.getSubImage(177, 6, 32, 32).getScaledCopy(scale), 100);
		deathAnimation.addFrame(
				temp.getSubImage(212, 7, 31, 31).getScaledCopy(scale), 100);
		deathAnimation.addFrame(
				temp.getSubImage(246, 7, 31, 31).getScaledCopy(scale), 100);
		deathAnimation.addFrame(
				temp.getSubImage(280, 6, 31, 31).getScaledCopy(scale), 100);
		deathAnimation.addFrame(
				temp.getSubImage(313, 6, 31, 31).getScaledCopy(scale), 100);
		deathAnimation.addFrame(
				temp.getSubImage(346, 7, 31, 31).getScaledCopy(scale), 100);
		deathAnimation.addFrame(
				temp.getSubImage(379, 7, 31, 31).getScaledCopy(scale), 100);
		deathAnimation.setLooping(false);
		jerk = .00001f;
		deathSound = new Sound("data/sounds/Missile_Explosion.wav");
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
		jerk = .00001f;
		box = makeBox(location.getX(), location.getY(),
				currentAnimation.getWidth(), currentAnimation.getHeight());
		super.create();
	}

	@Override
	public void create(Vector2f loc) {
		jerk = .00001f;
		box = makeBox(location.getX(), location.getY(),
				currentAnimation.getWidth(), currentAnimation.getHeight());
		super.create(loc);
	}

	@Override
	public void create(Vector2f loc, Vector2f dir) {
		jerk = .00001f;
		box = makeBox(location.getX(), location.getY(),
				currentAnimation.getWidth(), currentAnimation.getHeight());
		super.create(loc, dir);
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
		boolean displaced = false;
		if (!dead) {
			displaced = super.displace(rhs);
			if (displaced) {
				jerk = 0;
			}
		}
		return displaced;
	}

	@Override
	public void draw() {
		if (!dead) {
			if (dying)
				deathAnimation.draw(box.getX(), box.getY());
			else
				super.draw();
		}
	}

	@Override
	public void debugDraw(Graphics graphics) {
		if (!dead) {
			if (dying)
				deathAnimation.draw(box.getX(), box.getY());
			else
				super.debugDraw(graphics);
		}
	}

	@Override
	public void update(int dt) {
		if (!dead) {
			if (dying) {
				if (dyingTimer > 800)
					destroy();
				else
					dyingTimer += dt;
			} else {
				super.update(dt);
			}
		}
	}

	@Override
	public void handleCollision() {
		dying = true;
		box = makeBox(location.getX(), location.getY(), 32, 32);
		deathSound.play();
	}

	float scale;
	Animation trailingFire;
}
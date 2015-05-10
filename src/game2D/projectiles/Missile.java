package game2D.projectiles;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Missile extends Ammo {
	public Missile() throws SlickException {
		super();
		Image spriteSheet = new Image("data/Missile.png");
		trailingFire = new Animation();
		igniteTime = 0;
		jerk = 0;
		ignited = false;
		currentAnimation.addFrame(spriteSheet.getSubImage(3, 0, 92, 22), 100);
		trailingFire.addFrame(spriteSheet.getSubImage(3, 26, 92, 22), 10);
		trailingFire.addFrame(spriteSheet.getSubImage(3, 52, 92, 22), 10);
		updateBox();
		deathAnimation.addFrame(spriteSheet.getSubImage(142, 6, 32, 32)
				.getScaledCopy(scale), 50);
		deathAnimation.addFrame(spriteSheet.getSubImage(177, 6, 32, 32)
				.getScaledCopy(scale), 50);
		deathAnimation.addFrame(spriteSheet.getSubImage(212, 7, 32, 32)
				.getScaledCopy(scale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(246, 7, 32, 32)
				.getScaledCopy(scale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(280, 6, 32, 32)
				.getScaledCopy(scale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(313, 6, 32, 32)
				.getScaledCopy(scale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(346, 7, 32, 32)
				.getScaledCopy(scale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(379, 7, 32, 32)
				.getScaledCopy(scale), 100);
		deathAnimation.setLooping(false);
	}

	public Missile(Vector2f loc) throws SlickException {
		super("data/Missile.png", loc);
		jerk = 0;
	}

	public Missile(Vector2f loc, Vector2f dir) throws SlickException {
		super("data/Missile.png", loc, dir);
		jerk = 0;
	}

	@Override
	public void create() {
		jerk = .00001f;
		super.create();
	}

	@Override
	public void create(Vector2f loc) {
		jerk = .00001f;
		super.create(loc);
	}

	@Override
	public void create(Vector2f loc, Vector2f dir) {
		jerk = .00001f;
		super.create(loc, dir);
	}

	@Override
	public void destroy() {
		if (!dead) {
			jerk = 0;
			igniteTime = 0;
			ignited = false;
			super.destroy();
			rotateOnlyImage(true);
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
	public void update(int dt) {
		if (!dead) {
			TimeUntilDeath(700, dt);
			if (igniteTime > 300 && !ignited) {
				ignited = true;
				currentAnimation = trailingFire;
				currentAnimation.setAutoUpdate(true);
				rotateOnlyImage(false);
			} else {
				if (igniteTime < Integer.MAX_VALUE)
					igniteTime += dt;
			}
			acceleration += jerk * dt;
			super.update(dt);
		}
	}

	@Override
	protected void handleCollision(int collisionEnum) {
		dying = true;
		updateBox();
	}

	protected boolean ignited;
	protected int igniteTime;
	protected float jerk;
	protected Animation trailingFire;
}
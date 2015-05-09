package game2D.Projectile;

import game2D.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Missile extends Ammo {
	public Missile() throws SlickException {
		super();
		Image spriteSheet = new Image("data/Missile.png");
		trailingFire = new Animation();
		scale = 1.5f;
		jerk = 0;
		currentAnimation.addFrame(spriteSheet.getSubImage(2, 4, 50, 14), 1000);
		updateBox();
		trailingFire.addFrame(spriteSheet.getSubImage(53, 6, 42, 22), 100);
		trailingFire.addFrame(spriteSheet.getSubImage(98, 12, 42, 22), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(142, 6, 32, 32)
				.getScaledCopy(scale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(177, 6, 32, 32)
				.getScaledCopy(scale), 100);
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
		deathSound = new Sound("data/sounds/Missile_Explosion.wav");
		sound = new Sound("data/sounds/Missile_Launch.wav");
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
		sound.play();
	}

	@Override
	public void create(Vector2f loc) {
		jerk = .00001f;
		super.create(loc);
		sound.play();
	}

	@Override
	public void create(Vector2f loc, Vector2f dir) {
		jerk = .00001f;
		super.create(loc, dir);
		sound.play(1f, .4f);
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
	public void update(int dt) {
		if (!dead) {
			TimeUntilDeath(800, dt);
			acceleration += jerk * dt;
			super.update(dt);
		}
	}

	@Override
	protected void handleCollision() {
		dying = true;
		updateBox();
		sound.stop();
		deathSound.play(1f, .4f);
	}

	protected float scale;
	protected float jerk;
	protected Animation trailingFire;
}
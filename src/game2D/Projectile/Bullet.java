package game2D.Projectile;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Bullet extends Ammo {
	public Bullet() throws SlickException {
		super();
		Image temp = new Image("data/Bullet.png");
		currentAnimation.addFrame(temp.getScaledCopy(scale), 1000);
		updateBox();
		temp = new Image("data/Bullet_Hit.png");
		deathAnimation.addFrame(
				temp.getSubImage(0, 0, 16, 21).getScaledCopy(scale), 100);
		deathAnimation.addFrame(temp.getSubImage(19, 0, 16, 21), 100);
		deathAnimation.addFrame(temp.getSubImage(38, 0, 16, 21), 100);
		deathAnimation.setLooping(false);
		this.speed = 1;
		sound = new Sound("data/sounds/Bullet_Shot.wav");
	}

	// public Missile() throws SlickException {
	// super();
	// Image spriteSheet = new Image("data/Missile.png");
	// trailingFire = new Animation();
	// scale = 1.5f;
	// jerk = 0;
	// currentAnimation.addFrame(spriteSheet.getSubImage(2, 4, 50, 14), 1000);
	// updateBox();
	// trailingFire.addFrame(spriteSheet.getSubImage(53, 6, 42, 22), 100);
	// trailingFire.addFrame(spriteSheet.getSubImage(98, 12, 42, 22), 100);
	// deathAnimation.addFrame(spriteSheet.getSubImage(142, 6, 32, 32)
	// .getScaledCopy(scale), 50);
	// deathAnimation.addFrame(spriteSheet.getSubImage(177, 6, 32, 32)
	// .getScaledCopy(scale), 50);
	// deathAnimation.addFrame(spriteSheet.getSubImage(212, 7, 32, 32)
	// .getScaledCopy(scale), 100);
	// deathAnimation.addFrame(spriteSheet.getSubImage(246, 7, 32, 32)
	// .getScaledCopy(scale), 100);
	// deathAnimation.addFrame(spriteSheet.getSubImage(280, 6, 32, 32)
	// .getScaledCopy(scale), 100);
	// deathAnimation.addFrame(spriteSheet.getSubImage(313, 6, 32, 32)
	// .getScaledCopy(scale), 100);
	// deathAnimation.addFrame(spriteSheet.getSubImage(346, 7, 32, 32)
	// .getScaledCopy(scale), 100);
	// deathAnimation.addFrame(spriteSheet.getSubImage(379, 7, 32, 32)
	// .getScaledCopy(scale), 100);
	// deathAnimation.setLooping(false);
	// }

	public Bullet(Vector2f loc) throws SlickException {
		super("data/Bullet.png", loc);
		speed = 1;
	}

	public Bullet(Vector2f loc, Vector2f dir) throws SlickException {
		super("data/Bullet.png", loc, dir);
		speed = 1;
	}

	@Override
	public void create() {
		super.create();
		sound.play();
	};

	@Override
	public void create(Vector2f loc) {
		super.create(loc);
		sound.play();
	};

	public void create(Vector2f loc, Vector2f dir) {
		super.create(loc, dir);
		sound.play();
	}

	@Override
	public void update(int dt) {
		if (!dead) {
			TimeUntilDeath(300, dt);
			super.update(dt);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		speed = 1.0f;
	}

	@Override
	public void handleCollision(int collisionEnum) {
		dying = true;
		updateBox();
	}
}
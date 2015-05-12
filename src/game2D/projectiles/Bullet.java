package game2D.projectiles;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Bullet extends Ammo {
	public Bullet() throws SlickException {
		super();
		init();
	}

	public Bullet(Vector2f loc) throws SlickException {
		super("data/Bullet.png", loc);
		init();
	}

	public Bullet(Vector2f loc, Vector2f dir) throws SlickException {
		super("data/Bullet.png", loc, dir);
		init();
	}

	private void init() throws SlickException {
		Image temp = new Image("data/Bullet.png");
		currentAnimation.addFrame(temp.getScaledCopy(scale), 1000);
		updateBox();
		temp = new Image("data/Bullet_Hit.png");
		deathAnimation.addFrame(
				temp.getSubImage(0, 0, 16, 21).getScaledCopy(scale), 100);
		deathAnimation.addFrame(temp.getSubImage(19, 0, 16, 21), 100);
		deathAnimation.addFrame(temp.getSubImage(38, 0, 16, 21), 100);
		deathAnimation.setLooping(false);
		speed = 1;
		statDamage = 25;
	}

	@Override
	public void create() {
		super.create();
	};

	@Override
	public void create(Vector2f loc) {
		super.create(loc);
	};

	public void create(Vector2f loc, Vector2f dir) {
		super.create(loc, dir);
	}

	@Override
	public void update(int dt) {
		if (!dead) {
			super.update(dt);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		speed = 1.0f;
	}

	@Override
	public void handleCollision(int collisionEnum, int statDamage) {
		dying = true;
		updateBox();
	}

	@Override
	protected int getDeathTimer() {
		return 300;
	}
}
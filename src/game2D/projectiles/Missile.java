package game2D.projectiles;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Missile extends Ammo {
	public Missile() throws SlickException {
		super();
		init();
	}

	public Missile(Vector2f loc) throws SlickException {
		super("data/Missile.png", loc);
		init();
	}

	public Missile(Vector2f loc, Vector2f dir) throws SlickException {
		super("data/Missile.png", loc, dir);
		init();
	}

	private void init() throws SlickException {
		Image spriteSheet = new Image("data/Missile.png");
		trailingFire = new Animation();
		igniteTime = 0;
		jerk = 0;
		ignited = false;
		splashScale = 3f;
		aliveAnimation.addFrame(spriteSheet.getSubImage(3, 0, 92, 22), 100);
		currentAnimation = aliveAnimation;
		trailingFire.addFrame(spriteSheet.getSubImage(3, 26, 92, 22), 10);
		trailingFire.addFrame(spriteSheet.getSubImage(3, 52, 92, 22), 10);
		updateBox();
		deathAnimation.addFrame(spriteSheet.getSubImage(142, 6, 32, 32)
				.getScaledCopy(splashScale), 50);
		deathAnimation.addFrame(spriteSheet.getSubImage(177, 6, 32, 32)
				.getScaledCopy(splashScale), 50);
		deathAnimation.addFrame(spriteSheet.getSubImage(212, 7, 32, 32)
				.getScaledCopy(splashScale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(246, 7, 32, 32)
				.getScaledCopy(splashScale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(280, 6, 32, 32)
				.getScaledCopy(splashScale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(313, 6, 32, 32)
				.getScaledCopy(splashScale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(346, 7, 32, 32)
				.getScaledCopy(splashScale), 100);
		deathAnimation.addFrame(spriteSheet.getSubImage(379, 7, 32, 32)
				.getScaledCopy(splashScale), 100);
		deathAnimation.setLooping(false);
		statDamage = 100;
		statSplashDamage = 100;
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
	protected int getDeathTimer() {
		return 700;
	}

	@Override
	public void destroy() {
		if (!dead) {
			rotate(true, true);
			currentAnimation = aliveAnimation;
			jerk = 0;
			igniteTime = 0;
			ignited = false;
			super.destroy();

		}
	}

	@Override
	public void update(int dt) {
		if (!dead) {
			if (igniteTime > 300 && !ignited) {
				ignited = true;
				currentAnimation = trailingFire;
				rotate(true, false);
			} else {
				if (igniteTime < Integer.MAX_VALUE)
					igniteTime += dt;
			}
			acceleration += jerk * dt;
			super.update(dt);
		}
	}

	@Override
	public void debugDraw(Graphics graphics) {
		if (!dead) {
			graphics.drawString("Center: (" + getCenterX() + "," + getCenterY()
					+ ")", 0, 400);
		}
		super.debugDraw(graphics);
	}

	@Override
	public void handleCollision(int collisionEnum, int statDamage) {
		dying = true;
		displace(someMathFunction());
		updateBox();
	}

	// I don't know how to explain this function yet, I used a lot of trig +
	// geometry
	protected Vector2f someMathFunction() {
		float distance;
		Vector2f a = box.getPointVect(1);
		Vector2f b = box.getPointVect(2);
		Vector2f c = new Vector2f(box.getCenterX() - box.getX(),
				box.getCenterY() - box.getY());
		a.x = (a.x + b.x) / 2;
		a.y = (a.y + b.y) / 2;
		distance = a.distance(new Vector2f(box.getCenterX(), box.getCenterY()));
		c.add(new Vector2f(distance, distance).negate());
		return c;
	}

	protected boolean ignited;
	protected int igniteTime;
	protected float jerk;
	protected Animation trailingFire;
	protected float splashScale;
}
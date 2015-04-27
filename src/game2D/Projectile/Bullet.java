package game2D.Projectile;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Bullet extends Ammo {
	public Bullet() throws SlickException {
		super("data/Bullet.png");
		this.speed = 1;

	}

	public Bullet(float x, float y) throws SlickException {
		super("data/Bullet.png", x, y);
		this.speed = 1;

	}

	public Bullet(int x, int y) throws SlickException {
		super("data/Bullet.png", x, y);
		this.speed = 1;

	}

	public Bullet(float x, float y, Vector2f direction) throws SlickException {
		super("data/Bullet.png", x, y, direction);
		this.speed = 1;

	}

	public Bullet(int x, int y, Vector2f direction) throws SlickException {
		super("data/Bullet.png", x, y, direction);
		this.speed = 1;

	}

	@Override
	public void destroy() {
		super.destroy();
		speed = 1.0f;
	}

	@Override
	public void handleCollisions() {
		// TODO Auto-generated method stub

	}
}
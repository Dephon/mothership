package game2D.Projectile;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Bullet extends Ammo {
	public Bullet() throws SlickException {
		super("data/Bullet.png");
		this.speed = 1;
	}

	public Bullet(Vector2f loc) throws SlickException {
		super("data/Bullet.png", loc);
		speed = 1;
	}

	public Bullet(Vector2f loc, Vector2f dir) throws SlickException {
		super("data/Bullet.png", loc, dir);
		speed = 1;
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
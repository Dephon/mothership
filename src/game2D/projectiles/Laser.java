package game2D.projectiles;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Laser extends Ammo {
	public Laser() throws SlickException {
		super("data/Bullet.png");
	}

	public Laser(Vector2f loc) throws SlickException {
		super("data/Bullet.png", loc);
	}

	public Laser(Vector2f loc, Vector2f dir) throws SlickException {
		super("data/Bullet.png", loc, dir);
	}

	@Override
	public void handleCollision(int collisionEnum, int statDamage) {

	}

	@Override
	protected int getDeathTimer() {
		return 0;
	}
}

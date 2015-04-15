package game2D.Projectile;

import org.newdawn.slick.*;

public class Laser extends Ammo {
	public Laser() throws SlickException {
		super("data/Bullet.png");
	}

	public Laser(float x, float y) throws SlickException {
		super("data/Bullet.png", x, y);
	}

	public Laser(int x, int y) throws SlickException {
		super("data/Bullet.png", x, y);
	}
}

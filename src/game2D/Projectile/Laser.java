package game2D.Projectile;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

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

	public Laser(float x, float y, Vector2f direction) throws SlickException {
		super("data/Bullet.png", x, y, direction);
	}

	public Laser(int x, int y, Vector2f direction) throws SlickException {
		super("data/Bullet.png", x, y, direction);
	}
}

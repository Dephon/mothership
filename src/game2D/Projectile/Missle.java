package game2D.Projectile;

import org.newdawn.slick.*;

public class Missle extends Ammo {
	public Missle() throws SlickException {
		super("data/Bullet.png"); // change to Missle .png
		jerk = .002f;
	}

	public Missle(float x, float y) throws SlickException {
		super("data/Bullet.png", x, y);
		jerk = .002f;
	}

	public Missle(int x, int y) throws SlickException {
		super("data/Bullet.png", x, y);
		jerk = .002f;
	}
}
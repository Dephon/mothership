package game2D.Projectile;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Missile extends Ammo {
	public Missile() throws SlickException {
		super("data/Missile.png");
		this.jerk = .00001f;
	}

	public Missile(float x, float y) throws SlickException {
		super("data/Missile.png", x, y);
		this.jerk = .00001f;
	}

	public Missile(int x, int y) throws SlickException {
		super("data/Missile.png", x, y);
		this.jerk = .00001f;
	}

	public Missile(float x, float y, Vector2f dir) throws SlickException {
		super("data/Missile.png", x, y, dir);
		this.jerk = .00001f;
	}

	public Missile(int x, int y, Vector2f dir) throws SlickException {
		super("data/Missile.png", x, y, dir);
		this.jerk = .00001f;
	}

	@Override
	public void handleCollisions() {
		// TODO Auto-generated method stub

	}
}
package game2D.Projectile;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Missile extends Ammo {
	public Missile() throws SlickException {
		super("data/Missile.png");
		jerk = .0005f;
		this.fireDelay = 200;
	}

	public Missile(float x, float y) throws SlickException {
		super("data/Missile.png", x, y);
		jerk = .0005f;
		this.fireDelay = 200;
	}

	public Missile(int x, int y) throws SlickException {
		super("data/Missile.png", x, y);
		jerk = .0005f;
		this.fireDelay = 200;
	}

	public Missile(float x, float y, Vector2f direction) throws SlickException {
		super("data/Missile.png", x, y, direction);
		this.jerk = .0005f;
		this.fireDelay = 200;
	}

	public Missile(int x, int y, Vector2f direction) throws SlickException {
		super("data/Missile.png", x, y, direction);
		this.jerk = .0005f;
		this.fireDelay = 200;
	}
}
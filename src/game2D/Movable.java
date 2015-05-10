package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.openal.*;

public abstract class Movable extends Entity {
	public Movable() {
		super();
		init();
	}

	public Movable(String ref) throws SlickException {
		super(ref);
		init();
	}

	public Movable(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		init();
	}

	private void init() {
		hurt = null;
	}

	public void takeDamage(int dmg) {

		if (dmg > 0) {
			if (hurt != null) {
				hurt.playAsSoundEffect(1f, 1f, false);
			}

		}
		health -= dmg;
		if (health > 100)
			health = 100;
		if (health <= 0) {
			health = 0;
			dead = true;
		}
	}

	public int getHealth() {
		return health;
	}

	public int health;
	Audio hurt;
}

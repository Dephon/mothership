package game2D;

import org.newdawn.slick.*;

public abstract class Movable extends Entity {

	public Movable(String ref) throws SlickException {
		super(ref);
		// init();
	}

	public Movable(String ref, float x, float y) throws SlickException {
		super(ref, x, y);
		// init();
	}

	public Movable(String ref, int x, int y) throws SlickException {
		super(ref, x, y);
		// init();
	}

	// private void init() {
	// health = 100;
	// }

	public int health;

	public void takeDamage(int dmg) {
		health -= dmg;
		if (health > 100)
			health = 100;
		if (health <= 0) {
			health = 0;
			dead = true;
		}
	}

}

package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Movable extends Entity {

	public Movable(String ref) throws SlickException {
		super(ref);
		// TODO Auto-generated constructor stub
	}

	public Movable(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		// TODO Auto-generated constructor stub
	}

	// private void init() {
	// health = 100;
	// }

	public void takeDamage(int dmg) {
		health -= dmg;
		if (health > 100)
			health = 100;
		if (health <= 0) {
			health = 0;
			dead = true;
		}
	}

	public int health;

}

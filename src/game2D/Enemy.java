package game2D;

import game2D.Collision.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Enemy extends Movable {

	public Enemy(String ref) throws SlickException {
		super(ref);

	}

	public Enemy(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
	}

	@Override
	public void handleCollision(int collisionEnum) {
		if (collisionEnum == CollisionEnum.DAMAGING) {
			takeDamage(10);
		}
	}

	// @Override
	// public void handleCollisions() {
	// Collision temp;
	// while (!collisions.isEmpty()) {
	// temp = collisions.remove();
	// switch (temp.getID()) {
	// case Collision.BLOCKED:
	// break;
	// case Collision.DAMAGING:
	// break;
	// case Collision.TRANSPORTING:
	// break;
	// default:
	// break;
	// }
	// }
	// }

}

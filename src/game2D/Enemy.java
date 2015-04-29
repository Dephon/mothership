package game2D;

import game2D.Collision.*;

import org.newdawn.slick.*;

public class Enemy extends Movable {

	public Enemy(String ref) throws SlickException {
		super(ref);

	}

	public Enemy(String ref, float x, float y) throws SlickException {
		super(ref, x, y);

	}

	public Enemy(String ref, int x, int y) throws SlickException {
		super(ref, x, y);

	}

	@Override
	public void handleCollisions() {
		Collision temp;
		while (!collisions.isEmpty()) {
			temp = collisions.remove();
			switch (temp.getID()) {
			case Collision.BLOCKED:
				break;
			case Collision.DAMAGING:
				break;
			case Collision.TRANSPORTING:
				break;
			default:
				break;
			}
		}
	}

}

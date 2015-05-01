package game2D;

import org.newdawn.slick.*;

public class Player extends Movable {

	public Player(String ref) throws SlickException {
		super(ref);
	}

	public Player(String ref, float x, float y) throws SlickException {
		super(ref, x, y);
		// TODO Auto-generated constructor stub
	}

	public Player(String ref, int x, int y) throws SlickException {
		super(ref, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleCollisions() {
		// TODO Auto-generated method stub

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

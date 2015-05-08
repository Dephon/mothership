package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Player extends Movable {

	public Player(String ref) throws SlickException {
		super(ref);
		init();
	}

	public Player(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		init();
	}

	@Override
	public void handleCollisions() {
		// TODO Auto-generated method stub

	}

	public void init() throws SlickException {
		health = 100;
		ui = new UI();
	}

	public void updateUI() {
		ui.update(health);
	}

	public void drawUI() {
		ui.draw();
	}

	public static int PORTRAIT_HEALTHY = 0;
	public static int PORTRAIT_SLIGHT_DAMAGE = 1;
	public static int PORTRAIT_SHAKEN_UP = 2;
	public static int PORTRAIT_HEAVY_DAMAGE = 3;
	public static int PORTRAIT_DYING = 4;

	private UI ui;
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

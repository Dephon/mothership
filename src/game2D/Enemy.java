package game2D;

import game2D.abstracts.*;
import game2D.collisions.*;

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
		} else if (collisionEnum == CollisionEnum.NONE) {
			return;
		}
	}

	@Override
	protected void setAnimations() throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getMaxHP() {
		return 100;
	}
}

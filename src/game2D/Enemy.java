package game2D;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Enemy extends Movable {

	public Enemy(String ref) throws SlickException {
		super(ref);
		init();
	}

	public Enemy(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		init();
	}

	protected void init() {
		statDamage = 10;
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

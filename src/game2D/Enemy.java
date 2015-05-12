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
		speed = .06f;
	}

	@Override
	protected void setAnimations() throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getMaxHP() {
		return 100;
	}

	@Override
	public void update(Vector2f loc, int dt) {
		Vector2f temp = new Vector2f(loc.x - this.getCenterX(), loc.y
				- this.getCenterY());
		temp.normalise();
		if (!dead) {
			Vector2f dV = new Vector2f();
			direction.set(temp);
			dV.set(temp);
			dV.x *= speed * dt;
			dV.y *= speed * dt;
			location.add(dV);
			box.setLocation(location);
		}
	}

	public Enemy(Double toTest) throws SlickException {
		super(toTest);
	}

}
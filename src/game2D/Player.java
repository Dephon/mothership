package game2D;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public final class Player extends Movable {

	public Player() throws SlickException {
		init();
	}

	public Player(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		init();
	}

	protected void init() throws SlickException {
		super.init();
		invin = false;
		maxInvTime = 1000;
		missiles = 10;
		currentAnimation = movementAnimations[DIRECTION_EAST];
		updateBox();
	}

	@Override
	protected int getMaxHP() {
		return 100;
	}

	@Override
	public void takeDamage(int dmg) {
		if (!invin) {
			super.takeDamage(dmg);
			invin = true;
		}
	};

	@Override
	public void update(Vector2f dir, int dt) {
		super.update(dir, dt);
		invincCheck(dt);
	};

	private void invincCheck(int dt) {
		if (invin) {
			invinTimer += dt;
			if (invinTimer > maxInvTime) {
				invin = false;
				invinTimer = 0;
			}
		}
	}

	public boolean isDamaged() {
		if (health < maxHealth)
			return true;
		else
			return false;
	}

	public int getMissileCount() {
		return missiles;
	}

	// @Override
	// public void draw() {
	// if (damaged) {
	// if (invincible % 5 == 0) {
	// super.draw();
	// }
	// }
	// }
	//
	// @Override
	// public void debugDraw(Graphics graphics) {
	// if (!damaged || show) {
	// super.debugDraw(graphics);
	// } else {
	//
	// }
	// graphics.draw(box);
	// }

	public boolean isInvin() {
		return invin;
	}

	@Override
	protected void setAnimations() throws SlickException {
		SpriteSheet movement = new SpriteSheet(new Image(
				"data/sprites/move_animations.png"), 40, 40);
		movementAnimations = new Animation[movement.getVerticalCount()];

		for (int i = 0; i < movement.getVerticalCount(); i++) {
			movementAnimations[i] = new Animation();
			for (int j = 0; j < movement.getHorizontalCount(); j++) {
				movementAnimations[i].addFrame(movement.getSprite(j, i), 200);
			}
		}
	}

	protected int missiles;
	protected boolean show;
	protected int maxInvTime;
	protected int invinTimer;
	protected boolean invin;

	public Player(Double toTest) throws SlickException {
		super(toTest);
		invin = false;
	}

}

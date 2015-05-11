package game2D;

import game2D.abstracts.*;
import game2D.collisions.*;

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
		damaged = false;
		invincible = 200;
		currentAnimation = movementAnimations[DIRECTION_EAST];
		updateBox();
	}

	@Override
	public void handleCollision(int collisionEnum) {
		if (collisionEnum == CollisionEnum.DAMAGING) {

		}
	}

	@Override
	protected int getMaxHP() {
		return 100;
	}

	@Override
	public void takeDamage(int dmg) {
		if (!damaged) {
			super.takeDamage(dmg);
			damaged = true;
		}
	};

	@Override
	public void update(Vector2f dir, int dt) {
		super.update(dir, dt);
		damagedCheck(dt);
	};

	private void damagedCheck(int dt) {
		if (damaged) {
			invincible += dt;
			if (invincible > maxInvTime) {
				damaged = false;
				invincible = 0;
			}
		}
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

	protected int invincible;
	protected boolean damaged;
}

package game2D.abstracts;

import game2D.collisions.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Movable extends Entity {
	public Movable() throws SlickException {
		super();
		init();
	}

	public Movable(String ref) throws SlickException {
		super(ref);
		init();
	}

	public Movable(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		init();
	}

	protected void init() throws SlickException {
		setAnimations();
	}

	public int getHealth() {
		return health;
	}

	@Override
	public void create() {
		super.create();
		maxHealth = getMaxHP();
		health = maxHealth;
	}

	public void addHealth(int heal) {
		health += heal;
		if (health > maxHealth)
			health = maxHealth;
	}

	public void takeDamage(int dmg) {
		health -= dmg;
		if (health <= 0) {
			health = 0;
			destroy();
		}
	}

	@Override
	public void handleCollision(int collisionEnum, int statDamage) {
		if (collisionEnum == CollisionEnum.DAMAGING) {
			takeDamage(statDamage);
		}
	}

	public void updateAnimation(Vector2f dir) {
		if (dir.x == 0 && dir.y == 1) {
			currentAnimation = movementAnimations[DIRECTION_SOUTH];
			currentAnimation.start();
		} else if (dir.x == 0 && dir.y == -1) {
			currentAnimation = movementAnimations[DIRECTION_NORTH];
			currentAnimation.start();
		} else if (dir.x == -1 && dir.y == 0) {
			currentAnimation = movementAnimations[DIRECTION_WEST];
			currentAnimation.start();
		} else if (dir.x == 1 && dir.y == 0) {
			currentAnimation = movementAnimations[DIRECTION_EAST];
			currentAnimation.start();
		} else if (dir.x == 1 && dir.y == -1) {
			currentAnimation = movementAnimations[DIRECTION_NORTHEAST];
			currentAnimation.start();
		} else if (dir.x == 1 && dir.y == 1) {
			currentAnimation = movementAnimations[DIRECTION_SOUTHEAST];
			currentAnimation.start();
		} else if (dir.x == -1 && dir.y == 1) {
			currentAnimation = movementAnimations[DIRECTION_SOUTHWEST];
			currentAnimation.start();
		} else if (dir.x == -1 && dir.y == -1) {
			currentAnimation = movementAnimations[DIRECTION_NORTHWEST];
			currentAnimation.start();
		} else if (dir.x == 0 && dir.y == 0) {
			currentAnimation.stop();
		}
	}

	protected abstract int getMaxHP();

	protected abstract void setAnimations() throws SlickException;

	protected Animation[] movementAnimations;
	protected final int DIRECTION_NORTH = 0;
	protected final int DIRECTION_NORTHEAST = 1;
	protected final int DIRECTION_EAST = 2;
	protected final int DIRECTION_SOUTHEAST = 3;
	protected final int DIRECTION_SOUTH = 4;
	protected final int DIRECTION_SOUTHWEST = 5;
	protected final int DIRECTION_WEST = 6;
	protected final int DIRECTION_NORTHWEST = 7;
	protected int maxHealth;
	protected int health;
}

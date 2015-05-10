package game2D;

import java.io.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.util.*;

public class Player extends Movable {

	public Player() throws SlickException, IOException {
		init();
	}

	public Player(String ref, Vector2f loc) throws SlickException, IOException {
		super(ref, loc);
		init();
	}

	@Override
	public void handleCollision(int collisionEnum) {
		// TODO Auto-generated method stub

	}

	public void init() throws SlickException, IOException {
		health = 100;
		ui = new UI();
		SpriteSheet movement = new SpriteSheet(new Image(
				"data/sprites/move_animations.png"), 40, 40);
		movementAnimations = new Animation[movement.getVerticalCount()];

		for (int i = 0; i < movement.getVerticalCount(); i++) {
			movementAnimations[i] = new Animation();
			for (int j = 0; j < movement.getHorizontalCount(); j++) {
				movementAnimations[i].addFrame(movement.getSprite(j, i), 200);
			}
		}

		// Start in the downward position.
		currentAnimation = movementAnimations[Player.DIRECTION_SOUTH];
		updateBox();

		hurt = AudioLoader.getAudio("WAV",
				ResourceLoader.getResourceAsStream("data/sounds/hurt.wav"));

	}

	public void displace(EnemyManager enemyManager) {
		// TODO Auto-generated method stub

	}

	public void displace(Manager Manager, int CollisionEnum) {

	}

	public void updateUI() {
		ui.update(health);

	}

	public void drawUI() {
		ui.draw();
	}

	public void updateAnimation() {
		if (direction.x == 0 && direction.y == 1) {
			currentAnimation = movementAnimations[Player.DIRECTION_SOUTH];
			currentAnimation.start();
		} else if (direction.x == 0 && direction.y == -1) {
			currentAnimation = movementAnimations[Player.DIRECTION_NORTH];
			currentAnimation.start();
		} else if (direction.x == -1 && direction.y == 0) {
			currentAnimation = movementAnimations[Player.DIRECTION_WEST];
			currentAnimation.start();
		} else if (direction.x == 1 && direction.y == 0) {
			currentAnimation = movementAnimations[Player.DIRECTION_EAST];
			currentAnimation.start();
		} else if (direction.x == 1 && direction.y == -1) {
			currentAnimation = movementAnimations[Player.DIRECTION_NORTHEAST];
			currentAnimation.start();
		} else if (direction.x == 1 && direction.y == 1) {
			currentAnimation = movementAnimations[Player.DIRECTION_SOUTHEAST];
			currentAnimation.start();
		} else if (direction.x == -1 && direction.y == 1) {
			currentAnimation = movementAnimations[Player.DIRECTION_SOUTHWEST];
			currentAnimation.start();
		} else if (direction.x == -1 && direction.y == -1) {
			currentAnimation = movementAnimations[Player.DIRECTION_NORTHWEST];
			currentAnimation.start();
		} else if (direction.x == 0 && direction.y == 0) {
			currentAnimation.stop();
		}
	}

	public static int DIRECTION_NORTH = 0;
	public static int DIRECTION_NORTHEAST = 1;
	public static int DIRECTION_EAST = 2;
	public static int DIRECTION_SOUTHEAST = 3;
	public static int DIRECTION_SOUTH = 4;
	public static int DIRECTION_SOUTHWEST = 5;
	public static int DIRECTION_WEST = 6;
	public static int DIRECTION_NORTHWEST = 7;

	Animation[] movementAnimations;
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

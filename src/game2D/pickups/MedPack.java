package game2D.pickups;

import game2D.abstracts.*;

import org.newdawn.slick.*;

public class MedPack extends Entity {

	MedPack() throws SlickException {
		super();
		SpriteSheet medPackSheet = new SpriteSheet(new Image(
				"data/sprites/medpack.png"), 32, 32);
		currentAnimation = new Animation(medPackSheet, 500);
		updateBox();
	}

	@Override
	public void handleCollision(int collisionEnum, int statDamage) {
		dead = true;

	}

}

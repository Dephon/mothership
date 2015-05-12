package game2D;

import org.newdawn.slick.*;

public class AlienSS extends Enemy {
	public AlienSS() throws SlickException {
		super();
		SpriteSheet alienss = new SpriteSheet(new Image("data/alien_ship.png"),
				53, 30, 3);
		currentAnimation = new Animation(alienss, 100);
		updateBox();
	}
}
package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Boss extends Enemy {

	public Boss() throws SlickException {
		super();
		init1();
	}

	public Boss(String ref) throws SlickException {
		init1();
	}

	public Boss(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		init1();
	}

	protected void init1() throws SlickException {
		Image spriteSheet = new Image("data/sprites/alien.png");
		aliveAnimation.addFrame(spriteSheet.getSubImage(1, 84, 149, 66), 100);
		aliveAnimation.addFrame(spriteSheet.getSubImage(157, 84, 149, 66), 100);
		aliveAnimation.addFrame(spriteSheet.getSubImage(305, 84, 115, 66), 100);
		aliveAnimation.addFrame(spriteSheet.getSubImage(429, 84, 123, 66), 100);
		aliveAnimation.addFrame(spriteSheet.getSubImage(562, 84, 149, 66), 100);
		aliveAnimation.addFrame(spriteSheet.getSubImage(719, 84, 140, 66), 100);
		aliveAnimation.addFrame(spriteSheet.getSubImage(869, 84, 114, 66), 100);
		aliveAnimation.addFrame(spriteSheet.getSubImage(993, 84, 122, 66), 100);
		currentAnimation = aliveAnimation;
		directionFacing = true;
		updateBox();
		// deathAnimation.addFrame(spriteSheet.getSubImage(142, 6, 32, 32)
		// .getScaledCopy(splashScale), 50);
		// deathAnimation.addFrame(spriteSheet.getSubImage(177, 6, 32, 32)
		// .getScaledCopy(splashScale), 50);
		// deathAnimation.addFrame(spriteSheet.getSubImage(212, 7, 32, 32)
		// .getScaledCopy(splashScale), 50);
		// deathAnimation.addFrame(spriteSheet.getSubImage(246, 7, 32, 32)
		// .getScaledCopy(splashScale), 50);
		// deathAnimation.addFrame(spriteSheet.getSubImage(280, 6, 32, 32)
		// .getScaledCopy(splashScale), 50);
		// deathAnimation.addFrame(spriteSheet.getSubImage(313, 6, 32, 32)
		// .getScaledCopy(splashScale), 50);
		// deathAnimation.addFrame(spriteSheet.getSubImage(346, 7, 32, 32)
		// .getScaledCopy(splashScale), 50);
		// deathAnimation.addFrame(spriteSheet.getSubImage(379, 7, 32, 32)
		// .getScaledCopy(splashScale), 50);
		// deathAnimation.setLooping(false);
		statDamage = 30;
		speed = .07f;
	}

	@Override
	public void update(int dt) {
		if (direction.x > 1 && !directionFacing) {
			directionFacing = true;
		} else if (direction.x < 1 && directionFacing) {
			directionFacing = false;
		}
	}

	@Override
	public void debugDraw(Graphics graphics) {
		super.debugDraw(graphics);
		graphics.drawString("Facing right" + directionFacing, 0, 0);
	}

	protected void init() {
		statDamage = 30;
		speed = .07f;
		health = 10000;
	}

	@Override
	protected int getMaxHP() {
		return 10000;
	}

	boolean directionFacing;
}

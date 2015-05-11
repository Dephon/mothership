package game2D;

import game2D.abstracts.*;
import game2D.collisions.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class PlayerManager extends Manager {

	public PlayerManager(Porygon bounds, int maxAmount) throws SlickException {
		super(bounds, maxAmount);
		for (int i = 0; i < maxAmount; i++) {
			entities.add(new Player());
			entities.get(i).setSpeed(.1f);
		}
		hitSound = new Sound("data/sounds/player_hurt.wav");
	}

	@Override
	public void add(Vector2f loc, Vector2f dir) {
		for (Entity temp : entities) {
			Player player = (Player) temp;
			if (count < maxCount) {
				player.create();
				player.setLoc(loc);
				player.setDir(dir);
				activeNdxs.add(ndx);
				count++;
				ndx++;
				if (ndx == maxCount)
					ndx = 0;
			}
		}
	}

	public void update(int playerNum, Vector2f dir, int dt) {
		try {
			Player player = (Player) entities.get(playerNum - 1);
			if (player.getOriginY() <= 0 && dir.y < 0)
				dir.y = 0;
			else if (player.getEndY() >= gameBounds.getHeight() && dir.y > 0)
				dir.y = 0;
			if (player.getOriginX() <= 0 && dir.x < 0)
				dir.x = 0;
			else if (player.getEndX() >= gameBounds.getWidth() && dir.x > 0)
				dir.x = 0;
			if (!player.isDead()) {
				player.update(dir, dt);
				player.updateAnimation(dir);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
	}

	public int getHealth(int player) {
		try {
			Player temp = (Player) (entities.get(player - 1));
			return temp.getHealth();
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	public Vector2f getCenter(int player) {
		try {
			Player temp = (Player) (entities.get(player - 1));
			return new Vector2f(temp.getCenterX(), temp.getCenterY());
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public float fireX(int player) {
		try {
			return entities.get(player - 1).getCenterX();
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	public float fireY(int player) {
		try {
			return entities.get(player - 1).getCenterY();
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	public boolean areDead() {
		for (Entity player : entities) {
			if (!player.isDead()) {
				return false;
			}
			return true;
		}
		return true;
	}

	@Override
	public void handleCollision(Entity entity, int collisionEnum, int damage) {
		Player player = (Player) entity;
		if (collisionEnum == CollisionEnum.DAMAGING && !player.isDamaged()) {
			hitSound.stop();
			hitSound.play();
		}
		if (collisionEnum == CollisionEnum.MEDPACK) {
			player.addHealth(20);
		}
		super.handleCollision(entity, collisionEnum, damage);
	}

}

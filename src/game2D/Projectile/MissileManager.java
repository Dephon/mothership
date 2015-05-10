package game2D.projectile;

import game2D.*;
import game2D.abstracts.*;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class MissileManager extends Manager {
	public MissileManager(Porygon bounds, int maxAmount) throws SlickException {
		super(bounds, maxAmount);
		for (int i = 0; i < maxAmount; i++)
			entities.add((Missile) AmmoFactory.getAmmo(AmmoEnum.MISSILE));
		sound = new Sound("data/sounds/Missile_Launch.wav");
		hitSound = new Sound("data/sounds/Missile_Explosion.wav");
	}

	public void add(Vector2f position, Vector2f direction) {
		Vector2f posAmmo = new Vector2f(position);
		float missleCenterX, missleCenterY;
		if (fireTimer > 200) {
			if (count < maxCount) {
				missleCenterX = entities.get(ndx).getCenterX();
				missleCenterY = entities.get(ndx).getCenterY();
				posAmmo.x -= missleCenterX;
				posAmmo.y -= missleCenterY;
				entities.get(ndx).create(posAmmo, direction);
				activeNdxs.add(ndx);
				count++;
				ndx++;
				fireTimer = 0;
				if (ndx == maxCount)
					ndx = 0;
				sound.stop();
				sound.play(1f, .4f);
			}
		}
	}

	@Override
	public void update(int dt) {
		for (Entity missile : entities) {
			if (!missile.isDead()) {
				missile.update(dt);
				if (!missile.intersects(gameBounds)) {
					missile.destroy();
					count--;
				}
			}
		}
		if (dt < Integer.MAX_VALUE)
			fireTimer += dt;
	}

	@Override
	public void displace(Entity second, int CollisionEnum) {
		boolean displaced;
		for (Entity missile : entities) {
			if (!missile.isDead()) {
				displaced = missile.displace(second);
				if (displaced) {
					handleCollision();
				}
			}
		}
	}

	@Override
	public void displace(Manager rhs, int collisionEnum) {
		boolean displaced;
		ArrayList<Entity> secondList;
		secondList = rhs.getActive();
		for (Entity missile : entities) {
			if (!missile.isDead()) {
				for (Entity second : secondList) {
					displaced = missile.displace(second, collisionEnum);
					if (displaced) {
						handleCollision();
					}
				}
			}
		}
	}

	@Override
	public void handleCollision() {
		sound.stop();
		hitSound.stop();
		hitSound.play(1f, .4f);
	}
}
package game2D.projectiles;

import game2D.*;
import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class BulletManager extends Manager {

	public BulletManager(Porygon bounds, int maxAmount) throws SlickException {
		super(bounds, maxAmount);
		for (int i = 0; i < maxAmount; i++)
			entities.add((Bullet) AmmoFactory.getAmmo(AmmoEnum.BULLET));
		sound = new Sound("data/sounds/Bullet_Shot.wav");
	}

	@Override
	public void create(Vector2f loc, Vector2f dir) {
		Vector2f posAmmo = new Vector2f(loc);
		if (fireTimer > 100) {
			if (count < maxCount) {
				posAmmo.x -= entities.get(ndx).getCenterX();
				posAmmo.y -= entities.get(ndx).getCenterY();
				entities.get(ndx).create(posAmmo, dir);
				activeNdxs.add(ndx);
				count++;
				ndx++;
				fireTimer = 0;
				if (ndx == maxCount)
					ndx = 0;
				sound.play(1f, .4f);
			}
		}
	}

	public int getBulletCount() {
		return count;
	}

	@Override
	public void update(int dt) {
		for (Entity bullet : entities) {
			if (!bullet.isDead()) {
				bullet.update(dt);
				if (!bullet.intersects(gameBounds)) {
					bullet.destroy();
					remove(bullet);
					count--;
				}
			} else {
				remove(bullet);
			}
		}
		if (dt < Integer.MAX_VALUE)
			fireTimer += dt;
	}

	@Override
	public void handleCollision(Entity entity, int collisionEnum, int damage) {
		entity.handleCollision(collisionEnum, damage);
	}

	public void testCreate() {

		if (count < maxCount) {
			activeNdxs.add(ndx);
			count++;
			ndx++;
			if (ndx == maxCount)
				ndx = 0;

		}
	}

	public BulletManager(Double numBullets, Double bulletType)
			throws SlickException {
		super(numBullets);
		for (int i = 0; i < numBullets.intValue(); i++) {
			entities.add((Bullet) AmmoFactory.getAmmo(bulletType));
		}
	}
}

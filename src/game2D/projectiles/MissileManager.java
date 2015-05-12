package game2D.projectiles;

import game2D.*;
import game2D.abstracts.*;

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

	public void create(Vector2f position, Vector2f direction) {
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
					remove(missile);
				}
			}
		}
		if (dt < Integer.MAX_VALUE)
			fireTimer += dt;
	}

	@Override
	public void handleCollision(Entity entity, int collisionEnum, int damage) {
		if (!entity.isDying()) {
			sound.stop();
			hitSound.stop();
			hitSound.play(1f, .4f);
			entity.handleCollision(collisionEnum, damage);
			remove(entity);
		}
	}
}
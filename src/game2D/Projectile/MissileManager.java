package game2D.Projectile;

import game2D.*;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class MissileManager implements Manager {
	public MissileManager(Porygon bounds, int maxAmount) throws SlickException {
		missiles = new ArrayList<Missile>();
		for (int i = 0; i < maxAmount; i++)
			missiles.add((Missile) AmmoFactory.getAmmo(AmmoEnum.MISSILE));
		gameBounds = bounds;
		fireTimer = 0;
		missileCount = 0;
		missileIndex = 0;
		maxCount = maxAmount;
		missileNdxs = new ArrayList<Integer>();
		missileHitSound = new Sound("data/sounds/Missile_Explosion.wav");
		missileSound = new Sound("data/sounds/Missile_Launch.wav");
	}

	public void add(Vector2f position, Vector2f direction) {
		Vector2f posAmmo = new Vector2f(position);
		float missleCenterX, missleCenterY;
		if (fireTimer > 200) {
			if (missileCount < maxCount) {
				missleCenterX = missiles.get(missileIndex).getCenterX();
				missleCenterY = missiles.get(missileIndex).getCenterY();
				posAmmo.x -= missleCenterX;
				posAmmo.y -= missleCenterY;
				missiles.get(missileIndex).create(posAmmo, direction);
				missileNdxs.add(missileIndex);
				missileCount++;
				missileIndex++;
				fireTimer = 0;
				if (missileIndex == maxCount)
					missileIndex = 0;
				missileSound.stop();
				missileSound.play(1f, .4f);
			}
		}
	}

	@Override
	public void destroy(int ndx) {
		// TODO Auto-generated method stub

	}

	public int getMissileCount() {
		return missileCount;
	}

	public int getMissileIndex() {
		return missileIndex;
	}

	public void update(int dt) {
		for (Missile missile : missiles) {
			if (!missile.isDead()) {
				missile.update(dt);
				if (!missile.intersects(gameBounds)) {
					missile.destroy();
					missileCount--;
				}
			}
		}
		if (dt < Integer.MAX_VALUE)
			fireTimer += dt;
	}

	public void draw() {
		for (Missile missile : missiles)
			missile.draw();
	}

	public void debugDraw(Graphics graphics) {
		for (Missile missile : missiles)
			missile.debugDraw(graphics);
	}

	public void displace(Entity second, int CollisionEnum) {
		boolean displaced;
		for (Missile missile : missiles) {
			if (!missile.isDead()) {
				displaced = missile.displace(second);
				if (displaced) {
					missileSound.stop();
					missileHitSound.stop();
					missileHitSound.play(1f, .4f);
				}
			}
		}
	}

	@Override
	public void displace(Manager second, int CollisionEnum) {

	}

	private ArrayList<Integer> missileNdxs;
	private ArrayList<Missile> missiles;
	private Porygon gameBounds;
	private int missileCount;
	private int missileIndex;
	private int fireTimer;
	private int maxCount;
	private Sound missileSound;
	private Sound missileHitSound;
}
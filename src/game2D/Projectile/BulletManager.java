package game2D.Projectile;

import game2D.*;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class BulletManager implements Manager {

	public BulletManager(Porygon bounds, int maxAmount) throws SlickException {
		bullets = new ArrayList<Bullet>();
		for (int i = 0; i < maxAmount; i++)
			bullets.add((Bullet) AmmoFactory.getAmmo(AmmoEnum.BULLET));
		gameBounds = bounds;
		fireTimer = 0;
		count = 0;
		ndx = 0;
		maxCount = maxAmount;
		activeNdxs = new ArrayList<Integer>();
		bulletSound = new Sound("data/sounds/Bullet_Shot.wav");
	}

	@Override
	public void add(Vector2f position, Vector2f direction) {
		Vector2f posAmmo = new Vector2f(position);
		if (fireTimer > 100) {
			if (count < maxCount) {
				posAmmo.x -= bullets.get(ndx).getCenterX();
				posAmmo.y -= bullets.get(ndx).getCenterY();
				bullets.get(ndx).create(posAmmo, direction);
				activeNdxs.add(ndx);
				count++;
				ndx++;
				fireTimer = 0;
				if (ndx == maxCount)
					ndx = 0;
				bulletSound.stop();
				bulletSound.play(1f, .4f);
			}
		}
	}

	public int getBulletCount() {
		return count;
	}

	@Override
	public void destroy(int ndx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int dt) {
		for (Bullet bullet : bullets) {
			if (!bullet.isDead()) {
				bullet.update(dt);
				if (!bullet.intersects(gameBounds)) {
					bullet.destroy();
					count--;
				}
			}
		}
		if (dt < Integer.MAX_VALUE)
			fireTimer += dt;
	}

	@Override
	public void draw() {
		for (Bullet bullet : bullets)
			bullet.draw();
	}

	@Override
	public void debugDraw(Graphics graphics) {
		for (Bullet bullet : bullets)
			bullet.debugDraw(graphics);
	}

	@Override
	public void displace(Entity second, int CollisionEnum) {
		boolean displaced;
		for (Bullet bullet : bullets) {
			if (!bullet.isDead()) {
				displaced = bullet.displace(second);
				if (displaced) {
					bulletSound.stop();
				}
			}
		}
	}

	@Override
	public void displace(Manager second, int CollisionEnum) {
		// TODO Auto-generated method stub
	}

	public ArrayList<Bullet> getActiveBullets() {
		ArrayList<Bullet> currentBullets = new ArrayList<Bullet>();
		for (int i = 0; i < activeNdxs.size(); i++)
			currentBullets.add(bullets.get(activeNdxs.get(i)));
		return currentBullets;
	}

	private int count;
	private int ndx;
	private int fireTimer;
	private int maxCount;
	private ArrayList<Integer> activeNdxs;
	private ArrayList<Bullet> bullets;
	private Porygon gameBounds;
	private Sound bulletSound;
}

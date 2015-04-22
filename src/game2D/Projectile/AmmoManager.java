package game2D.Projectile;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class AmmoManager {
	public AmmoManager(Rectangle gameBounds) throws SlickException {
		bullets = new ArrayList<Bullet>();
		missiles = new ArrayList<Missile>();
		for (int i = 0; i < 100; i++)
			bullets.add((Bullet) AmmoFactory.getAmmo(AmmoEnum.BULLET));
		for (int i = 0; i < 100; i++)
			missiles.add((Missile) AmmoFactory.getAmmo(AmmoEnum.MISSILE));
		laser = (Laser) AmmoFactory.getAmmo(AmmoEnum.LASER);
		this.gameBounds = gameBounds;
		currentAmmo = AmmoEnum.BULLET;
		fireTimer = 0;
		bulletCount = 0;
		missileCount = 0;
		missileIndex = 0;
		bulletIndex = 0;
	}

	public void add(Vector2f position, Vector2f direction) {
		Vector2f posAmmo = position;
		if (currentAmmo == AmmoEnum.BULLET && fireTimer > 100) {
			if (bulletCount < 100) {
				posAmmo.x -= bullets.get(bulletIndex).getCenterX();
				posAmmo.y -= bullets.get(bulletIndex).getCenterY();
				bullets.get(bulletIndex).revive(posAmmo, direction);
				bulletCount++;
				bulletIndex++;
				fireTimer = 0;
				if (bulletIndex == 100)
					bulletIndex = 0;
			}
		} else if (currentAmmo == AmmoEnum.MISSILE && fireTimer > 200) {

			if (missileCount < 100) {
				posAmmo.x -= missiles.get(missileIndex).getCenterX();
				posAmmo.y -= missiles.get(missileIndex).getCenterY();
				missiles.get(missileIndex).revive(posAmmo, direction);
				missileCount++;
				missileIndex++;
				fireTimer = 0;
				if (missileIndex == 100)
					missileIndex = 0;
			}
		} else if (currentAmmo == AmmoEnum.LASER && fireTimer > 1000) {

		}
	}

	public void update(int dt) {
		for (Bullet bullet : bullets) {
			if (!bullet.isDead()) {
				bullet.update(dt);
				if (!bullet.intersects(gameBounds)) {
					bullet.destroy();
					bulletCount--;
				}
			}
		}
		for (Missile missile : missiles) {
			if (!missile.isDead()) {
				missile.update(dt);
				if (!missile.intersects(gameBounds)) {
					missile.destroy();
					missileCount--;
				}
			}
		}
		laser.update(dt);
		if (dt < Integer.MAX_VALUE)
			fireTimer += dt;
	}

	public int getMissileCount() {
		return missileCount;
	}

	public int getMissileIndex() {
		return missileIndex;
	}

	public void draw() {
		for (Bullet bullet : bullets)
			bullet.draw();
		for (Missile missile : missiles)
			missile.draw();
		laser.draw();

	}

	public void changeAmmo(int AmmoEnum) {
		currentAmmo = AmmoEnum;
	}

	public int getAmmo() {
		return currentAmmo;
	}

	private ArrayList<Bullet> bullets;
	private ArrayList<Missile> missiles;
	private Laser laser;
	private Rectangle gameBounds;
	private int bulletCount;
	private int bulletIndex;
	private int missileCount;
	private int missileIndex;
	private int currentAmmo;
	private int fireTimer;
}
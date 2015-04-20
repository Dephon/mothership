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
	}

	public void add(Vector2f position, Vector2f direction) {
		if (currentAmmo == AmmoEnum.BULLET && fireTimer > 100) {
			bullets.get(bulletCount).revive(position, direction);
			bulletCount++;
			fireTimer = 0;
		} else if (currentAmmo == AmmoEnum.MISSILE && fireTimer > 200) {
			missiles.get(missileCount).revive(position, direction);
			missileCount++;
			fireTimer = 0;
		} else if (currentAmmo == AmmoEnum.LASER && fireTimer > 1000) {

		}
	}

	public void update(int dt) {
		for (Bullet bullet : bullets) {
			bullet.update(dt);
			if (!bullet.isDead()) {
				if (!bullet.intersects(gameBounds)) {
					bullet.destroy();
					bulletCount--;
				}
			}
		}
		for (Missile missile : missiles) {
			missile.update(dt);
			if (!missile.isDead()) {
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

	public int getBulletCount() {
		return bulletCount;
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

	private ArrayList<Bullet> bullets;
	private ArrayList<Missile> missiles;
	private Laser laser;
	private Rectangle gameBounds;
	private int bulletCount;
	private int missileCount;
	private int currentAmmo;
	private int fireTimer;
}

// tempAmmo = AmmoFactory.getAmmo(AmmoEnum.MISSILE);
// tempAmmo.setLoc(new Vector2f(player.getCenterX()
// - tempAmmo.getCenterX(), player.getCenterY()
// - tempAmmo.getCenterY()));
// tempAmmo.setDirection(pVector);
// ammo.add(tempAmmo);
// }
// for (int i = 0; i < ammo.size(); i++) {
// if (ammo.get(i).getEndX() < 0 || ammo.get(i).getEndY() < 0) {
// ammo.remove(i);
// i--;
// } else if (ammo.get(i).getOriginX() > container.getWidth()
// || ammo.get(i).getOriginY() > container.getHeight()) {
// ammo.remove(i);
// i--;
// } else {
// ammo.get(i).update(dt);
// }


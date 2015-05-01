package game2D.Projectile;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class AmmoFactory {
	public static Ammo getAmmo(int ammoEnum) throws SlickException {
		if (ammoEnum == AmmoEnum.BULLET) {
			return new Bullet();
		} else if (ammoEnum == AmmoEnum.MISSILE) {
			return new Missile();
		} else if (ammoEnum == AmmoEnum.LASER) {
			return new Laser();
		} else {
			return null;
		}
	}

	public static Ammo getAmmo(int ammoEnum, float x, float y)
			throws SlickException {
		if (ammoEnum == AmmoEnum.BULLET) {
			return new Bullet(x, y);
		} else if (ammoEnum == AmmoEnum.MISSILE) {
			return new Missile(x, y);
		} else if (ammoEnum == AmmoEnum.LASER) {
			return new Laser(x, y);
		} else {
			return null;
		}
	}

	public static Ammo getAmmo(int ammoEnum, int x, int y)
			throws SlickException {
		if (ammoEnum == AmmoEnum.BULLET) {
			return new Bullet(x, y);
		} else if (ammoEnum == AmmoEnum.MISSILE) {
			return new Missile(x, y);
		} else if (ammoEnum == AmmoEnum.LASER) {
			return new Laser(x, y);
		} else {
			return null;
		}
	}

	public static Ammo getAmmo(int ammoEnum, float x, float y,
			Vector2f direction) throws SlickException {
		if (ammoEnum == AmmoEnum.BULLET) {
			return new Bullet(x, y, direction);
		} else if (ammoEnum == AmmoEnum.MISSILE) {
			return new Missile(x, y, direction);
		} else if (ammoEnum == AmmoEnum.LASER) {
			return new Laser(x, y, direction);
		} else {
			return null;
		}
	}

	public static Ammo getAmmo(int ammoEnum, int x, int y, Vector2f direction)
			throws SlickException {
		if (ammoEnum == AmmoEnum.BULLET) {
			return new Bullet(x, y, direction);
		} else if (ammoEnum == AmmoEnum.MISSILE) {
			return new Missile(x, y, direction);
		} else if (ammoEnum == AmmoEnum.LASER) {
			return new Laser(x, y, direction);
		} else {
			return null;
		}
	}
}
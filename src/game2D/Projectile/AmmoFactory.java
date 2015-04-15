package game2D.Projectile;

import org.newdawn.slick.*;

public class AmmoFactory {
	public static Ammo getAmmo(int ammoEnum) throws SlickException {
		if (ammoEnum == AmmoEnum.BULLET) {
			return new Bullet();
		} else if (ammoEnum == AmmoEnum.MISSLE) {
			return new Missle();
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
		} else if (ammoEnum == AmmoEnum.MISSLE) {
			return new Missle(x, y);
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
		} else if (ammoEnum == AmmoEnum.MISSLE) {
			return new Missle(x, y);
		} else if (ammoEnum == AmmoEnum.LASER) {
			return new Laser(x, y);
		} else {
			return null;
		}
	}
}

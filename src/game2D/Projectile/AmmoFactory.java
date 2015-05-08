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

	public static Ammo getAmmo(int ammoEnum, float locX, float locY)
			throws SlickException {
		if (ammoEnum == AmmoEnum.BULLET) {
			return new Bullet(new Vector2f(locX, locY));
		} else if (ammoEnum == AmmoEnum.MISSILE) {
			return new Missile(new Vector2f(locX, locY));
		} else if (ammoEnum == AmmoEnum.LASER) {
			return new Laser(new Vector2f(locX, locY));
		} else {
			return null;
		}
	}

	public static Ammo getAmmo(int ammoEnum, float locX, float locY,
			Vector2f direction) throws SlickException {
		if (ammoEnum == AmmoEnum.BULLET) {
			return new Bullet(new Vector2f(locX, locY), direction);
		} else if (ammoEnum == AmmoEnum.MISSILE) {
			return new Missile(new Vector2f(locX, locY), direction);
		} else if (ammoEnum == AmmoEnum.LASER) {
			return new Laser(new Vector2f(locX, locY), direction);
		} else {
			return null;
		}
	}
}
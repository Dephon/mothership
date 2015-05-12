package game2D.projectiles;

import game2D.abstracts.*;

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
			float dirX, float dirY) throws SlickException {
		if (ammoEnum == AmmoEnum.BULLET) {
			return new Bullet(new Vector2f(locX, locY),
					new Vector2f(dirX, dirY));
		} else if (ammoEnum == AmmoEnum.MISSILE) {
			return new Missile(new Vector2f(locX, locY), new Vector2f(dirX,
					dirY));
		} else if (ammoEnum == AmmoEnum.LASER) {
			return new Laser(new Vector2f(locX, locY), new Vector2f(dirX, dirY));
		} else {
			return null;
		}
	}

	public static Ammo getAmmo(Double ammoEnum) throws SlickException {
		if (ammoEnum.intValue() == AmmoEnum.BULLET) {
			return new Bullet(ammoEnum);
		} else {
			return null;
		}
	}
}
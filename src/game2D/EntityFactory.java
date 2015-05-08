package game2D;

import game2D.Projectile.*;

import org.newdawn.slick.*;

public class EntityFactory {
	public static Entity getAmmo(int ammoEnum) throws SlickException {
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
}

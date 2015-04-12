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
}

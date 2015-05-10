package game2D;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class EnemyManager extends Manager {
	public EnemyManager(Porygon bounds, int maxAmount) throws SlickException {
		super(bounds, maxAmount);
		for (int i = 0; i < maxAmount; i++)
			entities.add(new Enemy("data/Alien_PlaceHolder.gif"));
	}

	@Override
	public void add(Vector2f loc, Vector2f dir) {
		Enemy alien = (Enemy) entities.get(ndx);
		if (count < maxCount) {
			alien.create();
			alien.setLoc(loc);
			alien.setDir(dir);
			activeNdxs.add(ndx);
			count++;
			ndx++;
			if (ndx == maxCount)
				ndx = 0;
		}
	}

	@Override
	public void update(int dt) {
		for (Entity alien : entities) {
			if (!alien.isDead()) {
				alien.update(dt);
			}
		}
	}

	@Override
	public void handleCollision() {
		// TODO Auto-generated method stub
	}
}

package game2D;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class EnemyManager implements Manager {
	public EnemyManager(Rectangle bounds, int maxAmount) throws SlickException {
		aliens = new ArrayList<Enemy>();
		activeAliens = new ArrayList<Integer>();
		for (int i = 0; i < maxAmount; i++)
			aliens.add(new Enemy("data/Alien_PlaceHolder.gif"));
		gameBounds = bounds;
		alienCount = 0;
		alienIndex = 0;
		maxCount = maxAmount;
	}

	public void add(Vector2f loc, Vector2f dir) {
		Enemy alien = aliens.get(alienIndex);
		if (alienCount < maxCount) {
			alien.create();
			alien.setLoc(loc);
			alien.setDir(dir);
			alienCount++;
			alienIndex++;
			if (alienIndex == maxCount)
				alienIndex = 0;
		}
	}

	public void update(int dt) {
		for (Enemy alien : aliens) {
			if (!alien.isDead()) {
				alien.update(dt);
			}
		}
	}

	@Override
	public void destroy(int ndx) {
		aliens.get(ndx).destroy();
	}

	public void draw() {
		for (Enemy alien : aliens)
			alien.draw();
	}

	public void debugDraw(Graphics graphics) {
		for (Enemy alien : aliens)
			alien.debugDraw(graphics);
	}

	@Override
	public void displace(Entity second, int collisionEnum) {
		for (Enemy alien : aliens) {
			if (!alien.isDead()) {
				alien.displace(second, collisionEnum);
			}
		}
	}

	// public boolean displace(Entity rhs) {
	// Vector2f dis = Collision.intersects(this, rhs);
	// if (dis.x == 0 && dis.y == 0) {
	// return false;
	// } else {
	// location.add(dis);
	// box.setLocation(location);
	// handleCollision();
	// return true;
	// }
	// }

	@Override
	public void displace(Manager second, int CollisionEnum) {
		Vector2f displacement;
		for (Enemy alien : aliens) {
			if (!alien.isDead()) {
				// for(int i = 0; )
				// Collision.intersects(alien);
			}
		}
	}

	ArrayList<Integer> activeAliens;
	ArrayList<Enemy> aliens;
	private Rectangle gameBounds;
	private int alienCount;
	private int alienIndex;
	private int maxCount;
}

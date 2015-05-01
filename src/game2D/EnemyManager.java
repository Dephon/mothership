package game2D;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class EnemyManager {
	public EnemyManager(Rectangle gameBounds) throws SlickException {
		aliens = new ArrayList<Enemy>();
		for (int i = 0; i < 100; i++)
			aliens.add(new Enemy(""));
		this.gameBounds = gameBounds;
		alienCount = 0;
		alienIndex = 0;
	}

	public void add(Vector2f position) {
		// Vector2f posEnemy = new Vector2f(position);
		if (alienCount < 100) {
			aliens.get(alienIndex).create();
			alienCount++;
			alienIndex++;
			if (alienIndex == 100)
				alienIndex = 0;
		}
	}

	public void update(int dt) {
		for (Enemy alien : aliens) {
			if (!alien.isDead()) {
				// alien.update(dt);
				if (alien.intersects(gameBounds)) {
					alien.destroy();
					alienCount--;
				}
			}
		}
	}

	public void draw() {
		for (Enemy alien : aliens)
			alien.draw();
	}

	public void debugDraw(Graphics graphics) {
		for (Enemy alien : aliens)
			alien.debugDraw(graphics);
	}

	ArrayList<Enemy> aliens;
	private Rectangle gameBounds;
	private int alienCount;
	private int alienIndex;
}

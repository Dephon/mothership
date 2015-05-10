package game2D;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class ObstacleManager extends Manager {

	public ObstacleManager(Porygon bounds, int maxAmount) throws SlickException {
		super(bounds, maxAmount);
		for (int i = 0; i < maxAmount; i++)
			entities.add(new Obstacle());
	}

	@Override
	public void add(Vector2f position, Vector2f size) {
		Obstacle obstacle = (Obstacle) entities.get(ndx);
		if (count < maxCount) {
			obstacle.convertToInvObject(position.x, position.y, size.x, size.y);
			activeNdxs.add(ndx);
			count++;
			ndx++;
			if (ndx == maxCount)
				ndx = 0;
		}
	}

	public void add(String ref, Vector2f position, Vector2f size) {

	}

	@Override
	public void handleCollision() {

	}

}

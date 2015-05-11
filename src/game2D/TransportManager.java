package game2D;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class TransportManager extends Manager {

	public TransportManager(Porygon bounds, int maxAmount)
			throws SlickException {
		super(bounds, maxAmount);
		for (int i = 0; i < maxAmount; i++)
			entities.add(new Transport());
	}

	@Override
	public void add(Vector2f pos, Vector2f size) {
		Obstacle obstacle = (Obstacle) entities.get(ndx);
		if (count < maxCount) {
			obstacle.convertToInvObject(pos.x, 0, size.x, size.y);
			if (pos.y > 0)
				obstacle.setY(pos.y); // I hate slick2d so much
			activeNdxs.add(ndx);
			count++;
			ndx++;
			if (ndx == maxCount)
				ndx = 0;
		}

	}

	@Override
	public void handleCollision(Entity entity, int collisionEnum, int damage) {
	}

}

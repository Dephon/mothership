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
	public void create(Vector2f pos, Vector2f size) {
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

	public void add(String ref, Vector2f position, Vector2f size) {

	}

	@Override
	public void handleCollision(Entity entity, int collisionEnum, int damage) {
		entity.handleCollision(collisionEnum, damage);
	}
}

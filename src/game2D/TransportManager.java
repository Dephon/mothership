package game2D;

import game2D.abstracts.*;
import game2D.collisions.*;

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
		Transport trans = (Transport) entities.get(ndx);
		if (count < maxCount) {
			trans.convertToInvObject(pos.x, 0, size.x, size.y);
			if (pos.y > 0)
				trans.setY(pos.y); // I hate slick2d so much
			activeNdxs.add(ndx);
			count++;
			ndx++;
			if (ndx == maxCount)
				ndx = 0;
		}
	}

	public void add(float posX, float posY, float sizeX, float sizeY,
			int threeStateEnum) {
		Transport trans = (Transport) entities.get(ndx);
		if (count < maxCount) {
			trans.convertToInvObject(posX, 0, sizeX, sizeY);
			if (posY > 0)
				trans.setY(posY); // I hate slick2d so much
			activeNdxs.add(ndx);
			trans.setMapSide(threeStateEnum);
			count++;
			ndx++;
			if (ndx == maxCount)
				ndx = 0;
		}
	}

	@Override
	public void handleCollision(Entity entity, int collisionEnum, int damage) {
		if (collisionEnum == CollisionEnum.TRANSPORTING) {

		}
	}

}

package game2D;

import game2D.abstracts.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Transport extends Immovable {
	public Transport() {
		super();
	}

	public Transport(String ref) throws SlickException {
		super(ref);
	}

	public Transport(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
	}

	public Transport(float locX, float locY, float width, float height)
			throws SlickException {
		super(locX, locY, width, height);
		location.set(locX, locY);
		makeBox(locX, locY, width, height);
		invisible = true;
		dead = false;
	}

	public void convertToInvObject(float locX, float locY, float width,
			float height) {
		location.set(locX, locY);
		makeBox(locX, locY, width, height);
		invisible = true;
		dead = false;
	}

	public void setMapSide(int threeStateEnum) {
		mapSide = threeStateEnum;
	}

	public int getMapSide() {
		return mapSide;
	}

	protected int mapSide;
}
package game2D.Collision;

import game2D.*;

import java.util.*;

import org.newdawn.slick.*;

class Bucket {
	// Collision type for tile
	// private Collision tileCollision;
	// Entities that occupy the tile.
	private Obstacle obstacle;
	private ArrayList<Entity> occupants;
	private boolean obstacleExists;

	public Bucket(boolean obstExists) throws SlickException {
		obstacle = new Obstacle("data/InvObs.png");
		occupants = new ArrayList<Entity>();
		obstacleExists = obstExists;
	}

	public void add(Entity ent) {
		occupants.add(ent);
	}

	public void clear() {
		occupants.clear();
	}

	public void draw() {
		if (obstacleExists)
			obstacle.draw();
	}

	// public void setCollision(Collision collision) {
	// tileCollision = collision;
	// }
}
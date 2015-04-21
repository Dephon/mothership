package game2D.Collision;

import game2D.*;
import game2D.Map;

import java.util.*;

public class CollisionMap {

	private Bucket[][] grid;
	private int gridWidth, gridHeight;

	public CollisionMap(Map map) {
		gridWidth = map.getTiledMap().getWidth();
		gridHeight = map.getTiledMap().getHeight();
		grid = new Bucket[gridWidth][gridHeight];

		// set the collision type for the cell
		for (int i = 0; i < map.getTiledMap().getHeight(); i++) {
			for (int j = 0; j < map.getTiledMap().getWidth(); j++) {
				if (map.getTiledMap()
						.getTileProperty(map.getTiledMap().getTileId(i, j, 0),
								"isBlocked", "no property").equals("true")) {
					grid[i][j].setCollision(new BlockedMovement());
				}// Can add more cases once more collision types have been
					// implemented.
			}
		}
	}

	class Bucket {
		private Collision tileCollision;
		private ArrayList<Entity> occupants;

		Bucket() {

		}

		public void setCollision(Collision collision) {
			tileCollision = collision;
		}
	}
}

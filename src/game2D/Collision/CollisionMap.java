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

	public void addEntity(Entity ent) {
		Bucket[] cellsOccupied = hashEntity(ent);
		for (Bucket x : cellsOccupied) {
			x.add(ent);
		}
	}

	// Haven't tested this yet, so it might be completely buggy
	public Bucket[] hashEntity(Entity ent) {
		ArrayList<Bucket> cellsOccupied = new ArrayList<Bucket>();
		for (int i = (int) ent.getOriginX() / gridWidth; i <= (int) ent
				.getEndX() / gridWidth; i++) {
			for (int j = (int) ent.getOriginY() / gridHeight; j <= (int) ent
					.getEndY(); j++) {
				cellsOccupied.add(grid[i][j]);
			}
		}
		return (Bucket[]) cellsOccupied.toArray();
	}

	// Want a list of entities.
	public void fillGrid() {

	}

	public void clearGrid() {
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridHeight; j++) {
				grid[i][j].clear();
			}
		}
	}

	class Bucket {
		// Collision type for tile
		private Collision tileCollision;
		// Entities that occupy the tile.
		private ArrayList<Entity> occupants;

		Bucket() {
			occupants = new ArrayList<Entity>();
		}

		public void add(Entity ent) {
			occupants.add(ent);
		}

		public void clear() {
			occupants.clear();
		}

		public void setCollision(Collision collision) {
			tileCollision = collision;
		}
	}
}

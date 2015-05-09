package game2D.Collision;

import game2D.*;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.tiled.*;

public class CollisionMap {
	private TiledMap map;
	Audio backgroundMusic;
	private Bucket[][] grid;
	private int gridWidth;
	private int gridHeight;

	public CollisionMap(String ref) throws SlickException {
		map = new TiledMap(ref);
		gridWidth = map.getWidth();
		gridHeight = map.getHeight();
		grid = new Bucket[gridWidth][gridHeight];
		boolean isBlocked;

		// set the collision type for the cell
		for (int i = 0; i < gridHeight; i++) {
			for (int j = 0; j < gridWidth; j++) {
				isBlocked = map.getTileProperty(map.getTileId(i, j, 0),
						"isBlocked", "no property").equals("true");
				grid[i][j] = new Bucket(isBlocked);
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
		// Place every entity that's alive in here
	}

	public void clearGrid() {
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridHeight; j++) {
				grid[i][j].clear();
			}
		}
	}

	public void render() {
		map.render(0, 0);
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridHeight; j++) {
				grid[i][j].draw();
			}
		}
	}
}
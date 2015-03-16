package game2D;

import org.newdawn.slick.*;

//Needs to be fixed
public class TiledMap {
	public TiledMap(Image tile, int width, int height) {
		this.width = width;
		this.height = height;
		this.tile = tile;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Image getTile() {
		return tile;
	}

	public void draw() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 10; j++) {
				tile.draw(i * 45, j * 45, 45, 45);
			}
		}
	}

	private int width;
	private int height;
	private Image tile;
}

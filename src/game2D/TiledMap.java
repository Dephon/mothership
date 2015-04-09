package game2D;

import org.newdawn.slick.*;

//Needs to be fixed
public class TiledMap {
	public TiledMap(Image tile, int x, int y, int width, int height) {
		this.originX = x;
		this.originY = y;
		this.width = width;
		this.height = height;
		this.tile = tile;
	}

	public TiledMap(Image tile, int width, int height) {
		this.originX = 0;
		this.originY = 0;
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

	public int getOriginX() {
		return originX;
	}

	public int getOriginY() {
		return originY;
	}

	public void draw() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 10; j++) {
				tile.draw(i * 45 + originX, j * 45 + originY, 45, 45);
			}
		}
	}

	private int originX;
	private int originY;
	private int width;
	private int height;
	private Image tile;
}

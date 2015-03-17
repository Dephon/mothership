package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Sprite extends Image {
	public Sprite(String ref) throws SlickException {
		super(ref);
		location = new Vector2f(0, 0);
		rect = new Rectangle(0, 0, this.width, this.height);
	}

	public Sprite(String ref, float x, float y) throws SlickException {
		super(ref);
		location = new Vector2f(x, y);
		rect = new Rectangle(x, y, this.width, this.height);
	}

	public Sprite(String ref, int x, int y) throws SlickException {
		super(ref);
		location = new Vector2f(x, y);
		rect = new Rectangle(x, y, this.width, this.height);
	}

	public float getX() {
		return location.x;
	}

	public float getY() {
		return location.y;
	}

	public Rectangle getRectangle() {
		return rect;
	}

	public void setX(float x) {
		location.x = x;
		rect.setX(x);
	}

	public void setY(float y) {
		location.y = y;
		rect.setY(y);
	}

	public boolean intersects(Sprite rhs) {
		return rect.intersects(rhs.getRectangle());
	}

	public void draw() {
		this.draw(rect.getX(), rect.getY());
	}

	public void update(Vector2f movement) {
		location.add(movement);
		rect.setLocation(location);
	}

	Rectangle rect;
	Vector2f location;
}
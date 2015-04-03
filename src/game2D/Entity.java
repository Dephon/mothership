package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Entity {
	public Entity(String ref) throws SlickException {
		sprite = new Image(ref);
		location = new Vector2f(0, 0);
		rect = new Rectangle(location.x, location.y, sprite.getWidth(),
				sprite.getHeight());
	}

	public Entity(String ref, float x, float y) throws SlickException {
		sprite = new Image(ref);
		location = new Vector2f(x, y);
		rect = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
	}

	public Entity(String ref, int x, int y) throws SlickException {
		sprite = new Image(ref);
		location = new Vector2f(x, y);
		rect = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
	}

	public float getOriginX() {
		return location.x;
	}

	public float getOriginY() {
		return location.y;
	}

	public float getEndX() {
		return location.x + sprite.getWidth();
	}

	public float getEndY() {
		return location.y + sprite.getHeight();
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

	public boolean intersects(Entity rhs) {
		return rect.intersects(rhs.getRectangle());
	}

	public void draw() {
		sprite.draw(rect.getX(), rect.getY());
	}

	public void update(Vector2f movement) {
		location.add(movement);
		rect.setLocation(location);
	}

	Image sprite;
	Rectangle rect;
	Vector2f location;
}
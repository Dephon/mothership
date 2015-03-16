package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Sprite {
	public Sprite(String imageLink, int x, int y) throws SlickException {
		image = new Image(imageLink);
		location = new Vector2f(x, y);
		rect = new Rectangle(location.x, location.y, image.getWidth(),
				image.getHeight());
	}

	public Sprite(Image image, int x, int y) {
		this.image = image;
		location = new Vector2f(x, y);
		rect = new Rectangle(location.x, location.y, image.getWidth(),
				image.getHeight());
	}

	public Image getImage() {
		return image;
	}

	public float getX() {
		return rect.getX();
	}

	public float getY() {
		return rect.getY();
	}

	public float getWidth() {
		return rect.getWidth();
	}

	public float getHeight() {
		return rect.getHeight();
	}

	public Rectangle getRectangle() {
		return rect;
	}

	public void setX(float x) {
		rect.setX(x);
	}

	public void setY(float y) {
		rect.setY(y);
	}

	public void draw() {
		image.draw(rect.getX(), rect.getY());
	}

	public boolean intersects(Sprite rhs) {
		return rect.intersects(rhs.getRectangle());
	}

	public void update(Vector2f movement, int dt) {

	}

	Image image;
	Rectangle rect;
	Vector2f location;
}

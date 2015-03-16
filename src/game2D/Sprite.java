package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Sprite {
	Sprite(String imageLink) throws SlickException {
		image = new Image(imageLink);

	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public Vector2f getLocation() {
		return location;
	}

	public void setLocation(Vector2f location) {
		this.location = location;
	}

	Image image;
	Rectangle rect;
	Vector2f location;
}

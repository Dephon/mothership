package game2D;

import game2D.Collision.*;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

/*
 * TODO: Convert "Rectangle" to "Polygon"
 */

public abstract class Entity {

	public Entity(String ref) throws SlickException {
		sprite = new Image(ref);
		location = new Vector2f(0, 0);
		rect = new Rectangle(0, 0, sprite.getWidth(), sprite.getHeight());
		dead = true;
	}

	public Entity(String ref, float x, float y) throws SlickException {
		sprite = new Image(ref);
		location = new Vector2f(x, y);
		rect = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
		dead = true;
	}

	public Entity(String ref, Vector2f loc) throws SlickException {
		sprite = new Image(ref);
		location.set(loc);
		rect = new Rectangle(location.x, location.y, sprite.getWidth(),
				sprite.getHeight());
		dead = true;
	}

	public float getOriginX() {
		return location.x;
	}

	public float getOriginY() {
		return location.y;
	}

	public float getEndX() {
		return rect.getMaxX();
	}

	public float getEndY() {
		return rect.getMaxY();
	}

	public float getCenterX() {
		return rect.getCenterX();
	}

	public float getCenterY() {
		return rect.getCenterY();
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

	public void setLoc(Vector2f loc) {
		location.set(loc);
		rect.setLocation(location);
	}

	public void setLoc(float x, float y) {
		location.x = x;
		location.y = y;
		rect.setLocation(location);
	}

	public boolean isDead() {
		return dead;
	}

	public boolean intersects(Entity rhs) {
		return rect.intersects(rhs.getRectangle());
	}

	public boolean intersects(Rectangle rhs) {
		return rect.intersects(rhs);
	}

	public void create() {
		dead = false;
	}

	public void destroy() {
		dead = true;
		setLoc(0, 0);
	}

	public void draw() {
		if (!dead)
			sprite.draw(rect.getX(), rect.getY());
	}

	public void debugDraw(Graphics graphics) {
		if (!dead) {
			graphics.draw(rect);
			sprite.draw(rect.getX(), rect.getY());
		}
	}

	public void update(Vector2f movement) {
		if (!dead) {
			location.add(movement);
			rect.setLocation(location);
		}
	}

	public abstract void handleCollisions();

	Queue<Collision> collisions;
	protected boolean dead;
	protected Image sprite;
	protected Rectangle rect;
	protected Vector2f location;
}
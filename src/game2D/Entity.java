package game2D;

import game2D.Collision.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Entity {

	public Entity(String ref) throws SlickException {
		sprite = new Image(ref);
		location = new Vector2f(0, 0);
		box = makeBox(0, 0, sprite.getWidth(), sprite.getHeight());
		dead = true;
	}

	public Entity(String ref, float x, float y) throws SlickException {
		sprite = new Image(ref);
		location = new Vector2f(x, y);
		box = makeBox(x, y, sprite.getWidth(), sprite.getHeight());
		dead = true;
	}

	public Entity(String ref, Vector2f loc) throws SlickException {
		sprite = new Image(ref);
		location.set(loc);
		box = makeBox(location.x, location.y, sprite.getWidth(),
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
		return box.getMaxX();
	}

	public float getEndY() {
		return box.getMaxY();
	}

	public float getCenterX() {
		return box.getCenterX();
	}

	public float getCenterY() {
		return box.getCenterY();
	}

	public Porygon getPolygon() {
		return box;
	}

	public void setX(float x) {
		location.x = x;
		box.setX(x);
	}

	public void setY(float y) {
		location.y = y;
		box.setY(y);
	}

	public void setLoc(Vector2f loc) {
		location.set(loc);
		box.setLocation(location);
	}

	public void setLoc(float x, float y) {
		location.x = x;
		location.y = y;
		box.setLocation(location);
	}

	public boolean isDead() {
		return dead;
	}

	public void displace(Entity rhs) {
		location.add(Collision.intersects(this, rhs));
		box.setLocation(location);
	}

	public boolean intersects(Entity rhs) {
		if (Collision.intersects(this, rhs) == null)
			return false;
		else
			return true;
	}

	public boolean intersects(Rectangle rhs) {
		return box.intersects(rhs);
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
			sprite.draw(box.getX(), box.getY());
	}

	public void debugDraw(Graphics graphics) {
		if (!dead) {
			graphics.draw(box);
			sprite.draw(box.getX(), box.getY());
		}
	}

	public void update(Vector2f movement) {
		if (!dead) {
			location.add(movement);
			box.setLocation(location);
		}
	}

	private Porygon makeBox(float x, float y, float width, float height) {
		float[][] points = { { x, y }, { width, y }, { width, height },
				{ x, height } };
		Porygon temp = new Porygon();
		for (float[] i : points)
			temp.addPoint(i[0], i[1]);
		return temp;
	}

	public abstract void handleCollisions();

	protected boolean dead;
	protected Image sprite;
	protected Porygon box;
	protected Vector2f location;
	protected Animation currentAnimation;
}
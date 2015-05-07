package game2D;

import game2D.Collision.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Entity {

	public Entity(String ref) throws SlickException {
		sprite = new Image(ref);
		location = new Vector2f(0, 0);
		direction = new Vector2f(0, 0);
		box = makeBox(0, 0, sprite.getWidth(), sprite.getHeight());
		speed = 0;
		dead = true;
	}

	public Entity(String ref, Vector2f loc) throws SlickException {
		sprite = new Image(ref);
		location = new Vector2f(0, 0);
		direction = new Vector2f(0, 0);
		location.set(loc);
		box = makeBox(loc.x, loc.y, sprite.getWidth(), sprite.getHeight());
		speed = 0;
		dead = true;
	}

	public Entity(String ref, Vector2f loc, Vector2f dir) throws SlickException {
		sprite = new Image(ref);
		location = new Vector2f(0, 0);
		direction = new Vector2f(0, 0);
		location.set(loc);
		direction.set(dir);
		box = makeBox(loc.x, loc.y, sprite.getWidth(), sprite.getHeight());
		speed = 0;
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

	public void setSpeed(float sp) {
		speed = sp;
	}

	public float getSpeed() {
		return speed;
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

	public void setDir(Vector2f dir) {
		direction.set(dir);
	}

	public void setDir(float x, float y) {
		direction.set(x, y);
	}

	public void setVelocity(Vector2f dir, int speed) {
		direction.set(dir);
		this.speed = speed;
	}

	public boolean isDead() {
		return dead;
	}

	public boolean displace(Entity rhs) {
		Vector2f dis = Collision.intersects(this, rhs);
		if (dis.x == 0 && dis.y == 0) {
			return false;
		} else {
			location.add(dis);
			box.setLocation(location);
			return true;
		}
	}

	public boolean intersects(Entity rhs) {
		Vector2f test = Collision.intersects(this, rhs);
		if (test.x == 0 && test.y == 0)
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

	public void create(Vector2f loc) {
		dead = false;
		setLoc(loc);
	}

	public void create(Vector2f loc, Vector2f dir) {
		dead = false;
		setLoc(loc);
		direction.set(dir);
	}

	public void destroy() {
		if (!dead) {
			dead = true;
			speed = 0;
			direction.x = 0;
			direction.y = 0;
			setLoc(0, 0);
		}
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

	public void update(int dt) {
		if (!dead) {
			Vector2f dV = new Vector2f();
			dV.set(direction);
			dV.x *= speed * dt;
			dV.y *= speed * dt;
			location.add(dV);
			box.setLocation(location);
		}
	}

	public void update(Vector2f dir, int dt) {
		if (!dead) {
			Vector2f dV = new Vector2f();
			direction.set(dir);
			dV.set(dir);
			dV.x *= speed * dt;
			dV.y *= speed * dt;
			location.add(dV);
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

	public Vector2f getDirection() {
		return direction;
	}

	public void setDirection(Vector2f dir) {
		direction.set(dir);
	}

	public abstract void handleCollisions();

	/**
	 * Rotates both the Image and Polygon with respect to the x axis
	 * 
	 * @param reverse
	 *            if true the rotation is counter clockwise, otherwise clockwise
	 *
	 **/
	protected void rotate(boolean reverse) {
		float theta;

		if (reverse)
			theta = (float) (360 - direction.getTheta());
		else
			theta = (float) direction.getTheta();

		sprite.rotate(theta);
		box.rotate(theta);
	}

	protected boolean dead;
	protected float speed;
	protected Image sprite;
	protected Porygon box;
	protected Vector2f direction;
	protected Vector2f location;
	protected Animation currentAnimation;
}
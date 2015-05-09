package game2D;

import game2D.Collision.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Entity {

	public Entity() {
		currentAnimation = new Animation();
		deathAnimation = new Animation();
		location = new Vector2f();
		direction = new Vector2f();
		speed = 0;
		dead = true;
		dying = false;
		dyingTimer = 0;
	}

	public Entity(String ref) throws SlickException {
		currentAnimation = new Animation();
		currentAnimation.addFrame(new Image(ref), 1000);
		deathAnimation = new Animation();
		location = new Vector2f();
		direction = new Vector2f();
		updateBox();
		speed = 0;
		dead = true;
		dying = false;
		dyingTimer = 0;
	}

	public Entity(String ref, Vector2f loc) throws SlickException {
		currentAnimation = new Animation();
		currentAnimation.addFrame(new Image(ref), 1000);
		deathAnimation = new Animation();
		location = new Vector2f();
		direction = new Vector2f();
		location.set(loc);
		updateBox();
		speed = 0;
		dead = true;
		dying = false;
		dyingTimer = 0;
	}

	public Entity(String ref, Vector2f loc, Vector2f dir) throws SlickException {
		currentAnimation = new Animation();
		currentAnimation.addFrame(new Image(ref), 1000);
		deathAnimation = new Animation();
		location = new Vector2f();
		direction = new Vector2f();
		location.set(loc);
		direction.set(dir);
		updateBox();
		speed = 0;
		dead = true;
		dying = false;
		dyingTimer = 0;
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

	public Vector2f getDirection() {
		return direction;
	}

	public void setDirection(Vector2f dir) {
		direction.set(dir);
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
			handleCollision();
			return true;
		}
	}

	public boolean intersects(Entity rhs) {
		Vector2f test;
		if (!dead) {
			test = Collision.intersects(this, rhs);
			if (test.x == 0 && test.y == 0)
				return false;
			else
				return true;
		}
		return false;
	}

	public boolean intersects(Porygon rhs) {
		Vector2f test;
		if (!dead) {
			test = Collision.intersects(this, rhs);
			if (test.x == 0 && test.y == 0)
				return false;
			else
				return true;
		}
		return false;
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
			dying = false;
			dyingTimer = 0;
			speed = 0;
			direction.x = 0;
			direction.y = 0;
			setLoc(0, 0);
		}
	}

	public void draw() {
		if (!dead)
			currentAnimation.draw(box.getX(), box.getY());
	}

	public void debugDraw(Graphics graphics) {
		if (!dead) {
			graphics.draw(box);
			currentAnimation.draw(box.getX(), box.getY());
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

	protected Porygon makeBox(float x, float y, float width, float height) {
		float[][] points = { { x, y }, { x + width, y },
				{ x + width, y + height }, { x, y + height } };
		Porygon temp = new Porygon();
		for (float[] i : points)
			temp.addPoint(i[0], i[1]);
		temp.setLocation(x, y);
		return temp;
	}

	protected void updateBox() {
		float x = location.x;
		float y = location.y;
		float[][] points = {
				{ x, y },
				{ x + currentAnimation.getWidth(), location.y },
				{ x + currentAnimation.getWidth(),
						y + currentAnimation.getHeight() },
				{ location.x, y + currentAnimation.getHeight() } };
		Porygon temp = new Porygon();
		for (float[] i : points)
			temp.addPoint(i[0], i[1]);
		box = temp;
	}

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

		rotateAnimation(currentAnimation, theta);
		box.rotate(theta);
	}

	protected void rotateAnimation(Animation animation, float angle) {
		for (int i = 0; i < animation.getFrameCount(); i++) {
			animation.getImage(i).rotate(angle);
		}
	}

	protected abstract void handleCollision();

	protected boolean dead;
	protected boolean dying;
	protected int dyingTimer;
	protected float speed;
	protected Porygon box;
	protected Vector2f direction;
	protected Vector2f location;
	protected Animation currentAnimation;
	protected Animation deathAnimation;
	protected Sound sound;
	protected Sound deathSound;
}
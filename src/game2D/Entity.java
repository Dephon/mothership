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
		scale = 1.5f;
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
		scale = 1.5f;
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
		scale = 1.5f;
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

	public boolean displace(Entity rhs, int collisionEnum) {
		Vector2f dis = Collision.intersects(this, rhs);
		if (dis.x == 0 && dis.y == 0) {
			return false;
		} else {
			if (collisionEnum == CollisionEnum.BLOCKING) {
				location.add(dis);
				box.setLocation(location);
			} else if (collisionEnum == CollisionEnum.DAMAGING) {
				handleCollision(CollisionEnum.DAMAGING);
			}
			return true;
		}
	}

	public boolean displace(Entity rhs) {
		Vector2f dis = Collision.intersects(this, rhs);
		if (dis.x == 0 && dis.y == 0) {
			return false;
		} else {
			location.add(dis);
			box.setLocation(location);
			handleCollision(CollisionEnum.BLOCKING);
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
		dyingTimer = 0;
		updateBox();
	}

	public void create(Vector2f loc) {
		dead = false;
		dyingTimer = 0;
		setLoc(loc);
		updateBox();
	}

	public void create(Vector2f loc, Vector2f dir) {
		dead = false;
		dyingTimer = 0;
		setLoc(loc);
		direction.set(dir);
		updateBox();
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
			deathAnimation.restart();
		}
	}

	public void draw() {
		if (!dead) {
			if (dying) {
				deathAnimation.draw(box.getX(), box.getY());
			} else {
				currentAnimation.draw(box.getX(), box.getY());
			}
		}
	}

	public void debugDraw(Graphics graphics) {

		if (!dead) {
			if (dying) {
				deathAnimation.draw(box.getX(), box.getY());
			} else {
				currentAnimation.draw(box.getX(), box.getY());
			}
			graphics.draw(box);
			graphics.drawGradientLine(box.getX(), box.getY(), 0, 0, 0, 0,
					box.getX() + 1, box.getY() + 1, 0, 0, 0, 0);
		}
	}

	public void update(int dt) {
		if (!dead) {
			if (!dying) {
				Vector2f dV = new Vector2f();
				dV.set(direction);
				dV.x *= speed * dt;
				dV.y *= speed * dt;
				location.add(dV);
				box.setLocation(location);
			}
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
		float[][] points = { { x, y }, { width, y }, { width, height },
				{ x, height } };
		Porygon temp = new Porygon();
		for (float[] i : points)
			temp.addPoint(i[0], i[1]);
		temp.setLocation(x, y);
		return temp;
	}

	protected void updateBox() {
		float x = location.x;
		float y = location.y;
		float width;
		float height;
		if (dying) {
			width = deathAnimation.getWidth();
			height = deathAnimation.getHeight();
		} else {
			width = currentAnimation.getWidth();
			height = currentAnimation.getHeight();
		}
		float[][] points = { { x, y }, { x + width, y },
				{ x + width, y + height }, { x, y + height } };
		Porygon temp = new Porygon();
		temp.setLocation(location);
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

	protected void rotateOnlyImage(boolean reverse) {
		float theta;

		if (reverse)
			theta = (float) (360 - direction.getTheta());
		else
			theta = (float) direction.getTheta();

		rotateAnimation(currentAnimation, theta);
	}

	protected void rotateAnimation(Animation animation, float angle) {
		for (int i = 0; i < animation.getFrameCount(); i++) {
			animation.getImage(i).rotate(angle);
		}
	}

	protected abstract void handleCollision(int collisionEnum);

	protected void TimeUntilDeath(int eta, int dt) {
		if (!dead) {
			if (dying) {
				if (dyingTimer > eta)
					destroy();
				else
					dyingTimer += dt;
			}
		}
	}

	protected boolean dead;
	protected boolean dying;
	protected int dyingTimer;
	protected float scale;
	protected float speed;
	protected Porygon box;
	protected Vector2f direction;
	protected Vector2f location;
	protected Animation currentAnimation;
	protected Animation deathAnimation;
	protected Sound sound;
	protected Sound deathSound;
}
package game2D.abstracts;

import game2D.*;
import game2D.collisions.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Entity {

	public Entity() {
		init();
	}

	public Entity(String ref) throws SlickException {
		init();
		currentAnimation.addFrame(new Image(ref), 1000);
		updateBox();
	}

	public Entity(String ref, Vector2f loc) throws SlickException {
		init();
		currentAnimation.addFrame(new Image(ref), 1000);
		location.set(loc);
		updateBox();
	}

	public Entity(String ref, Vector2f loc, Vector2f dir) throws SlickException {
		init();
		currentAnimation.addFrame(new Image(ref), 1000);
		location.set(loc);
		direction.set(dir);
		updateBox();
	}

	protected Entity(float locX, float locY, float width, float height) {
		init();
		location.set(locX, locY);
		makeBox(locX, locY, width, height);
		invisible = true;
	}

	private void init() {
		aliveAnimation = new Animation();
		currentAnimation = new Animation();
		deathAnimation = new Animation();
		location = new Vector2f();
		direction = new Vector2f();
		speed = 0;
		dead = true;
		dying = false;
		invisible = false;
		dyingTimer = 0;
		scale = 1.5f;
		statDamage = 0;
		statSplashDamage = 0;
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

	public boolean isSplashDamage() {
		return splashDamage;
	}

	public int getStatDamage() {
		return statDamage;
	}

	public int getStatSplashDamage() {
		return statSplashDamage;
	}

	public boolean isDying() {
		return dying;
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
			currentAnimation = aliveAnimation;
		}
	}

	public void draw() {
		if (!dead) {
			if (dying) {
				deathAnimation.draw(box.getX(), box.getY());
			} else {
				if (!invisible) {
					currentAnimation.draw(box.getX(), box.getY());
				}
			}
		}
	}

	public void debugDraw(Graphics graphics) {

		if (!dead) {
			if (dying) {
				deathAnimation.draw(box.getX(), box.getY());
			} else {
				if (!invisible) {
					currentAnimation.draw(box.getX(), box.getY());
				}
			}
			graphics.draw(box);
		}
	}

	public void displace(Vector2f dis) {
		location.add(dis);
		box.setLocation(location);
	}

	// For projectiles
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

	// For Movables
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

	protected void makeBox(float locX, float locY, float width, float height) {
		Porygon temp = new Porygon();
		float[][] points = { { locX, locY }, { locX + width, locY },
				{ locX + width, locY + height }, { locX, locY + height } };
		temp.setLocation(location);
		for (float[] i : points)
			temp.addPoint(i[0], i[1]);
		box = temp;
	}

	protected void updateBox() {
		Porygon temp = new Porygon();
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
		temp.setLocation(location);
		for (float[] i : points)
			temp.addPoint(i[0], i[1]);
		box = temp;
	}

	/**
	 * Rotates both the Image and Polygon with respect to the x axis
	 * 
	 * @param onlyImage
	 *            TODO
	 * @param reverse
	 *            if true the rotation is counter clockwise, otherwise clockwise
	 *
	 **/
	protected void rotate(boolean onlyImage, boolean reverse) {

		if (reverse)
			angle = (float) (360 - direction.getTheta());
		else
			angle = (float) direction.getTheta();

		rotateAnimation(currentAnimation, angle);
		if (!onlyImage)
			box.rotate(angle);
	}

	protected void rotateAnimation(Animation animation, float angle) {
		for (int i = 0; i < animation.getFrameCount(); i++) {
			animation.getImage(i).rotate(angle);
		}
	}

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

	public abstract void handleCollision(int collisionEnum, int statDamage);

	protected float angle;
	protected boolean dead;
	protected boolean dying;
	protected boolean splashDamage;
	protected boolean invisible;
	protected int statDamage;
	protected int statSplashDamage;
	protected int dyingTimer;
	protected float scale;
	protected float speed;
	protected Porygon box;
	protected Vector2f direction;
	protected Vector2f location;
	protected Animation currentAnimation;
	protected Animation aliveAnimation;
	protected Animation deathAnimation;
	protected Animation hitAnimation;
	protected Sound sound;
	protected Sound deathSound;
}
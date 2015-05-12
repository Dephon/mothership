package game2D.abstracts;

import game2D.*;
import game2D.collisions.*;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public abstract class Manager {

	public Manager(Porygon bounds, int maxAmount) throws SlickException {
		init();
		gameBounds = bounds;
		maxCount = maxAmount;
	}

	private void init() {
		entities = new ArrayList<Entity>();
		activeNdxs = new ArrayList<Integer>();
		fireTimer = 0;
		count = 0;
		ndx = 0;
	}

	public void create(Vector2f loc, Vector2f dir) {
		Entity entity = entities.get(ndx);
		if (count < maxCount) {
			entity.create();
			entity.setLoc(loc);
			entity.setDir(dir);
			activeNdxs.add(ndx);
			count++;
			ndx++;
			if (ndx == maxCount)
				ndx = 0;
		}
	}

	public void remove(Entity entity) {
		activeNdxs.remove((Integer) entities.indexOf(entity));
		count--;
	}

	public void add(float posX, float posY, float dirX, float dirY) {
		create(new Vector2f(posX, posY), new Vector2f(dirX, dirY));
	}

	public void update(int dt) {
		for (Entity entity : entities) {
			if (!entity.isDead()) {
				entity.update(dt);
			}
		}
		if (dt < Integer.MAX_VALUE)
			fireTimer += dt;
	}

	public void draw() {
		for (Entity entity : entities)
			entity.draw();
	}

	public void debugDraw(Graphics graphics) {
		for (Entity entity : entities)
			entity.debugDraw(graphics);
	}

	public void displace(Manager rhs, int collisionEnum) {
		ArrayList<Entity> secondList;
		Vector2f dis;
		secondList = rhs.getActive();
		for (Entity entity : entities) {
			if (!entity.isDead()) {
				for (Entity second : secondList) {
					dis = Collision.intersects(entity, second);
					if (dis.x != 0 || dis.y != 0) {
						if (collisionEnum == CollisionEnum.BLOCKING) {
							entity.displace(dis);
						}
						if (second.isDying()) {
							handleCollision(entity, collisionEnum,
									second.getStatSplashDamage());
						} else {
							handleCollision(entity, collisionEnum,
									second.getStatDamage());
							rhs.handleCollision(second, collisionEnum, 0);
						}
					}
				}
			}
		}
	}

	public int checkTransport(TransportManager rhs) {
		int threeStateEnum = ThreeStateEnum.NONE;
		Transport trans;
		ArrayList<Entity> secondList;
		Vector2f dis;
		secondList = rhs.getActive();
		for (Entity entity : entities) {
			if (!entity.isDead()) {
				for (Entity second : secondList) {
					dis = Collision.intersects(entity, second);
					if (dis.x != 0 || dis.y != 0) {
						entity.displace(dis);
						trans = (Transport) second;
						threeStateEnum = trans.getMapSide();
					}
				}
			}
		}
		return threeStateEnum;
	}

	public ArrayList<Entity> getActive() {
		ArrayList<Entity> currentBullets = new ArrayList<Entity>();
		for (int i = 0; i < activeNdxs.size(); i++)
			currentBullets.add(entities.get(activeNdxs.get(i)));
		return currentBullets;
	}

	public void handleCollision(Entity entity, int collisionEnum, int damage) {
		entity.handleCollision(collisionEnum, damage);
		if (entity.isDead())
			remove(entity);
	}

	protected int count;
	protected int ndx;
	protected int fireTimer;
	protected int maxCount;
	protected ArrayList<Integer> activeNdxs;
	protected ArrayList<Entity> entities;
	protected Porygon gameBounds;
	protected Sound sound;
	protected Sound hitSound;

	public Manager(Double thisIsATest) {
		maxCount = thisIsATest.intValue();
		entities = new ArrayList<Entity>();
		activeNdxs = new ArrayList<Integer>();
		fireTimer = 0;
		count = 0;
		ndx = 0;

	}
}
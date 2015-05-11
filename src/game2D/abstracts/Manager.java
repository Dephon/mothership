package game2D.abstracts;

import game2D.*;

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

	public abstract void add(Vector2f pos, Vector2f dir);

	public void add(float posX, float posY, float dirX, float dirY) {
		add(new Vector2f(posX, posY), new Vector2f(dirX, dirY));
	}

	public abstract void handleCollision();

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

	public void displace(Entity rhs, int collisionEnum) {
		for (Entity entity : entities) {
			if (!entity.isDead()) {
				entity.displace(rhs, collisionEnum);
			}
		}
	}

	public void displace(Manager rhs, int collisionEnum) {
		ArrayList<Entity> secondList;
		boolean displaced;
		secondList = rhs.getActive();
		for (Entity entity : entities) {
			if (!entity.isDead()) {
				for (Entity second : secondList) {
					displaced = entity.displace(second, collisionEnum);
					if (displaced) {
						handleCollision();
						rhs.handleCollision();
					}
				}
			}
		}
	}

	public ArrayList<Entity> getActive() {
		ArrayList<Entity> currentBullets = new ArrayList<Entity>();
		for (int i = 0; i < activeNdxs.size(); i++)
			currentBullets.add(entities.get(activeNdxs.get(i)));
		return currentBullets;
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
}
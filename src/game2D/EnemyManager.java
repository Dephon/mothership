package game2D;

import game2D.abstracts.*;
import game2D.collisions.*;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class EnemyManager extends Manager {
	public EnemyManager(Porygon bounds, int maxAmount) throws SlickException {
		super(bounds, maxAmount);
		for (int i = 0; i < maxAmount; i++)
			entities.add(new Enemy("data/Alien_PlaceHolder.gif"));

		spawnLocations = new ArrayList<EnemySpawner>();
		rand = new Random();
		spawnsEnabled = false;
	}

	@Override
	public void create(Vector2f loc, Vector2f dir) {
		Enemy alien = (Enemy) entities.get(ndx);
		if (count < maxCount) {
			alien.create();
			alien.setLoc(loc);
			alien.setDir(dir);
			activeNdxs.add(ndx);
			count++;
			ndx++;
			if (ndx == maxCount)
				ndx = 0;
		}
	}

	@Override
	public void update(int dt) {
		for (Entity alien : entities) {
			if (!alien.isDead()) {
				alien.update(dt);
			}
		}
		if (spawnsEnabled)
			updateSpawns(dt);
	}

	public void update(Vector2f loc, int dt) {
		for (Entity alien : entities) {
			if (!alien.isDead()) {
				alien.update(loc, dt);
			}
		}
		if (spawnsEnabled)
			updateSpawns(dt);
	}

	@Override
	public void handleCollision(Entity entity, int collisionEnum, int damage) {
		if (collisionEnum == CollisionEnum.DAMAGING) {
			// hitSound.stop();
			// hitSound.play();
		}
		super.handleCollision(entity, collisionEnum, damage);
	}

	public void addSpawner(Vector2f loc, int rate, int dur, boolean active) {
		spawnLocations.add(new EnemySpawner(loc, rate, dur, active));
	}

	public void enableSpawns(boolean state) {
		spawnsEnabled = state;
	}

	private void updateSpawns(int dt) {
		if (spawnLocations.isEmpty()) {
			spawnsEnabled = false;
			return;
		}

		for (EnemySpawner e : spawnLocations) {
			if (e.isActive()) {
				e.setLastSpawn(e.getLastSpawn() + dt);
				e.setDuration(e.getDuration() - dt);
				if (e.getDuration() <= 0) {
					e.setActive(false);
					continue;
				}
				if (e.getLastSpawn() >= e.getSpawnRate()) {
					create(e.getLocation(), new Vector2f(0, 0));
					e.setLastSpawn(0);
				}
			}
		}
	}

	private boolean spawnsEnabled;
	private ArrayList<EnemySpawner> spawnLocations;
	private Random rand;

	public void testAdd() {
		// Enemy alien = (Enemy) entities.get(ndx);
		if (count < maxCount) {
			activeNdxs.add(ndx);
			count++;
			ndx++;
			if (ndx == maxCount)
				ndx = 0;
		}
	}

	public EnemyManager(Double thisIsATest, Double otherOne)
			throws SlickException {
		super(thisIsATest);
		for (int i = 0; i < thisIsATest.intValue(); i++) {
			entities.add(new Enemy(otherOne));
		}

	}
}

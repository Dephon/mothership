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

		spawnLocations = new ArrayList<Vector2f>();
		rand = new Random();
		lastSpawn = 0;
		spawnAtRandom = false;
	}

	@Override
	public void add(Vector2f loc, Vector2f dir) {
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
		if (spawnAtRandom)
			updateSpawns(dt);
	}

	public void update(Vector2f loc, int dt) {
		for (Entity alien : entities) {
			if (!alien.isDead()) {
				alien.update(loc, dt);
			}
		}
		if (spawnAtRandom)
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

	public void spawnAtRandom(int duration, int rate) {
		spawnAtRandom = true;
		enemySpawnInterval = duration;
		spawnRate = rate;
	}

	public void stopRandomSpawns() {
		spawnAtRandom = false;

	}

	private void updateSpawns(int dt) {
		if (spawnLocations.isEmpty()) {
			spawnAtRandom = false;
			return;
		}

		if (enemySpawnInterval > 0) {
			enemySpawnInterval -= dt;
			lastSpawn += dt;
			if (lastSpawn >= spawnRate) {
				lastSpawn = 0;
				add(spawnLocations.get(rand.nextInt(spawnLocations.size())),
						new Vector2f(0, 0));
			}
		} else {
			spawnAtRandom = false;
		}
	}

	public void addSpawnLocation(Vector2f loc) {
		spawnLocations.add(loc);
	}

	private boolean spawnAtRandom;
	private ArrayList<Vector2f> spawnLocations;
	private int enemySpawnInterval;
	private int spawnRate;
	private int lastSpawn;
	private Random rand;
}

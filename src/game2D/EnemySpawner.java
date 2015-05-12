package game2D;

import org.newdawn.slick.geom.*;

public class EnemySpawner {

	EnemySpawner(Vector2f loc, int rate, int dur, boolean active) {
		location = loc;
		spawnRate = rate;
		duration = dur;
		lastSpawn = 0;
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean state) {
		active = state;
	}

	public int getLastSpawn() {
		return lastSpawn;
	}

	public void setLastSpawn(int lastSpawn) {
		this.lastSpawn = lastSpawn;
	}

	public int getSpawnRate() {
		return spawnRate;
	}

	public void setSpawnRate(int spawnRate) {
		this.spawnRate = spawnRate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Vector2f getLocation() {
		return location;
	}

	public void setLocation(Vector2f location) {
		this.location = location;
	}

	private boolean active;
	private int lastSpawn;
	private int spawnRate;
	private int duration;
	private Vector2f location;
}

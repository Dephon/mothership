package testclasses;

public class MockMedPack {

	MockMedPack() {

	}

	public void create() {
		dead = false;
	}

	public void handleCollision() {
		dead = true;
	}

	public boolean isDead() {
		return dead;
	}

	public boolean dead;
	// public int id; Not sure if I'll use this.
}

package testclasses;

import game2D.projectiles.*;

public class mockUI {

	public mockUI() {
		numbers = new mockImage[10];
		for (int i = 0; i < 10; i++) {
			numbers[i] = new mockImage(i);
		}

		rocket = new mockImage(AmmoEnum.MISSILE);
		chainGun = new mockImage(AmmoEnum.BULLET);

		update(100, AmmoEnum.BULLET);
	}

	public void update(int healthUpdate, int ammo) {
		this.ammo = ammo;
		health = healthUpdate;
		updateHealthCounter();
	}

	public mockImage getDigit(int i) {
		switch (i) {
		case 1:
			return firstDigit;
		case 2:
			return secondDigit;
		case 3:
			return thirdDigit;
		default:
			return null;
		}
	}

	private void updateHealthCounter() {
		thirdDigit = numbers[health % 10];
		if (health == 100)
			secondDigit = numbers[0];
		else
			secondDigit = numbers[health / 10];
		firstDigit = numbers[health / 100];
	}

	public mockImage[] getNumbers() {
		return numbers;
	}

	public int getWep() {
		return ammo;
	}

	private int health;
	private int ammo;
	private mockImage firstDigit;
	private mockImage secondDigit;
	private mockImage thirdDigit;

	private mockImage rocket;
	private mockImage chainGun;

	private mockImage[] numbers;
}

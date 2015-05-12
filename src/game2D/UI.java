package game2D;

import game2D.projectiles.*;

import org.newdawn.slick.*;

public class UI {

	public UI() throws SlickException {
		// INIT PORTRAIT
		SpriteSheet heads = new SpriteSheet(new Image(
				"data/UI/doom heads smaller.png"), 77, 80);

		portraits = new Animation[heads.getVerticalCount()];

		for (int i = 0; i < heads.getVerticalCount(); i++)
			portraits[i] = new Animation();

		// Prepare animation classes
		for (int i = 0; i < heads.getVerticalCount(); i++)
			for (int j = 0; j < heads.getHorizontalCount(); j++)
				portraits[i].addFrame(heads.getSprite(j, i), 700);

		// Set to healthy by default.
		curPortrait = portraits[UI.PORTRAIT_HEALTHY];

		// INIT NUMBERS
		SpriteSheet nums = new SpriteSheet(new Image(
				"data/UI/doom numbers smaller.png"), 45, 70);
		numbers = new Image[10];

		int k = 0;
		for (int i = 0; i < nums.getVerticalCount(); i++) {
			for (int j = 0; j < nums.getHorizontalCount(); j++) {
				numbers[k] = nums.getSprite(j, i);
				k++;
			}
		}

		uiBar = new Image("data/UI/UIbarmaybe scaled orig.png");

		SpriteSheet mapSheet = new SpriteSheet(new Image(
				"data/UI/UI alien thing.png"), 160, 80);
		alienMap = new Animation(mapSheet, 4000);
		chainGun = new Image("data/UI/chaingun.png");
		rocket = new Image("data/UI/rocket.png");
		health = 0;
		updateHealthCounter();
	}

	public void draw() {
		uiBar.draw(0, 560);
		curPortrait.draw(628, 560);
		if (firstDigit != numbers[0])
			firstDigit.draw(506, 557);
		if (secondDigit != numbers[0] || health == 100)
			secondDigit.draw(540, 557);
		thirdDigit.draw(580, 557);
		alienMap.draw(0, 560);
		if (currentAmmo == AmmoEnum.BULLET)
			chainGun.draw(275, 570);
		else
			rocket.draw(270, 570);
	}

	public void update(int healthUpdate, int ammoType, int missileCount) {
		health = healthUpdate;
		updatePortrait();
		updateHealthCounter();
		currentAmmo = ammoType;
	}

	private void updatePortrait() {
		if (health >= 80) {
			curPortrait = portraits[UI.PORTRAIT_HEALTHY];
		} else if (health >= 60) {
			curPortrait = portraits[UI.PORTRAIT_SLIGHT_DAMAGE];
		} else if (health >= 40) {
			curPortrait = portraits[UI.PORTRAIT_SHAKEN_UP];
		} else if (health >= 20) {
			curPortrait = portraits[UI.PORTRAIT_HEAVY_DAMAGE];
		} else if (health < 20) {
			curPortrait = portraits[UI.PORTRAIT_DYING];
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

	public Image getDigit(int i) {
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

	public Image[] getNumbers() {
		return numbers;
	}

	public static int PORTRAIT_DYING = 4;
	public static int PORTRAIT_HEAVY_DAMAGE = 3;
	public static int PORTRAIT_SHAKEN_UP = 2;
	public static int PORTRAIT_SLIGHT_DAMAGE = 1;
	public static int PORTRAIT_HEALTHY = 0;

	private int currentAmmo;
	private int health;
	private Image firstDigit;
	private Image secondDigit;
	private Image thirdDigit;

	private Image rocket;
	private Image chainGun;
	private Image uiBar;
	private Image[] numbers;
	private Animation[] portraits;
	private Animation alienMap;
	public Animation curPortrait;

}
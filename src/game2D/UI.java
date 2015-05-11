package game2D;

import org.newdawn.slick.*;

public class UI {

	public UI() throws SlickException {

		// INIT PORTRAIT
		SpriteSheet heads = new SpriteSheet(new Image(
				"data/UI/doom heads smaller.png"), 77, 80);

		portraits = new Animation[heads.getVerticalCount()];

		for (int i = 0; i < heads.getVerticalCount(); i++) {
			portraits[i] = new Animation();
		}
		// Prepare animation classes
		for (int i = 0; i < heads.getVerticalCount(); i++) {
			for (int j = 0; j < heads.getHorizontalCount(); j++) {
				portraits[i].addFrame(heads.getSprite(j, i), 700);
			}
		}

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
		// updateHealthCounter();
	}

	public void draw() {
		uiBar.draw(0, 560);
		curPortrait.draw(623, 560);
		if (firstDigit != numbers[0])
			firstDigit.draw(506, 557);
		if (secondDigit != numbers[0] || health == 100)
			secondDigit.draw(540, 557);
		thirdDigit.draw(580, 557);
		alienMap.draw(0, 560);
	}

	public void update(int healthUpdate) {
		health = healthUpdate;
		updatePortrait();
		updateHealthCounter();

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

	public static int PORTRAIT_DYING = 4;
	public static int PORTRAIT_HEAVY_DAMAGE = 3;
	public static int PORTRAIT_SHAKEN_UP = 2;
	public static int PORTRAIT_SLIGHT_DAMAGE = 1;
	public static int PORTRAIT_HEALTHY = 0;

	private int health;
	private Image firstDigit;
	private Image secondDigit;
	private Image thirdDigit;

	private Image uiBar;
	private Image[] numbers;
	private Animation[] portraits;
	private Animation alienMap;
	public Animation curPortrait;

}
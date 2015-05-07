package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Player extends Movable {

	public Player(String ref) throws SlickException {
		super(ref);
		init();
	}

	public Player(String ref, Vector2f loc) throws SlickException {
		super(ref, loc);
		init();
	}

	@Override
	public void handleCollisions() {
		// TODO Auto-generated method stub

	}

	public void init() throws SlickException {
		uI = new UI();
		health = 100;
	}

	public void drawUI() {
		uI.update();
		uI.draw();
	}

	public static int PORTRAIT_HEALTHY = 0;
	public static int PORTRAIT_SLIGHT_DAMAGE = 1;
	public static int PORTRAIT_SHAKEN_UP = 2;
	public static int PORTRAIT_HEAVY_DAMAGE = 3;
	public static int PORTRAIT_DYING = 4;

	private UI uI;

	// @Override
	// public void handleCollisions() {
	// Collision temp;
	// while (!collisions.isEmpty()) {
	// temp = collisions.remove();
	// switch (temp.getID()) {
	// case Collision.BLOCKED:
	// break;
	// case Collision.DAMAGING:
	// break;
	// case Collision.TRANSPORTING:
	// break;
	// default:
	// break;
	// }
	// }
	// }

	public class UI {

		public UI() throws SlickException {

			// INIT PORTRAIT
			SpriteSheet heads = new SpriteSheet(new Image(
					"data/UI/doom heads.png"), 133, 139);

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
			curPortrait = portraits[Player.PORTRAIT_HEALTHY];

			// INIT NUMBERS
			SpriteSheet nums = new SpriteSheet(new Image(
					"data/UI/doom numbers.png"), 92, 144);
			numbers = new Image[10];

			int k = 0;
			for (int i = 0; i < nums.getVerticalCount(); i++) {
				for (int j = 0; j < nums.getHorizontalCount(); j++) {
					numbers[k] = nums.getSprite(j, i);
					k++;
				}
			}
		}

		public void draw() {
			curPortrait.draw(20, 500);
			if (firstDigit != numbers[0])
				firstDigit.draw(600, 500);
			if (secondDigit != numbers[0] || health == 100)
				secondDigit.draw(700, 500);
			thirdDigit.draw(800, 500);
		}

		public void update() {

			updatePortrait();
			updateHealthCounter();

		}

		private void updatePortrait() {
			if (health >= 80) {
				curPortrait = portraits[Player.PORTRAIT_HEALTHY];
			} else if (health >= 60) {
				curPortrait = portraits[Player.PORTRAIT_SLIGHT_DAMAGE];
			} else if (health >= 40) {
				curPortrait = portraits[Player.PORTRAIT_SHAKEN_UP];
			} else if (health >= 20) {
				curPortrait = portraits[Player.PORTRAIT_HEAVY_DAMAGE];
			} else if (health > 20) {
				curPortrait = portraits[Player.PORTRAIT_DYING];
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

		private Image firstDigit;
		private Image secondDigit;
		private Image thirdDigit;

		private Image[] numbers;
		private Animation[] portraits;
		public Animation curPortrait;
	}
}

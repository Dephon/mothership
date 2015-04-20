package states;

import game2D.*;
import game2D.Projectile.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class GameState extends BasicGameState {
	Player player;
	Entity[] enemies;
	TiledMap gameMap;
	AmmoManager ammoManager;
	int fireTimer;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		player = new Player("data/S3K_Hyper_Knuckles.gif", 0, 0); // Placeholder
		gameMap = new TiledMap(new Image("data/Steel_Plate.png"),
				container.getWidth(), container.getHeight());
		ammoManager = new AmmoManager(new Rectangle(0, 0, container.getWidth(),
				container.getHeight()));
		ammoManager.changeAmmo(AmmoEnum.BULLET);
		fireTimer = 0;
		// container.setAnimatedMouseCursor(arg0, arg1, arg2, arg3, arg4, arg5);
		// container.setDefaultMouseCursor();
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		Vector2f location = new Vector2f();
		Vector2f pVector = new Vector2f();
		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.PAUSE);
			container.setPaused(true);
		} else {
			if (input.isKeyDown(Input.KEY_W)) { // Move up
				if (player.getOriginY() > gameMap.getOriginY())
					pVector.y += -.1 * dt;
			}
			if (input.isKeyDown(Input.KEY_S)) { // Move Down
				if (player.getEndY() < container.getHeight())
					pVector.y += .1 * dt;
			}
			if (input.isKeyDown(Input.KEY_A)) { // Move Left
				if (player.getOriginX() > gameMap.getOriginX())
					pVector.x += -.1 * dt;
			}
			if (input.isKeyDown(Input.KEY_D)) { // Move Right
				if (player.getEndX() < container.getWidth())
					pVector.x += .1 * dt;
			}
			player.update(pVector);
		}

		if (input.isMouseButtonDown(0)) {
			location.x = player.getCenterX(); // - tempAmmo.getCenterX();
			location.y = player.getCenterY(); // - tempAmmo.getCenterY();
			pVector.x = input.getAbsoluteMouseX() - player.getCenterX();
			pVector.y = input.getAbsoluteMouseY() - player.getCenterY();
			pVector = pVector.normalise();
			ammoManager.add(location, pVector);
		}
		ammoManager.update(dt);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		gameMap.draw();
		ammoManager.draw();
		player.draw();
		graphics.drawString("Bullet Count" + ammoManager.getBulletCount(), 0, 0);
	}

	@Override
	public int getID() {
		return StateEnum.GAME;
	}

}

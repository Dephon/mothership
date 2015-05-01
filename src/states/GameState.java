package states;

import game2D.*;
import game2D.Projectile.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.tiled.*;

public class GameState extends BasicGameState {
	Player player;
	Entity[] enemies;
	TiledMap gameMap;
	AmmoManager ammoManager;
	int fireTimer;
	Obstacle wall;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		player = new Player("data/S3K_Hyper_Knuckles.gif", 0, 0); // Placeholder
		player.create();
		gameMap = new TiledMap("maps/mothership level 1_basic.tmx");
		ammoManager = new AmmoManager(new Rectangle(0, 0, container.getWidth(),
				container.getHeight()));
		ammoManager.setAmmo(AmmoEnum.BULLET);
		fireTimer = 0;
		wall = new Obstacle("data/MetalBlock.png");
		wall.create();
		wall.setLoc(100, 0);
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
			// container.setPaused(true);
		} else {
			if (input.isKeyPressed(Input.KEY_SPACE)) {
				if (ammoManager.getAmmo() == AmmoEnum.BULLET) {
					ammoManager.setAmmo(AmmoEnum.MISSILE);
				} else
					ammoManager.setAmmo(AmmoEnum.BULLET);
			}
			if (input.isKeyDown(Input.KEY_W)) { // Move up
				if (player.getOriginY() >= 0)
					pVector.y += -.1 * dt;
			}
			if (input.isKeyDown(Input.KEY_S)) { // Move Down
				if (player.getEndY() < container.getHeight())
					pVector.y += .1 * dt;
			}
			if (input.isKeyDown(Input.KEY_A)) { // Move Left
				if (player.getOriginX() >= 0)
					pVector.x += -.1 * dt;
			}
			if (input.isKeyDown(Input.KEY_D)) { // Move Right
				if (player.getEndX() < container.getWidth())
					pVector.x += .1 * dt;
			}
			player.update(pVector);
			if (input.isKeyDown(input.KEY_MINUS)) {
				sbg.enterState(StateEnum.GAME_OVER, new FadeOutTransition(),
						new FadeInTransition());
			}
		}

		if (input.isMouseButtonDown(0)) {
			location.x = player.getCenterX();
			location.y = player.getCenterY();
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
		gameMap.render(0, 0);
		// ammoManager.draw();
		ammoManager.debugDraw(graphics);
		player.debugDraw(graphics);
		wall.debugDraw(graphics);
	}

	@Override
	public int getID() {
		return StateEnum.GAME;
	}

}

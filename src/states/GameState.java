package states;

import game2D.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class GameState extends BasicGameState {

	char keyDown;
	Player player;
	Entity[] Enemies;
	TiledMap gameMap;
	boolean leftClick;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		gameMap = new TiledMap(new Image("data/Steel_Plate.png"), 100, 100);
		player = new Player("data/S3K_Hyper_Knuckles.gif", 0, 0); // Placeholder

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		Vector2f pVector = new Vector2f(0, 0);
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.PAUSE);
			container.setPaused(true);
		} else {
			if (input.isKeyDown(Input.KEY_W)) { // Move up
				if (player.getOriginY() > gameMap.getOriginY())
					pVector.y += -1 * dt / 10.0;
			}
			if (input.isKeyDown(Input.KEY_S)) { // Move Down
				if (player.getEndY() < container.getHeight())
					pVector.y += dt / 10.0;
			}
			if (input.isKeyDown(Input.KEY_A)) { // Move Left
				if (player.getOriginX() > gameMap.getOriginX())
					pVector.x += -1 * dt / 10.0;
			}
			if (input.isKeyDown(Input.KEY_D)) { // Move Right
				if (player.getEndX() < container.getWidth())
					pVector.x += dt / 10.0;
			}
			player.update(pVector);
		}

		if (input.isMouseButtonDown(0)) {
			pVector.x = input.getAbsoluteMouseX() - player.getEndX();
			pVector.y = input.getAbsoluteMouseY() - player.getEndY();
			pVector = pVector.normalise();
		}
		if (input.isKeyDown(Input.KEY_Q)) {
			container.exit();
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		gameMap.draw();

		player.draw();

	}

	@Override
	public int getID() {
		return StateEnum.GAME;
	}

}

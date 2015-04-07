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
		Vector2f vector = new Vector2f(0, 0);
		Vector2f pVector = new Vector2f(0, 0);
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.PAUSE);
			container.setPaused(true);
		} else {
			if (input.isKeyDown(Input.KEY_W)) { // Move up
				if (player.getOriginY() > gameMap.getOriginY())
					vector.y += -1 * dt / 10.0;
			}
			if (input.isKeyDown(Input.KEY_S)) { // Move Down
				if (player.getEndY() < container.getHeight())
					vector.y += dt / 10.0;
			}
			if (input.isKeyDown(Input.KEY_A)) { // Move Left
				if (player.getOriginX() > gameMap.getOriginX())
					vector.x += -1 * dt / 10.0;
			}
			if (input.isKeyDown(Input.KEY_D)) { // Move Right
				if (player.getEndX() < container.getWidth())
					vector.x += dt / 10.0;
			}
			player.update(vector);
		}

		if (input.isMouseButtonDown(0)) {
			pVector.x = input.getAbsoluteMouseX() - player.getEndX();
			pVector.y = input.getAbsoluteMouseY() - player.getEndY();
			pVector = pVector.normalise();

			leftClick = true;
		} else {
			leftClick = false;
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
		if (leftClick)
			graphics.drawString("Left Click Down", 0, 0);
		else
			graphics.drawString("Game State", 0, 0);
		if (keyDown != 0) {
			graphics.drawString("Key pressed: " + keyDown, 0, 20);
		}

	}

	@Override
	public int getID() {
		return StateEnum.GAME;
	}

}

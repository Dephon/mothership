package states;

import game2D.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class GameState extends BasicGameState {

	char keyDown;
	Sprite hero;
	Sprite[] Enemies;
	TiledMap gameMap;
	boolean leftClick;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		gameMap = new TiledMap(new Image("data/Steel_Plate.png"), 100, 100);
		hero = new Sprite("data/S3K_Hyper_Knuckles.gif", 0, 0); // Placeholder

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		Vector2f vector = new Vector2f(0, 0);
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.PAUSE.getID());
			container.setPaused(true);
		} else {
			if (input.isKeyDown(Input.KEY_W)) { // Move up
				if (hero.getOriginY() > gameMap.getOriginY())
					vector.y += -1 * dt / 10.0;
			}
			if (input.isKeyDown(Input.KEY_S)) { // Move Down
				if (hero.getEndY() < container.getHeight())
					vector.y += dt / 10.0;
			}
			if (input.isKeyDown(Input.KEY_A)) { // Move Left
				if (hero.getOriginX() > gameMap.getOriginX())
					vector.x += -1 * dt / 10.0;
			}
			if (input.isKeyDown(Input.KEY_D)) { // Move Right
				if (hero.getEndX() < container.getWidth())
					vector.x += dt / 10.0;
			}
			hero.update(vector);
		}

		if (input.isMouseButtonDown(0)) // TODO: Handle mouse events
			// Attack
			leftClick = true;
		else
			leftClick = false;

		if (input.isKeyDown(input.KEY_Q)) {
			container.exit();
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		gameMap.draw();
		hero.draw();
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
		return StateEnum.GAME.getID();
	}

}

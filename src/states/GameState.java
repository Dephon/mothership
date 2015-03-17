package states;

import game2D.*;

import org.newdawn.slick.*;
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
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.PAUSE.getID());
			container.setPaused(true);
		} else {
		}
		if (input.isMouseButtonDown(0)) // TODO: Handle mouse events
			leftClick = true;
		else
			leftClick = false;
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

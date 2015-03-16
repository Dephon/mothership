package states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameState extends BasicGameState {

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.PAUSE.getID());
			container.setPaused(true);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		graphics.drawString("Game State", 50, 50);

	}

	@Override
	public int getID() {
		return StateEnum.GAME.getID();
	}

}

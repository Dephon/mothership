package states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class PauseState extends BasicGameState {

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.GAME);
			container.setPaused(false);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		graphics.drawString("Paused", 200, 200);
	}

	@Override
	public int getID() {
		return StateEnum.PAUSE;
	}

}
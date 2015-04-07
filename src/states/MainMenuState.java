package states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class MainMenuState extends BasicGameState {

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		sbg.enterState(StateEnum.GAME);

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		// TODO Auto-generated method stub
		graphics.drawString("Main Menu", 50, 50);

	}

	@Override
	public int getID() {
		return StateEnum.MAIN_MENU;
	}

}
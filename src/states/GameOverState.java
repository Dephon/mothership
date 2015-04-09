package states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameOverState extends BasicGameState {

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		arg2.drawString("Game Over", 150, 150);

	}

	@Override
	public int getID() {
		return StateEnum.GAMEOVER.getID();
	}

}
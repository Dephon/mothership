package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import states.*;
import states.GameState;

/**
 */
public class Main extends StateBasedGame {

	public Main() {
		super("Operation Mothership");
	}

	public static void main(String[] arguments) {
		try {
			AppGameContainer app = new AppGameContainer(new Main());
			app.setDisplayMode(500, 400, false);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new MainMenuState());
		this.addState(new GameState());
		this.addState(new GameOverState());
		this.addState(new PauseState());

	}
}

package game2D;

import game2D.states.*;
import game2D.states.levels.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 */
public class Main extends StateBasedGame {

	public Main() {
		super("Operation Mothership");
	}

	public static void main(String[] arguments) {
		try {
			AppGameContainer app = new AppGameContainer(new Main());
			app.setDisplayMode(960, 640, false);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new MainMenuState());
		this.addState(new GameOverState());
		this.addState(new PauseState());
		this.addState(new MapOne());
		// this.addState(new MapTwo());
		// this.addState(new MapThree());
	}
}
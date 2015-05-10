package game2D.states;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

public class PauseState extends BasicGameState {

	private Image pausedLogo;
	private Image backButton;
	private Image quitButton;
	private Rectangle returnTo, quit;
	private Circle mousePoint;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		pausedLogo = new Image("dickPics/pausedTitle.png");
		backButton = new Image("dickPics/returnToGame.png");
		quitButton = new Image("dickPics/quitGameButton.png");

		returnTo = new Rectangle((container.getWidth() - 150) / 2f - 20,
				container.getHeight() / 2.7f, 150, 50);
		quit = new Rectangle((container.getWidth() - 150) / 2f - 20,
				container.getHeight() / 2.2f, 150, 50);
		mousePoint = new Circle(1, 1, 1);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {

		// if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
		// sbg.enterState(StateEnum.GAME);
		// container.setPaused(false);
		// }

		Input input = container.getInput();
		mousePoint.setCenterX(input.getMouseX());
		mousePoint.setCenterY(input.getMouseY());

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (returnTo.intersects(mousePoint)) {
				sbg.enterState(GameState.currentLevel, new FadeOutTransition(),
						new FadeInTransition());
			}
			if (quit.intersects(mousePoint)) {
				container.exit();
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics disp) throws SlickException {
		// graphics.drawString("Paused", 200, 200);

		// disp.fill(returnTo);
		// disp.fill(quit);
		// disp.fill(mousePoint);

		disp.drawImage(pausedLogo, (container.getWidth() - 400) / 2f,
				container.getWidth() / 7.5f);
		disp.drawImage(backButton, returnTo.getX(), returnTo.getY());
		disp.drawImage(quitButton, quit.getX(), quit.getY());
	}

	@Override
	public int getID() {
		return StateEnum.PAUSE;
	}

}
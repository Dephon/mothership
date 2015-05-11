package game2D.states;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class MainMenuState extends BasicGameState {

	private Image nameLogo;
	private Image startButton;
	private Image quitButton;
	private Rectangle start, quit;
	private Circle mousePoint;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		nameLogo = new Image("dickPics/logo.png");
		startButton = new Image("dickPics/playGameButton.png");
		quitButton = new Image("dickPics/quitGameButton.png");
		start = new Rectangle((container.getWidth() - 150) / 3f + 75,
				container.getHeight() / 3.0f, 200, 100);
		quit = new Rectangle((container.getWidth() - 150) / 3f + 75,
				container.getHeight() / 2.3f, 200, 100);
		mousePoint = new Circle(1, 1, 1);

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {

		// sbg.enterState(StateEnum.GAME_LEVEL_ONE);

		Input input = container.getInput();
		mousePoint = new Circle(1, 1, 1);
		mousePoint.setCenterX(input.getMouseX());
		mousePoint.setCenterY(input.getMouseY());

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (start.intersects(mousePoint)) {
				sbg.enterState(StateEnum.GAME_LEVEL_ONE);
				// sbg.enterState(StateEnum.GAME_LEVEL_ONE,
				// new FadeOutTransition(), new FadeInTransition());
			}
			if (quit.intersects(mousePoint)) {
				container.exit();
			}
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics disp) throws SlickException {

		// nameLogo = new Image("dickPics/logo.png");
		// startButton = new Image("dickPics/playGameButton.png");
		// quitButton = new Image("dickPics/quitGameButton.png");
		// start = new Rectangle((container.getWidth() - 150) / 3f + 75,
		// container.getHeight() / 3.0f, 150, 50);
		// quit = new Rectangle((container.getWidth() - 150) / 3f + 75,
		// container.getHeight() / 2.3f, 150, 50);

		// disp.fill(start);
		// disp.fill(quit);

		disp.setBackground(Color.gray);
		disp.drawImage(nameLogo, (container.getWidth() - 927) / 2f,
				container.getHeight() / 7.5f);
		disp.drawImage(startButton, start.getX(), start.getY());
		disp.drawImage(quitButton, quit.getX(), quit.getY());

	}

	@Override
	public int getID() {
		return StateEnum.MAIN_MENU;
	}

}
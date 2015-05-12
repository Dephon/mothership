package game2D.states;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

public class CongratulationsState extends BasicGameState {

	private Image winLogo;
	private Image backButton;
	private Rectangle returnTo;
	private Circle mousePoint;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		winLogo = new Image("dickPics/winTitle.png");
		backButton = new Image("dickPics/returnToMenu.png");
		returnTo = new Rectangle((container.getWidth() - 150) / 2f,
				container.getHeight() / 1.8f, 150, 50);
		mousePoint = new Circle(1, 1, 1);

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = container.getInput();
		mousePoint.setCenterX(input.getMouseX());
		mousePoint.setCenterY(input.getMouseY());

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (returnTo.intersects(mousePoint)) {
				sbg.enterState(StateEnum.MAIN_MENU, new FadeOutTransition(),
						new FadeInTransition());
			}
		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics disp) throws SlickException {

		disp.drawImage(winLogo, (container.getWidth() - 909) / 2f,
				container.getHeight() / 7.5f);
		disp.drawImage(backButton, returnTo.getX(), returnTo.getY());

	}

	@Override
	public int getID() {
		return StateEnum.CongratulationsState;
	}

}
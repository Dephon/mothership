package game2D.states.levels;

import game2D.states.*;
import game2D.states.GameState;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public class MapOne extends GameState {

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		super.init(container, sbg);
		levelChanged = true;
	}

	@Override
	public int getID() {
		return StateEnum.GAME_LEVEL_ONE;
	}

	@Override
	public void setMap() throws SlickException {
		gameMap = new TiledMap("maps/level_1.tmx");
	}

	@Override
	public void setWalls(GameContainer container) throws SlickException {
		obstacles.add(0, 0, container.getWidth(), 62.5f);
		obstacles.add(0, 0, 25, container.getHeight());
		obstacles.add(938f, 0f, 25, container.getHeight());
		obstacles.add(0f, 469f, container.getWidth(), 26f);
	}

	@Override
	public void update(GameContainer container,
			org.newdawn.slick.state.StateBasedGame sbg, int dt)
			throws SlickException {
		super.update(container, sbg, dt);
	};

	@Override
	public void setMusic() throws SlickException {
		background = new Music("data/sounds/despair_tower_top.wav");
	}
}

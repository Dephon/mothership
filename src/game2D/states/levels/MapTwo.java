package game2D.states.levels;

import game2D.states.*;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.*;

public class MapTwo extends GameState {

	@Override
	public int getID() {
		return StateEnum.GAME_LEVEL_TWO;
	}

	@Override
	public void setMap() throws SlickException {
		gameMap = new TiledMap("maps/level_2.tmx");
	}

	@Override
	public void setWalls(GameContainer container) throws SlickException {
		// TODO: Generate Walls
		// obstacles.add(0, 0, container.getWidth(), 62.5f);
		// obstacles.add(0, 0, 25, container.getHeight());
		// obstacles.add(938f, 0f, 25, container.getHeight());
		// obstacles.add(0f, 469f, container.getWidth(), 26f);
	}

	@Override
	public void setMusic() throws SlickException {
		background = new Music("data/sounds/despair_tower_top.wav");
	}
}

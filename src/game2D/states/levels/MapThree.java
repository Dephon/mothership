package game2D.states.levels;

import game2D.states.*;
import game2D.states.GameState;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public class MapThree extends GameState {
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		super.init(container, sbg);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		super.update(container, sbg, dt);
	}

	@Override
	public int getID() {
		return StateEnum.GAME_LEVEL_THREE;
	}

	@Override
	public void setMap() throws SlickException {
		gameMap = new TiledMap("maps/level_3.tmx");
	}

	@Override
	public void setWalls(GameContainer container) throws SlickException {
		// TODO Generate Walls
	}

	@Override
	public void setMusic() throws SlickException {
		background = new Music("data/sounds/despair_tower_top.wav");
	}
}

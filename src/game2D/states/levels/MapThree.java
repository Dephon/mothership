package game2D.states.levels;

import game2D.*;
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

	public void setWalls(GameContainer container) throws SlickException {
		// Top Wall
		obstacles.add(0, 0, 450, 20);
		obstacles.add(544, 0, 416, 20);
		// Right Wall
		obstacles.add(935, 0, 25, 233);
		obstacles.add(935, 310, 25, 330);
		// Left Wall
		obstacles.add(0, 0, 25, 233);
		obstacles.add(0, 310, 25, 330);
		// Bottom Wall
		obstacles.add(0, 545, 960, 15);
		// Obstacles
		obstacles.add(100, 530, 56, 15);
		obstacles.add(260, 530, 56, 15);
		obstacles.add(628, 530, 56, 15);
		obstacles.add(773, 530, 56, 15);
		// Transport
		mapMover.add(0, 283, 1, 122, ThreeStateEnum.LEFT);
		mapMover.add(959, 283, 1, 122, ThreeStateEnum.RIGHT);
	}

	@Override
	public void setMusic() throws SlickException {
		background = new Music("data/sounds/despair_tower_top.wav");
	}
}

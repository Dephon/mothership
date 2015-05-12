package game2D.states.levels;

import game2D.*;
import game2D.states.*;
import game2D.states.GameState;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public class MapThree extends GameState {
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		super.init(container, sbg);
		MapOne map = (MapOne) sbg.getState(StateEnum.GAME_LEVEL_ONE);
		players = map.getPlayers();
		// boss = new Enemy("");
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
		// Bottom Wall Obstacles
		obstacles.add(100, 530, 56, 15);
		obstacles.add(260, 530, 56, 15);
		obstacles.add(628, 530, 56, 15);
		obstacles.add(773, 530, 56, 15);
		// Right Wall Obstacles
		obstacles.add(915, 115, 20, 25);
		obstacles.add(915, 420, 20, 25);
		obstacles.add(915, 515, 20, 25);
		// Transport
		mapMover.add(0, 234, 1, 75, ThreeStateEnum.LEFT);
	}

	@Override
	public void setMusic() throws SlickException {
		bgm = new Music("data/sounds/Crateria_Underground_-_Super_Metroid.wav");
	}

	@Override
	public Vector2f defaultLeftSpawn() {
		return new Vector2f(2, 272);
	}

	@Override
	public Vector2f defaultRightSpawn() {
		return new Vector2f(900, 272);
	}

	Enemy boss;
}
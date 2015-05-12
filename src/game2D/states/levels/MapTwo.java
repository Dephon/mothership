package game2D.states.levels;

import game2D.*;
import game2D.states.*;
import game2D.states.GameState;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public class MapTwo extends GameState {
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		MapOne map = (MapOne) sbg.getState(StateEnum.GAME_LEVEL_ONE);
		super.init(container, sbg);
		enemies.enableSpawns(true);
		enemies.addSpawner(new Vector2f(940, 350), 2000, 60000, true);
		enemies.addSpawner(new Vector2f(495, 64), 2000, 60000, true);
		enemies.addSpawner(new Vector2f(460, 530), 2000, 60000, true);
		enemies.addSpawner(new Vector2f(5, 350), 2000, 60000, true);
		players = map.getPlayers();
		bgm = map.getMusic();
	}

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
		// Top Wall
		obstacles.add(0, 0, 447, 63);
		obstacles.add(544, 0, 416, 63);
		obstacles.add(0, 0, 960, 33);
		// Right Wall
		obstacles.add(938, 0, 22, 282);
		obstacles.add(938, 406, 22, 156);
		// Left Wall
		obstacles.add(0, 0, 25, 282);
		obstacles.add(0, 406, 25, 156);
		// Bottom Wall
		obstacles.add(0f, 530, 411, 30);
		obstacles.add(550, 530, 430, 30);
		// Transport
		mapMover.add(0, 283, 1, 122, ThreeStateEnum.LEFT);
		mapMover.add(959, 283, 1, 122, ThreeStateEnum.RIGHT);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		super.update(container, sbg, dt);
	};

	@Override
	public void setMusic() throws SlickException {
		bgm = new Music("data/sounds/despair_tower_top.wav");
	}

	@Override
	public Vector2f defaultLeftSpawn() {
		return new Vector2f(3, 338);
	}

	@Override
	public Vector2f defaultRightSpawn() {
		return new Vector2f(870, 344);
	}
}

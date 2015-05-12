package testclasses;

import game2D.*;
import game2D.states.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public class mockMapOne extends mockGameState {

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		super.init(container, sbg);
		levelChanged = true;
		currentLevel = StateEnum.GAME_LEVEL_ONE;
		enemies.enableSpawns(true);
		enemies.addSpawner(new Vector2f(940, 350), 2000, 20000, true);
		enemies.addSpawner(new Vector2f(495, 64), 2000, 20000, true);
		enemies.addSpawner(new Vector2f(460, 530), 2000, 20000, true);
		enemies.addSpawner(new Vector2f(5, 350), 2000, 20000, true);
	}

	@Override
	public int getID() {
		return StateEnum.GAME_LEVEL_ONE;
	}

	@Override
	public void setMap() throws SlickException {
		gameMap = new TiledMap("maps/OldMap.tmx");
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
		obstacles.add(0, 530, 411, 30);
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
		background = new Music("data/sounds/despair_tower_top.wav");
	}

	@Override
	public Vector2f defaultLeftSpawn() {
		return new Vector2f(100, 100);
	}

	@Override
	public Vector2f defaultRightSpawn() {
		return new Vector2f(100, 100);
	}

	public mockMapOne(Double thisIsATest, Double otherOne)
			throws SlickException {
		super(thisIsATest, otherOne);
	}
}
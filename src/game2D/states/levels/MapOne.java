package game2D.states.levels;

import game2D.*;
import game2D.projectiles.*;
import game2D.states.*;
import game2D.states.GameState;

import java.io.*;
import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.tiled.*;
import org.newdawn.slick.util.*;

public class MapOne extends GameState {
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		Porygon bounds;
		ArrayList<Vector2f> points = new ArrayList<Vector2f>();
		ui = new UI();
		points.add(new Vector2f(0, 0));
		points.add(new Vector2f(container.getWidth(), 0));
		points.add(new Vector2f(container.getWidth(), container.getHeight()));
		points.add(new Vector2f(0, container.getHeight()));
		bounds = new Porygon(points);
		try {
			player = new Player();
			player.setLoc(100, 100);
			player.create();
			player.setSpeed(.1f);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		gameMap = new TiledMap("maps/level_2.tmx");
		obstacleManager = new ObstacleManager(bounds, 100);
		missileManager = new MissileManager(bounds, 100);
		bulletManager = new BulletManager(bounds, 100);
		enemyManager = new EnemyManager(bounds, 100);
		enemyManager.add(new Vector2f(300, 300), new Vector2f(-1, 0));
		generateWalls(container);
		currentAmmo = AmmoEnum.BULLET;
		try {
			// backGround = AudioLoader.getAudio("WAV", ResourceLoader
			// .getResourceAsStream("data/sounds/d_e1m3.wav"));
			backGround = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("data/sounds/despair_tower_top.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		debugDraw = false;
	}

	public void generateWalls(GameContainer container) {
		obstacleManager.add(new Vector2f(), new Vector2f(container.getWidth(),
				62.5f));
		obstacleManager.add(new Vector2f(),
				new Vector2f(25, container.getHeight()));
		// obstacleManager.add(new Vector2f(0f, 1f), new Vector2f(60f, 62.5f));
		obstacleManager.add(new Vector2f(938f, 0f),
				new Vector2f(25, container.getHeight()));
		obstacleManager.add(new Vector2f(0f, 469f),
				new Vector2f(container.getWidth(), 26f));
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		Input input = container.getInput();
		super.update(container, sbg, dt);
		if (input.isKeyPressed(Input.KEY_1)) {
			currentLevel = 1;
			sbg.enterState(StateEnum.GAME_LEVEL_ONE, new FadeOutTransition(),
					new FadeInTransition());
		}
		if (input.isKeyPressed(Input.KEY_2)) {
			currentLevel = 2;
			sbg.enterState(StateEnum.GAME_LEVEL_TWO, new FadeOutTransition(),
					new FadeInTransition());
		}
		if (input.isKeyPressed(Input.KEY_3)) {
			currentLevel = 3;
			sbg.enterState(StateEnum.GAME_LEVEL_THREE, new FadeOutTransition(),
					new FadeInTransition());
		}
		if (input.isKeyPressed(Input.KEY_4)) {
			if (backGround.isPlaying())
				backGround.stop();
			else
				backGround.playAsMusic(1f, 1f, true);
		}
		if (input.isKeyPressed(Input.KEY_5)) {
			if (backGround2.isPlaying())
				backGround2.stop();
			else
				backGround2.playAsMusic(1f, 1f, true);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		gameMap.render(0, 0);
		if (debugDraw) {
			bulletManager.debugDraw(graphics);
			missileManager.debugDraw(graphics);
			player.debugDraw(graphics);
			obstacleManager.debugDraw(graphics);
			enemyManager.debugDraw(graphics);
		} else {
			bulletManager.draw();
			missileManager.draw();
			player.draw();
			obstacleManager.draw();
			enemyManager.draw();
			ui.draw();
		}
	}

	@Override
	public int getID() {
		return StateEnum.GAME_LEVEL_ONE;
	}

	public void Debug() {
		return; // Add breakpoint here
	}
}

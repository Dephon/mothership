package game2D.states.levels;

import game2D.*;
import game2D.collisions.*;
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

public class MapTwo extends GameState {
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		Porygon bounds;
		ArrayList<Vector2f> points = new ArrayList<Vector2f>();
		points.add(new Vector2f(0, 0));
		points.add(new Vector2f(container.getWidth(), 0));
		points.add(new Vector2f(container.getWidth(), container.getHeight()));
		points.add(new Vector2f(0, container.getHeight()));
		bounds = new Porygon(points);
		try {
			player = new Player();
			player.create();
			player.setLoc(100, 100);
			player.setSpeed(.1f);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		gameMap = new TiledMap("maps/level_1.tmx");
		obstacleManager = new ObstacleManager(bounds, 100);
		missileManager = new MissileManager(bounds, 100);
		bulletManager = new BulletManager(bounds, 100);
		enemyManager = new EnemyManager(bounds, 100);
		enemyManager.add(new Vector2f(300, 300), new Vector2f(-1, 0));
		obstacleManager.add(new Vector2f(), new Vector2f(container.getWidth(),
				80));
		currentAmmo = AmmoEnum.BULLET;
		try {
			backGround = AudioLoader.getAudio("WAV", ResourceLoader
					.getResourceAsStream("data/sounds/d_e1m3.wav"));
			backGround2 = AudioLoader
					.getAudio(
							"WAV",
							ResourceLoader
									.getResourceAsStream("data/sounds/Crateria_Underground_-_Super_Metroid.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		Vector2f location = new Vector2f();
		Vector2f pVector = new Vector2f();
		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.PAUSE);
			container.setPaused(true);
		} else {
			if (input.isKeyPressed(Input.KEY_SPACE)) {
				if (currentAmmo == AmmoEnum.BULLET) {
					currentAmmo = AmmoEnum.MISSILE;
				} else
					currentAmmo = AmmoEnum.BULLET;
			}
			if (input.isKeyDown(Input.KEY_W)) { // Move up
				if (player.getOriginY() >= 0)
					pVector.y += -1;
			}
			if (input.isKeyDown(Input.KEY_S)) { // Move Down
				if (player.getEndY() < container.getHeight())
					pVector.y += 1;
			}
			if (input.isKeyDown(Input.KEY_A)) { // Move Left
				if (player.getOriginX() >= 0)
					pVector.x += -1;
			}
			if (input.isKeyDown(Input.KEY_D)) { // Move Right
				if (player.getEndX() < container.getWidth())
					pVector.x += 1;
			}
			if (input.isKeyDown(Input.KEY_0)) {
				Debug();
			}
			player.update(pVector, dt);
			if (player.isDead()) {
				sbg.enterState(StateEnum.GAME_OVER, new FadeOutTransition(),
						new FadeInTransition());
			}
			if (input.isKeyPressed(Input.KEY_1)) {
				currentLevel = 1;
				sbg.enterState(StateEnum.GAME_LEVEL_ONE,
						new FadeOutTransition(), new FadeInTransition());
			}
			if (input.isKeyPressed(Input.KEY_2)) {
				currentLevel = 2;
				sbg.enterState(StateEnum.GAME_LEVEL_TWO,
						new FadeOutTransition(), new FadeInTransition());
			}
			if (input.isKeyPressed(Input.KEY_3)) {
				currentLevel = 3;
				sbg.enterState(StateEnum.GAME_LEVEL_THREE,
						new FadeOutTransition(), new FadeInTransition());
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

		if (input.isMouseButtonDown(0)) {
			location.x = player.getCenterX();
			location.y = player.getCenterY();
			pVector.x = input.getAbsoluteMouseX() - player.getCenterX();
			pVector.y = input.getAbsoluteMouseY() - player.getCenterY();
			pVector = pVector.normalise();
			if (currentAmmo == AmmoEnum.BULLET) {
				bulletManager.add(location, pVector);
			} else if (currentAmmo == AmmoEnum.MISSILE) {
				missileManager.add(location, pVector);
			}
		}
		missileManager.update(dt);
		bulletManager.update(dt);
		enemyManager.update(dt);
		player.updateAnimation();
		player.displace(obstacleManager, CollisionEnum.BLOCKING);
		player.displace(enemyManager, CollisionEnum.DAMAGING);
		bulletManager.displace(obstacleManager, CollisionEnum.BLOCKING);
		missileManager.displace(obstacleManager, CollisionEnum.BLOCKING);
		enemyManager.displace(obstacleManager, CollisionEnum.BLOCKING);
		enemyManager.displace(bulletManager, CollisionEnum.DAMAGING);
		enemyManager.displace(missileManager, CollisionEnum.DAMAGING);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		gameMap.render(0, 0);
		bulletManager.debugDraw(graphics);
		missileManager.debugDraw(graphics);
		player.debugDraw(graphics);
		obstacleManager.debugDraw(graphics);
		enemyManager.draw();
	}

	@Override
	public int getID() {
		return StateEnum.GAME_LEVEL_TWO;
	}

	public void Debug() {
		return; // Add breakpoint here
	}
}

package states;

import game2D.*;
import game2D.Collision.*;
import game2D.Projectile.*;

import java.io.*;
import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.tiled.*;
import org.newdawn.slick.util.*;

public class GameState extends BasicGameState {
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		ArrayList<Vector2f> points = new ArrayList<Vector2f>();
		points.add(new Vector2f(0, 0));
		points.add(new Vector2f(container.getWidth(), 0));
		points.add(new Vector2f(container.getWidth(), container.getHeight()));
		points.add(new Vector2f(0, container.getHeight()));
		try {
			player = new Player();
			player.create();
			player.setSpeed(.1f);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		gameMap = new TiledMap("maps/mothership level 1_basic.tmx");
		missileManager = new MissileManager(new Porygon(points), 100);
		bulletManager = new BulletManager(new Porygon(points), 100);
		currentAmmo = AmmoEnum.BULLET;
		enemyManager = new EnemyManager(new Rectangle(0, 0,
				container.getWidth(), container.getHeight()), 100);
		enemyManager.add(new Vector2f(300, 300), new Vector2f(-1, 0));
		wall = new Immovable("data/MetalBlock.png");
		wall.create();
		wall.setLoc(100, 0);

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
		ui = new UI();
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
				player.takeDamage(10);
			}
			if (input.isKeyPressed(Input.KEY_2)) {
				player.takeDamage(-10);
			}
			if (input.isKeyPressed(Input.KEY_3)) {
				if (backGround.isPlaying())
					backGround.stop();
				else
					backGround.playAsMusic(1f, 1f, true);
			}
			if (input.isKeyPressed(Input.KEY_4)) {
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
		ui.update(player.getHealth());
		player.displace(wall, CollisionEnum.BLOCKING);
		bulletManager.displace(wall, CollisionEnum.BLOCKING);
		missileManager.displace(wall, CollisionEnum.BLOCKING);
		enemyManager.displace(wall, CollisionEnum.BLOCKING);
		enemyManager.displace(bulletManager, CollisionEnum.DAMAGING);
		enemyManager.displace(missileManager, CollisionEnum.DAMAGING);
		player.displace(enemyManager, CollisionEnum.DAMAGING);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		gameMap.render(0, 0);
		bulletManager.debugDraw(graphics);
		missileManager.debugDraw(graphics);
		player.debugDraw(graphics);
		ui.draw();
		enemyManager.draw();
		wall.debugDraw(graphics);

		// bulletManager.draw();
		// missileManager.draw();
		// player.draw();
		// player.drawUI();
		// enemyManager.draw();
		// wall.draw();
	}

	@Override
	public int getID() {
		return StateEnum.GAME;
	}

	public void Debug() {
		return; // Add breakpoint here
	}

	int currentAmmo;
	Player player;
	MissileManager missileManager;
	BulletManager bulletManager;
	EnemyManager enemyManager;
	TiledMap gameMap;
	Immovable wall;
	Audio backGround;
	Audio backGround2;
	UI ui;
}

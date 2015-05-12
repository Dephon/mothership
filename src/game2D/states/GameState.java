package game2D.states;

import game2D.*;
import game2D.collisions.*;
import game2D.pickups.*;
import game2D.projectiles.*;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.tiled.*;

public abstract class GameState extends BasicGameState {
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		Porygon bounds;
		ArrayList<Vector2f> points = new ArrayList<Vector2f>();

		points.add(new Vector2f(0, 0));
		points.add(new Vector2f(container.getWidth(), 0));
		points.add(new Vector2f(container.getWidth(), 560));
		points.add(new Vector2f(0, container.getHeight()));
		bounds = new Porygon(points);

		players = new PlayerManager(bounds, 1);
		obstacles = new ObstacleManager(bounds, 100);
		missiles = new MissileManager(bounds, 100);
		bullets = new BulletManager(bounds, 100);
		enemies = new EnemyManager(bounds, 100);
		medPacks = new MedPackManager(bounds, 100);
		mapMover = new TransportManager(bounds, 2);
		ui = new UI();

		setMap();
		setWalls(container);
		setMusic();
		currentAmmo = AmmoEnum.BULLET;
		debugDraw = true;
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		Vector2f location = new Vector2f();
		Vector2f pVector = new Vector2f();
		Input input = container.getInput();

		if (levelChanged) {
			postChangeLevel(sbg);
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.PAUSE);
		} else {
			if (input.isKeyPressed(Input.KEY_SPACE))
				switchAmmo();
			if (input.isKeyDown(Input.KEY_W)) // Move up
				pVector.y += -1;
			if (input.isKeyDown(Input.KEY_S)) // Move Down
				pVector.y += 1;
			if (input.isKeyDown(Input.KEY_A)) // Move Left
				pVector.x += -1;
			if (input.isKeyDown(Input.KEY_D)) // Move Right
				pVector.x += 1;
			if (input.isKeyDown(Input.KEY_0))
				Debug();
			players.update(1, pVector, dt);
			if (players.areDead()) {
				GameState temp = (GameState) sbg
						.getState(StateEnum.GAME_LEVEL_ONE);
				temp.init(container, sbg);
				temp = (GameState) sbg.getState(StateEnum.GAME_LEVEL_TWO);
				temp.init(container, sbg);
				temp = (GameState) sbg.getState(StateEnum.GAME_LEVEL_THREE);
				temp.init(container, sbg);
				sbg.enterState(StateEnum.GAME_OVER, new FadeOutTransition(),
						new FadeInTransition());
			}

			if (input.isMouseButtonDown(0)) {
				debugX = input.getAbsoluteMouseX();
				debugY = input.getAbsoluteMouseY();
				location.x = players.fireX(1);
				location.y = players.fireY(1);
				pVector.x = input.getAbsoluteMouseX() - players.fireX(1);
				pVector.y = input.getAbsoluteMouseY() - players.fireY(1);
				pVector = pVector.normalise();
				if (currentAmmo == AmmoEnum.BULLET)
					bullets.create(location, pVector);
				else if (currentAmmo == AmmoEnum.MISSILE)
					missiles.create(location, pVector);
			}
			missiles.update(dt);
			bullets.update(dt);
			enemies.update(dt);
			enemies.update(players.getCenter(1), dt);
			players.update(dt);
			players.displace(obstacles, CollisionEnum.BLOCKING);
			players.displace(enemies, CollisionEnum.DAMAGING);
			players.displace(medPacks, CollisionEnum.MEDPACK);
			players.displace(mapMover, CollisionEnum.TRANSPORTING);
			bullets.displace(obstacles, CollisionEnum.BLOCKING);
			missiles.displace(obstacles, CollisionEnum.BLOCKING);
			enemies.displace(obstacles, CollisionEnum.BLOCKING);
			enemies.displace(bullets, CollisionEnum.DAMAGING);
			enemies.displace(missiles, CollisionEnum.DAMAGING);
			ui.update(players.getHealth(1), currentAmmo,
					players.getMissileCount(1));
			if (enemies.getActive().size() == 0) {
				changeLevel(players.checkTransport(mapMover), sbg);
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		gameMap.render(0, 0);
		if (debugDraw) {
			bullets.debugDraw(graphics);
			missiles.debugDraw(graphics);
			players.debugDraw(graphics);
			obstacles.debugDraw(graphics);
			enemies.debugDraw(graphics);
			medPacks.debugDraw(graphics);
			graphics.drawString("(" + debugX + "," + debugY + ")", 0, 0);
			// graphics.drawString("Moving to map: " + map, 400, 0);
			ui.draw();
			graphics.setColor(Color.yellow);
			if (currentAmmo == AmmoEnum.MISSILE)
				graphics.drawString(missiles.getMissileCount() + "/" + "20",
						295, 600);
			else
				graphics.drawString("infinite", 285, 600);
		} else {
			players.draw();
			obstacles.draw();
			enemies.draw();
			missiles.draw();
			bullets.draw();
			medPacks.draw();
			ui.draw();
			graphics.setColor(Color.yellow);
			if (currentAmmo == AmmoEnum.MISSILE)
				graphics.drawString(missiles.getMissileCount() + "/" + "20",
						295, 600);
			else
				graphics.drawString("infinite", 285, 600);
		}
	}

	// Transfer Data to the other state
	protected void preChangeLevel(int dir, StateBasedGame sbg) {
		GameState state;
		if (dir == ThreeStateEnum.LEFT) {
			if (currentLevel == StateEnum.GAME_LEVEL_THREE)
				resetMusic = true;
			currentLevel--;
			state = (GameState) sbg.getState(currentLevel);
			state.setMissileCount(missiles.getMissileCount());
			state.setcurrentAmmo(currentAmmo);
			players.setDir(-1, 0);
			players.setLoc(state.defaultRightSpawn());
			levelChanged = true;
		} else if (dir == ThreeStateEnum.RIGHT) {
			if (currentLevel == StateEnum.GAME_LEVEL_TWO) {
				resetMusic = true;
			}
			currentLevel++;
			state = (GameState) sbg.getState(currentLevel);
			state.setMissileCount(missiles.getMissileCount());
			state.setcurrentAmmo(currentAmmo);
			players.setDir(1, 0);
			players.setLoc(state.defaultLeftSpawn());
			levelChanged = true;
		}
	}

	// Change state
	protected void changeLevel(int dir, StateBasedGame sbg) {
		if (dir != ThreeStateEnum.NONE) {
			preChangeLevel(dir, sbg);
			sbg.enterState(currentLevel, new FadeOutTransition(),
					new FadeInTransition());
		}
	}

	// Process state change
	protected void postChangeLevel(StateBasedGame sbg) {
		levelChanged = false;
		if (resetMusic) {
			bgm.loop();
			resetMusic = false;
		}
	}

	public void setMissileCount(int count) {
		missiles.setMissileCount(count);
	}

	public Music getMusic() {
		return bgm;
	}

	public void setPlayers(PlayerManager info) {
		players = info;
	}

	public PlayerManager getPlayers() {
		return players;
	}

	protected void switchAmmo() {
		if (currentAmmo == AmmoEnum.BULLET)
			currentAmmo = AmmoEnum.MISSILE;
		else
			currentAmmo = AmmoEnum.BULLET;
	}

	public boolean isLevelChanged() {
		return levelChanged;
	}

	public void Debug() {
		return; // Add breakpoint here
	}

	public MedPackManager getMedPacks() {
		return medPacks;
	}

	public int getMissileCount() {
		return missiles.getMissileCount();
	}

	@Override
	public abstract int getID();

	public void setcurrentAmmo(int ammoEnum) {
		currentAmmo = ammoEnum;
	}

	public int getcurrentAmmo() {
		return currentAmmo;
	}

	protected abstract void setMap() throws SlickException;

	protected abstract void setWalls(GameContainer container)
			throws SlickException;

	protected abstract void setMusic() throws SlickException;

	public abstract Vector2f defaultLeftSpawn();

	public abstract Vector2f defaultRightSpawn();

	protected int debugX, debugY;
	protected PlayerManager players;
	protected ObstacleManager obstacles;
	protected MissileManager missiles;
	protected BulletManager bullets;
	protected EnemyManager enemies;
	protected MedPackManager medPacks;
	protected TransportManager mapMover;
	protected TiledMap gameMap;
	protected Music bgm;
	protected UI ui;
	protected int map;
	protected int currentAmmo;
	protected boolean debugDraw;
	protected static boolean levelChanged;
	protected static int currentLevel;
	protected static boolean resetMusic;
}

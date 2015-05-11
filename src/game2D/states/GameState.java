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
		points.add(new Vector2f(container.getWidth(), container.getHeight()));
		points.add(new Vector2f(0, container.getHeight()));
		bounds = new Porygon(points);

		players = new PlayerManager(bounds, 1);
		obstacles = new ObstacleManager(bounds, 100);
		missiles = new MissileManager(bounds, 100);
		bullets = new BulletManager(bounds, 100);
		enemies = new EnemyManager(bounds, 100);
		medPacks = new MedPackManager(bounds, 100);
		ui = new UI();

		enemies.add(300, 300, 0, 0);
		players.add(100, 100, 1, 0);
		medPacks.add(500, 200, 0, 0);

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
			postChangeLevel();
			levelChanged = false;
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
					bullets.add(location, pVector);
				else if (currentAmmo == AmmoEnum.MISSILE)
					missiles.add(location, pVector);
			}
			missiles.update(dt);
			bullets.update(dt);
			enemies.update(dt);
			enemies.update(players.getCenter(1), dt);
			players.update(dt);
			players.displace(obstacles, CollisionEnum.BLOCKING);
			players.displace(enemies, CollisionEnum.DAMAGING);
			players.displace(medPacks, CollisionEnum.MEDPACK);
			bullets.displace(obstacles, CollisionEnum.BLOCKING);
			missiles.displace(obstacles, CollisionEnum.BLOCKING);
			enemies.displace(obstacles, CollisionEnum.BLOCKING);
			enemies.displace(bullets, CollisionEnum.DAMAGING);
			enemies.displace(missiles, CollisionEnum.DAMAGING);

			ui.update(players.getHealth(1), currentAmmo);
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
			ui.draw();
		} else {
			bullets.draw();
			missiles.draw();
			players.draw();
			obstacles.draw();
			enemies.draw();
			medPacks.draw();
			ui.draw();
		}
	}

	// public void getPlayerInfo(GameState gs) {
	//
	// }
	//
	// public void setPlayerInfo(GameState gs) {
	//
	// }

	protected void switchAmmo() {
		if (currentAmmo == AmmoEnum.BULLET)
			currentAmmo = AmmoEnum.MISSILE;
		else
			currentAmmo = AmmoEnum.BULLET;
	}

	public boolean isLevelChanged() {
		return levelChanged;
	}

	protected void postChangeLevel() {
		if (isLevelChanged()) {
			background.loop();
		}
	}

	public void Debug() {
		return; // Add breakpoint here
	}

	@Override
	public abstract int getID();

	public abstract void setMap() throws SlickException;

	public abstract void setWalls(GameContainer container)
			throws SlickException;

	public abstract void setMusic() throws SlickException;

	protected int debugX, debugY;
	protected PlayerManager players;
	protected ObstacleManager obstacles;
	protected MissileManager missiles;
	protected BulletManager bullets;
	protected EnemyManager enemies;
	protected MedPackManager medPacks;
	protected TiledMap gameMap;
	protected Music background;
	protected UI ui;
	protected int currentAmmo;
	public static int currentLevel;
	protected boolean debugDraw;
	protected boolean levelChanged;
}

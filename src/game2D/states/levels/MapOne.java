package game2D.states.levels;

import game2D.*;
import game2D.collisions.*;
import game2D.projectiles.*;
import game2D.states.*;
import game2D.states.GameState;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.tiled.*;

public class MapOne extends GameState {

	Image nonTiledBackground;
	Image nonTiledMap;
	Image bridge;
	Image ship;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		super.init(container, sbg);
		Porygon bounds;
		ArrayList<Vector2f> points = new ArrayList<Vector2f>();

		points.add(new Vector2f(0, 0));
		points.add(new Vector2f(container.getWidth(), 0));
		points.add(new Vector2f(container.getWidth(), 560));
		points.add(new Vector2f(0, container.getHeight()));
		bounds = new Porygon(points);
		enemies = new EnemyManager(true, bounds, 100);

		currentLevel = StateEnum.GAME_LEVEL_ONE;
		players.add(defaultLeftSpawn().x, defaultLeftSpawn().y, 1, 0);
		resetMusic = true;
		nonTiledBackground = new Image("maps/gottagofast.png")
				.getScaledCopy(1.9f);
		nonTiledMap = new Image("maps/rock3.png").getScaledCopy(3);
		bridge = new Image("maps/rock2.png").getScaledCopy(3);
		ship = new Image("maps/ship.png");
		ship.rotate(30);
		bridge.rotate(270);
		enemies.enableSpawns(true);
		enemies.addSpawner(new Vector2f(0, 0), 5000, 20000, true);
		enemies.addSpawner(new Vector2f(0, 100), 2000, 20000, true);
		enemies.addSpawner(new Vector2f(100, 0), 2000, 20000, true);
		enemies.addSpawner(new Vector2f(200, 560), 2000, 20000, true);
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
		obstacles.add(287, 200, 700, 27);
		// Left Wall
		obstacles.add(310, 208, 35, 300);
		// Bottom Wall
		obstacles.add(287, 477, 700, 27);
		// Right Wall
		obstacles.add(606, 218, 130, 118);
		// Transport
		mapMover.add(959, 227, 1, 250, ThreeStateEnum.RIGHT);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		Vector2f location = new Vector2f();
		Vector2f pVector = new Vector2f();
		Input input = container.getInput();

		if (levelChanged || resetMusic) {
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
				players.get(1).takeDamage(10);
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
	public void setMusic() throws SlickException {
		bgm = new Music("data/sounds/despair_tower_top.wav");
	}

	@Override
	public Vector2f defaultLeftSpawn() {
		return new Vector2f(358, 335);
	}

	@Override
	public Vector2f defaultRightSpawn() {
		return new Vector2f(910, 335);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		nonTiledBackground.draw();
		bridge.draw(500, 200);
		nonTiledMap.draw(700, 190);
		nonTiledMap.draw(325, 190);
		ship.draw(327, 366);
		if (debugDraw) {
			bullets.debugDraw(graphics);
			missiles.debugDraw(graphics);
			players.debugDraw(graphics);
			obstacles.debugDraw(graphics);
			enemies.debugDraw(graphics);
			medPacks.debugDraw(graphics);
			graphics.drawString("(" + debugX + "," + debugY + ")", 0, 0);
			graphics.drawString("Moving to map: " + map, 400, 0);
			ui.draw();
			graphics.setColor(Color.yellow);
			if (currentAmmo == AmmoEnum.MISSILE)
				graphics.drawString(missiles.getMissileCount() + "/" + "10",
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
}
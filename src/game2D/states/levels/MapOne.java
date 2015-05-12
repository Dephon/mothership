package game2D.states.levels;

import game2D.*;
import game2D.collisions.*;
import game2D.projectiles.*;
import game2D.states.*;
import game2D.states.GameState;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.tiled.*;

public class MapOne extends GameState {

	Image nonTiledBackground;
	Image nonTiledMap;
	Image bridge;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		super.init(container, sbg);
		currentLevel = StateEnum.GAME_LEVEL_ONE;
		bgm.loop();
		nonTiledBackground = new Image("maps/gottagofast.png")
				.getScaledCopy(1.9f);
		nonTiledMap = new Image("maps/rock3.png").getScaledCopy(3);
		bridge = new Image("maps/rock2.png").getScaledCopy(3);
		bridge.rotate(270);
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
		obstacles.add(288, 208, 35, 300);
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
			if (enemies.getActive().size() == 0)
				map = players.checkTransport(mapMover);
			changeMap(map, sbg);
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
		return new Vector2f(949, 339);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		nonTiledBackground.draw();
		bridge.draw(500, 200);
		nonTiledMap.draw(700, 190);
		nonTiledMap.draw(325, 190);

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
		} else {
			players.draw();
			obstacles.draw();
			enemies.draw();
			missiles.draw();
			bullets.draw();
			medPacks.draw();
			ui.draw();
		}
	}
}
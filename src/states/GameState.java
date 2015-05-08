package states;

import game2D.*;
import game2D.Projectile.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.tiled.*;

public class GameState extends BasicGameState {
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		player = new Player("data/S3K_Hyper_Knuckles.gif", new Vector2f());
		player.create();
		player.setSpeed(.1f);
		gameMap = new TiledMap("maps/mothership level 1_basic.tmx");
		ammoManager = new AmmoManager(new Rectangle(0, 0, container.getWidth(),
				container.getHeight()), 100);
		ammoManager.setAmmo(AmmoEnum.BULLET);
		enemyManager = new EnemyManager(new Rectangle(0, 0,
				container.getWidth(), container.getHeight()), 100);
		enemyManager.add(new Vector2f(300, 300), new Vector2f(-1, 0));
		wall = new Immovable("data/MetalBlock.png");
		wall.create();
		wall.setLoc(100, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		Vector2f location = new Vector2f();
		Vector2f pVector = new Vector2f();
		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.PAUSE);
			// container.setPaused(true);
		} else {
			if (input.isKeyPressed(Input.KEY_SPACE)) {
				if (ammoManager.getAmmo() == AmmoEnum.BULLET) {
					ammoManager.setAmmo(AmmoEnum.MISSILE);
				} else
					ammoManager.setAmmo(AmmoEnum.BULLET);
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
		}

		if (input.isMouseButtonDown(0)) {
			location.x = player.getCenterX();
			location.y = player.getCenterY();
			pVector.x = input.getAbsoluteMouseX() - player.getCenterX();
			pVector.y = input.getAbsoluteMouseY() - player.getCenterY();
			pVector = pVector.normalise();
			ammoManager.add(location, pVector);
		}
		ammoManager.update(dt);
		enemyManager.update(dt);
		player.updateAnimation();
		player.updateUI();
		player.displace(wall);
		ammoManager.displace(wall);
		enemyManager.displace(wall);
		enemyManager.displace(player);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		gameMap.render(0, 0);
		graphics.drawString("Index: " + ammoManager.getMissileIndex(), 800, 0);
		ammoManager.debugDraw(graphics);
		player.debugDraw(graphics);
		player.drawUI();
		enemyManager.draw();
		wall.debugDraw(graphics);
	}

	@Override
	public int getID() {
		return StateEnum.GAME;
	}

	public void Debug() {
		return; // Add breakpoint here
	}

	Player player;
	AmmoManager ammoManager;
	EnemyManager enemyManager;
	TiledMap gameMap;
	Immovable wall;
}

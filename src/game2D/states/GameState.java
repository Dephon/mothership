package game2D.states;

import game2D.*;
import game2D.collisions.*;
import game2D.projectiles.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;
import org.newdawn.slick.tiled.*;

public abstract class GameState extends BasicGameState {
	@Override
	public abstract void init(GameContainer container, StateBasedGame sbg)
			throws SlickException;

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
				switchAmmo();
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

			ui.update(player.getHealth());
		}
	}

	@Override
	public abstract void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException;

	@Override
	public abstract int getID();

	public abstract void Debug();

	public void switchAmmo() {
		if (currentAmmo == AmmoEnum.BULLET) {
			currentAmmo = AmmoEnum.MISSILE;
		} else
			currentAmmo = AmmoEnum.BULLET;
	}

	protected Player player;
	protected ObstacleManager obstacleManager;
	protected MissileManager missileManager;
	protected BulletManager bulletManager;
	protected EnemyManager enemyManager;
	protected TiledMap gameMap;
	protected Audio backGround;
	protected Audio backGround2;
	protected int currentAmmo;
	public static int currentLevel;
	protected boolean debugDraw;
	protected UI ui;
}

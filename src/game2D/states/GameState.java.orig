package game2D.states;

import game2D.*;
import game2D.projectiles.*;

import org.newdawn.slick.*;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;

public abstract class GameState extends BasicGameState {
	@Override
	public abstract void init(GameContainer container, StateBasedGame sbg)
			throws SlickException;

	@Override
	public abstract void update(GameContainer container, StateBasedGame sbg,
			int dt) throws SlickException;
		ui.update(player.getHealth());

	@Override
	public abstract void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException;
		ui.draw();

	@Override
	public abstract int getID();

	public abstract void Debug();

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
}

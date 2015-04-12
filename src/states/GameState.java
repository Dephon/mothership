package states;

import game2D.*;
import game2D.Projectile.*;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class GameState extends BasicGameState {
	Player player;
	Entity[] enemies;
	TiledMap gameMap;
	ArrayList<Ammo> ammo;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		player = new Player("data/S3K_Hyper_Knuckles.gif", 0, 0); // Placeholder
		gameMap = new TiledMap(new Image("data/Steel_Plate.png"), 100, 100);
		ammo = new ArrayList<Ammo>();
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int dt)
			throws SlickException {
		Vector2f pVector = new Vector2f();
		Input input = container.getInput();
		Ammo tempAmmo;

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(StateEnum.PAUSE);
			container.setPaused(true);
		} else {
			if (input.isKeyDown(Input.KEY_W)) { // Move up
				if (player.getOriginY() > gameMap.getOriginY())
					pVector.y += -.1 * dt;
			}
			if (input.isKeyDown(Input.KEY_S)) { // Move Down
				if (player.getEndY() < container.getHeight())
					pVector.y += .1 * dt;
			}
			if (input.isKeyDown(Input.KEY_A)) { // Move Left
				if (player.getOriginX() > gameMap.getOriginX())
					pVector.x += -.1 * dt;
			}
			if (input.isKeyDown(Input.KEY_D)) { // Move Right
				if (player.getEndX() < container.getWidth())
					pVector.x += .1 * dt;
			}
			player.update(pVector);
		}

		if (input.isMousePressed(0)) {
			pVector.x = input.getAbsoluteMouseX() - player.getCenterX();
			pVector.y = input.getAbsoluteMouseY() - player.getCenterY();
			pVector = pVector.normalise();
			tempAmmo = AmmoFactory.getAmmo(AmmoEnum.BULLET);
			tempAmmo.setLoc(new Vector2f(player.getEndX(), player.getEndY()));
			tempAmmo.setVelocity(pVector);
			ammo.add(tempAmmo);
		}
		for (int i = 0; i < ammo.size(); i++)
			ammo.get(i).update(dt);
		if (input.isKeyDown(Input.KEY_Q)) {
			container.exit();
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		gameMap.draw();
		for (int i = 0; i < ammo.size(); i++)
			ammo.get(i).draw();
		player.draw();
	}

	@Override
	public int getID() {
		return StateEnum.GAME;
	}

}

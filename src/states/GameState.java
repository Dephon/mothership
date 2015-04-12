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
	int fireTimer;

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		player = new Player("data/S3K_Hyper_Knuckles.gif", 0, 0); // Placeholder
		gameMap = new TiledMap(new Image("data/Steel_Plate.png"), 100, 100);
		ammo = new ArrayList<Ammo>();
		fireTimer = 0;
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

		if (input.isMouseButtonDown(0)) {
			if (fireTimer > 100) { // You can only fire once every 100ms
				pVector.x = input.getAbsoluteMouseX() - player.getCenterX();
				pVector.y = input.getAbsoluteMouseY() - player.getCenterY();
				pVector = pVector.normalise();
				tempAmmo = AmmoFactory.getAmmo(AmmoEnum.BULLET);
				tempAmmo.setLoc(new Vector2f(player.getCenterX()
						- tempAmmo.getCenterX(), player.getCenterY()
						- tempAmmo.getCenterY()));
				tempAmmo.setVelocity(pVector);
				ammo.add(tempAmmo);
				fireTimer = 0;
			} else {
				fireTimer += dt;
			}
		}
		for (int i = 0; i < ammo.size(); i++) {
			if (ammo.get(i).getEndX() < 0 || ammo.get(i).getEndY() < 0) {
				ammo.remove(i);
			} else if (ammo.get(i).getOriginX() > container.getWidth()
					|| ammo.get(i).getOriginY() > container.getHeight()) {
				ammo.remove(i);
			} else {
				ammo.get(i).update(dt);
			}

		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg,
			Graphics graphics) throws SlickException {
		gameMap.draw();
		for (int i = 0; i < ammo.size(); i++)
			ammo.get(i).draw();
		player.draw();
		graphics.drawString("Bullets: " + ammo.size(), 0, 0);
	}

	@Override
	public int getID() {
		return StateEnum.GAME;
	}

}

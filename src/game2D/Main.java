package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import states.*;
import states.GameState;

/**
 */
public class Main extends StateBasedGame {

	public Main() {
		super("Operation Mothership");
	}

	public static void main(String[] arguments) {
		try {
			AppGameContainer app = new AppGameContainer(new Main());
			app.setDisplayMode(500, 400, false);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new MainMenuState());
		this.addState(new GameState());
		this.addState(new GameOverState());
		this.addState(new PauseState());

	}
}

/*
 * package game2D;
 * 
 * import java.util.logging.*;
 * 
 * import org.newdawn.slick.*; import org.newdawn.slick.geom.*;
 * 
 * public class Main extends BasicGame { private Image knucklesRight; private
 * Image knucklesLeft; private Image currentKnuckles; private Vector2f x; //
 * Displacement private Vector2f v; // Velocity private Vector2f a; //
 * Acceleration
 * 
 * public Main(String gamename) { super(gamename); }
 * 
 * @Override public void init(GameContainer gc) throws SlickException {
 * knucklesRight = new Image("data/S3K_Hyper_Knuckles.gif"); knucklesLeft =
 * knucklesRight.getFlippedCopy(true, false); x = new Vector2f(120f, 0f); v =
 * new Vector2f(0.1f, 0f); a = new Vector2f(0.01f, 0f); currentKnuckles =
 * knucklesRight; // Rectangle rect = new Rectangle(0, 0, 0, 0); }
 * 
 * @Override public void update(GameContainer gc, int dt) throws SlickException
 * { if (x.x > 640f) { v.x *= -1; a.x *= -1; currentKnuckles = knucklesLeft; }
 * else if (x.x < 0f - knucklesLeft.getWidth()) { v.x *= -1; a.x *= -1;
 * currentKnuckles = knucklesRight; } x.x += v.x * dt + .5 * a.x * (dt * dt);
 * x.y += v.y * dt + .5 * a.x * (dt * dt); }
 * 
 * @Override public void render(GameContainer gc, Graphics g) throws
 * SlickException { g.drawString("Howdy!", 100, 100); //
 * g.drawString(sprite.getName(), 200, 100); g.drawImage(currentKnuckles, x.x,
 * x.y); }
 * 
 * public static void main(String[] args) { try { AppGameContainer appgc; appgc
 * = new AppGameContainer(new Main("S3K")); appgc.setDisplayMode(640, 480,
 * false); appgc.start(); } catch (SlickException ex) {
 * Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex); } } }
 */

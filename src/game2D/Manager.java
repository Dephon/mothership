package game2D;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public interface Manager {
	public void add(Vector2f position, Vector2f direction);

	public void destroy(int ndx);

	public void update(int dt);

	public void draw();

	public void debugDraw(Graphics graphics);

	public void displace(Entity second);

	public void displace(Manager second);
}
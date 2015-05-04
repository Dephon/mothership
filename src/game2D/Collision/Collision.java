package game2D.Collision;

import game2D.*;

import org.newdawn.slick.geom.*;

public class Collision {
	// TODO: return a displacement vector that can move the player out of the
	// intersection of the obstacle
	public static Vector2f intersects(Entity first, Entity second) {
		int i;
		float mag = 0, temp;
		float[] projA, projB;
		Porygon a = first.getPolygon();
		Porygon b = second.getPolygon();
		Vector2f normal;
		Vector2f finished = new Vector2f();

		for (i = 0; i < a.getSideCount(); i++) {
			normal = a.getNormalOfSide(i);
			projA = vProjection(a, normal);
			projB = vProjection(b, normal);

			if (projA[1] > projB[0]) {
				temp = Math.abs(projB[0] - projA[1]);
				if (i == 0) {
					mag = temp;
				} else {
					if (mag > temp) {
						finished.set(normal);
						finished = finished.negate();
						mag = temp;
					}
				}
			} else {
				finished.set(0, 0);
				return finished;
			}
		}
		return finished;
	}

	private static float[] vProjection(Porygon a, Vector2f normal) {
		int i;
		float temp;
		float[] bounds = { 0, 0 };
		for (i = 0; i < a.getPointCount(); i++) {
			temp = normal.dot(a.getPointVect(i));
			if (i == 0) {
				bounds[0] = temp;
				bounds[1] = bounds[0];
			} else {
				if (temp > bounds[1])
					bounds[1] = temp;
				else if (temp < bounds[0])
					bounds[0] = temp;
			}
		}
		return bounds;
	}
}

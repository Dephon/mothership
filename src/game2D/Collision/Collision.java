package game2D.collision;

import game2D.*;
import game2D.abstracts.*;

import java.util.*;

import org.newdawn.slick.geom.*;

public class Collision {
	public static Vector2f intersects(Entity first, Entity second) {
		ArrayList<Vector2f> normalList = new ArrayList<Vector2f>();
		float temp, magnitude = 0;
		float[] projA, projB;
		int ndx;
		Porygon a = first.getPolygon();
		Porygon b = second.getPolygon();
		Vector2f seperation = new Vector2f();

		for (ndx = 0; ndx < a.getSideCount(); ndx++) {
			normalList.add(a.getNormalOfSide(ndx));
		}
		for (ndx = 0; ndx < b.getSideCount(); ndx++) {
			normalList.add(b.getNormalOfSide(ndx));
		}

		for (ndx = 0; ndx < normalList.size(); ndx++) {
			projA = vProjection(a, normalList.get(ndx));
			projB = vProjection(b, normalList.get(ndx));

			if (projA[1] > projB[0]) {
				temp = Math.abs(projB[0] - projA[1]);
				if (ndx == 0 || magnitude > temp) {
					seperation.set(normalList.get(ndx));
					seperation = seperation.negate();
					magnitude = temp;
				}
			} else {
				seperation.set(0, 0);
				return seperation;
			}
		}
		seperation.scale(magnitude);
		return seperation;
	}

	public static Vector2f intersects(Entity first, Porygon second) {
		ArrayList<Vector2f> normalList = new ArrayList<Vector2f>();
		float temp, magnitude = 0;
		float[] projA, projB;
		int ndx;
		Porygon a = first.getPolygon();
		Porygon b = second;
		Vector2f seperation = new Vector2f();

		for (ndx = 0; ndx < a.getSideCount(); ndx++) {
			normalList.add(a.getNormalOfSide(ndx));
		}
		for (ndx = 0; ndx < b.getSideCount(); ndx++) {
			normalList.add(b.getNormalOfSide(ndx));
		}

		for (ndx = 0; ndx < normalList.size(); ndx++) {
			projA = vProjection(a, normalList.get(ndx));
			projB = vProjection(b, normalList.get(ndx));

			if (projA[1] > projB[0]) {
				temp = Math.abs(projB[0] - projA[1]);
				if (ndx == 0 || magnitude > temp) {
					seperation.set(normalList.get(ndx));
					seperation = seperation.negate();
					magnitude = temp;
				}
			} else {
				seperation.set(0, 0);
				return seperation;
			}
		}
		seperation.scale(magnitude);
		return seperation;
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

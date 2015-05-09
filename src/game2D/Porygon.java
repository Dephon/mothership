package game2D;

import java.util.*;

import org.newdawn.slick.geom.*;

public class Porygon extends Polygon {

	public Porygon() {
		super();
		sides = new ArrayList<Vector2f>();
		normalOfSides = new ArrayList<Vector2f>();
		pointCount = 0;
	}

	public Porygon(ArrayList<Vector2f> points) {
		super();
		sides = new ArrayList<Vector2f>();
		normalOfSides = new ArrayList<Vector2f>();
		pointCount = 0;
		for (Vector2f point : points)
			addPoint(point.x, point.y);
	}

	@Override
	public void addPoint(float x, float y) {
		super.addPoint(x, y);
		pointCount++;
		generateSides();
		generateNormalOfSides();
	}

	public void setPoint(int ndx, float x, float y) {
		points[ndx * 2] = x;
		points[ndx * 2 + 1] = y;
	}

	@Override
	public float[] getPoint(int ndx) {
		return super.getPoint(ndx % pointCount);
	}

	public Vector2f getPointVect(int ndx) {
		float[] point = getPoint(ndx);
		return new Vector2f(point[0], point[1]);
	}

	public int getPointCount() {
		return pointCount;
	}

	public int getSideCount() {
		return sides.size();
	}

	public Vector2f getNormalOfSide(int sideNum) {
		return normalOfSides.get(sideNum);
	}

	public void rotate(double theta) {
		float x_point, y_point;
		float temp_x, temp_y;
		float x_center = this.center[0];
		float y_center = this.center[1];
		float rad = (float) Math.toRadians(theta);

		for (int i = 0; i < pointCount; i++) {
			x_point = getPoint(i)[0];
			y_point = getPoint(i)[1];
			x_point -= x_center;
			y_point -= y_center;
			temp_x = (float) (x_point * Math.cos(rad) - y_point * Math.sin(rad));
			temp_y = (float) (x_point * Math.sin(rad) + y_point * Math.cos(rad));
			temp_x += x_center;
			temp_y += y_center;
			setPoint(i, temp_x, temp_y);
		}
		generateSides();
		generateNormalOfSides();
	}

	protected void generateSides() {
		float x, y;
		if (pointCount > 1) {
			sides.clear();
			for (int i = 0; i < pointCount; i++) {
				x = getPoint(i + 1)[0] - getPoint(i)[0];
				y = getPoint(i + 1)[1] - getPoint(i)[1];
				sides.add(new Vector2f(x, y));
			}
		}
	}

	protected void generateNormalOfSides() {
		Vector2f temp;
		if (pointCount > 1) {
			normalOfSides.clear();
			for (int i = 0; i < pointCount; i++) {
				temp = new Vector2f();
				temp.set(sides.get(i).y, -1 * sides.get(i).x);
				temp = temp.normalise();
				normalOfSides.add(temp);
			}
		}
	}

	protected ArrayList<Vector2f> sides;
	protected ArrayList<Vector2f> normalOfSides;

	protected int pointCount;
	protected static final long serialVersionUID = 1L;
}

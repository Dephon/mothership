package game2D;

import java.util.*;

import org.newdawn.slick.geom.*;

public class Porygon extends Polygon {

	public Porygon() {
		super();
		sides = new ArrayList<Vector2f>();
		pointCount = 0;
	}

	public Porygon(float[] point) {
		super(point);
		sides = new ArrayList<Vector2f>();
		pointCount = points.length;
		generateSides();
	}

	@Override
	public void addPoint(float x, float y) {
		super.addPoint(x, y);
		pointCount++;
		generateSides();
	}

	public void setPoint(int ndx, float x, float y) {
		points[ndx * 2] = x;
		points[ndx * 2 + 1] = y;
	}

	@Override
	public float[] getPoint(int ndx) {
		return super.getPoint(ndx % pointCount);
	}

	private void generateSides() {
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
	}

	private ArrayList<Vector2f> sides;
	private int pointCount;
	private static final long serialVersionUID = 1L;
}

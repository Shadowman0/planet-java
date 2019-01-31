package socket.rocket.model;

import java.awt.Point;
import java.util.Arrays;

public class Vector2d {
	public double[] value = new double[2];

	public Vector2d(double x, double y) {
		setValue(x, y);
	}

	public Vector2d(Vector2d vector) {
		setValue(vector.value[0], vector.value[1]);
	}

	public Vector2d(Point point) {
		setValue(point.getX(), point.getY());
	}

	@Override
	public String toString() {
		return "Vector [value=" + Arrays.toString(value) + "]";
	}

	public void setValue(double x, double y) {
		value[0] = x;
		value[1] = y;
	}

	private void setValue(Vector2d vector) {
		value[0] = vector.value[0];
		value[1] = vector.value[1];
	}

	public Vector2d scaleBy(double scalar) {
		return new Vector2d(value[0] * scalar, value[1] * scalar);

	}

	public Vector2d plus(Vector2d vector) {
		return new Vector2d(
				this.value[0] + vector.value[0],
				this.value[1] + vector.value[1]);
	}

	public Vector2d multiplyAdd(Vector2d vector, double scalar) {
		return new Vector2d(
				this.value[0] + scalar * vector.value[0],
				this.value[1] + scalar * vector.value[1]);
	}

	public double distanceTo(Vector2d vector) {
		return this.multiplyAdd(vector, -1).norm();
	}

	public double norm() {
		return Math.sqrt(value[0] * value[0] + value[1] * value[1]);
	}

	// public Vector calcNormedDistance(Vector vector) {
	// double distance = calcDistance(vector);
	// return multByScalar(Math.pow(distance, -3));
	//
	// }

	public Point toPoint(double scaling) {
		Point point = new Point((int) Math.round(value[0] * scaling), (int) Math.round(value[1] * scaling));
		return point;
	}

	public Point toPoint(double scaling, Point offset) {
		Point point = new Point((int) (Math.round(value[0] * scaling) + offset.getX()),
				(int) (Math.round(value[1] * scaling) + offset.getY()));
		return point;
	}

}

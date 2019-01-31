package socket.rocket.model;

import java.awt.Point;
import java.util.Arrays;

public class Vector {
    public double[] value = new double[2];

    public Vector(double x, double y) {
        setValue(x, y);
    }

    public Vector(Vector vector) {
        setValue(vector.value[0], vector.value[1]);
    }

    public Vector(Point point) {
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

    public void setValue(Vector vector) {
        value[0] = vector.value[0];
        value[1] = vector.value[1];
    }

    public Vector multByScalar(double scalar) {
        return new Vector(value[0] * scalar, value[1] * scalar);
    }

    public void addVector(Vector vector) {
        this.value[0] += vector.value[0];
        this.value[1] += vector.value[1];
    }

    public void addVector(Vector vector, double scalar) {
        this.value[0] += scalar * vector.value[0];
        this.value[1] += scalar * vector.value[1];
    }

    public Vector addVector2(Vector vector) {
        Vector result = new Vector(this);
        result.value[0] += vector.value[0];
        result.value[1] += vector.value[1];
        return result;
    }

    public Vector addVector2(Vector vector, double scalar) {
        Vector result = new Vector(this);
        result.value[0] += scalar * vector.value[0];
        result.value[1] += scalar * vector.value[1];
        return result;
    }

    public double calcDistance(Vector vector) {
        return this.addVector2(vector, -1).norm();
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

    public Point toPointWithOffset(double scaling, Point offset) {
        Point point = new Point((int) Math.round(offset.x / scaling + value[0] * scaling), (int) Math.round(offset.y / scaling + value[1] * scaling));
        return point;
    }

    public Point toPoint(double scaling, Point offset) {
        Point point = new Point((int) (Math.round(value[0] * scaling) + offset.getX()), (int) (Math.round(value[1] * scaling) + offset.getY()));
        return point;
    }

    public Vector rotate(double angle) {
        Vector result = new Vector(
                Math.cos(value[0]) - Math.sin(value[1]),
                Math.sin(value[0]) + Math.cos(value[1]));
        return result;
    }

    public Vector normalize(double scalar) {
        return new Vector(this).multByScalar(scalar / norm());
    }

}

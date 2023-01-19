package de.ideaonic703.math;

import java.awt.*;
import java.util.Vector;

public class Vector2D {
    public static Vector2D ZERO = new Vector2D(0, 0);
    public static Vector2D ONE = new Vector2D(1, 1);
    private int x, y;
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static Vector2D fromDimension(Dimension dim) {
        return new Vector2D((int)dim.getWidth(), (int)dim.getHeight());
    }
    public static Vector2D fromPoint(Point point) {
        return new Vector2D((int)point.getX(), (int)point.getY());
    }
    public Dimension toDimension() {
        return new Dimension(x, y);
    }
    public Point toPoint() {
        return new Point(x, y);
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public double getLength() {
        return Math.sqrt(x*x+y*y);
    }
    public void multiply(Vector2D other) {
        this.x *= other.getX();
        this.y *= other.getY();
    }
    public void multiply(int otherX, int otherY) {
        this.x *= otherX;
        this.y *= otherY;
    }
    public void scale(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }
    public void add(int summand) {
        this.x += summand;
        this.y += summand;
    }
    public void add(Vector2D other) {
        this.x += other.getX();
        this.y += other.getY();
    }
    public void add(int otherX, int otherY) {
        this.x += otherX;
        this.y += otherY;
    }
    public Vector2D limit(Vector2D upperBound) {
        return new Vector2D(Math.min(x, upperBound.getX()), Math.min(y, upperBound.getY()));
    }
    public Vector2D translate(Vector2D offset) {
        return new Vector2D(x+offset.x, y+offset.y);
    }
    public void reverseX() {
        this.x *= -1;
    }
    public void reverseY() {
        this.y *= -1;
    }
    public void reverse() {
        this.x *= -1;
        this.y *= -1;
    }
    public Vector2D copy() {
        return new Vector2D(x, y);
    }

    public void divide(int scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }
}

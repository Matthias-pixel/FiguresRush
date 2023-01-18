package de.ideaonic703.math;

import java.util.Vector;

public class Vector2D {
    private int x, y;
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
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
}

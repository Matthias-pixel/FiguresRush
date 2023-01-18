package de.ideaonic703.logic;

import de.ideaonic703.GeometryDash;
import de.ideaonic703.math.Vector2D;

import java.awt.*;

public class RectBody {
    private Vector2D size;
    private Vector2D position;
    private Vector2D velocity;
    public RectBody(Vector2D position, Vector2D size, Vector2D velocity) {
        this.position = position;
        this.size = size;
        this.velocity = velocity;
    }
    public RectBody(Vector2D position, Vector2D size) {
        this(position, size, new Vector2D(0, 0));
    }
    public RectBody(int x, int y, int width, int height, int velX, int velY) {
        this(new Vector2D(x, y), new Vector2D(width, height), new Vector2D(velX, velY));
    }
    public RectBody(int x, int y, int width, int height) {
        this(x, y, width, height, 0, 0);
    }
    public void tick() {
        position.add(velocity);
    }
    public Vector2D getPosition() {
        return position;
    }
    public Vector2D getSize() {
        return size;
    }
    public Vector2D getVelocity() {
        return velocity;
    }
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public int getWidth() {
        return size.getX();
    }
    public int getHeight() {
        return size.getY();
    }
    public void reverseX() {
        velocity.multiply(-1, 1);
    }
    public void reverseY() {
        velocity.multiply(1, -1);
    }
    public void reverse() {
        velocity.multiply(-1, -1);
    }
}

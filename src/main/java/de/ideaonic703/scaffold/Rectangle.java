package de.ideaonic703.scaffold;

import de.ideaonic703.math.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Rectangle extends Element {
    private Color color;
    private GradientPaint gradient;
    private boolean filled = true;
    public Rectangle(Vector2D position, Vector2D size, Vector2D velocity, Color color) {
        this.position = position;
        this.size = size;
        this.velocity = velocity;
        this.color = color;
    }
    public Rectangle(Vector2D position, Vector2D size, Color color) {
        this(position, size, new Vector2D(0, 0), color);
    }
    public Rectangle(int x, int y, int width, int height, int xVel, int yVel, Color color) {
        this(new Vector2D(x, y), new Vector2D(width, height), new Vector2D(xVel, yVel), color);
    }
    public Rectangle(int x, int y, int width, int height, Color color) {
        this(new Vector2D(x, y), new Vector2D(width, height), new Vector2D(0, 0), color);
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public boolean isFilled() {
        return filled;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    public void setGradient(GradientPaint gradient) {
        this.gradient = gradient;
    }
    public GradientPaint getGradient() {
        return gradient;
    }
    @Override
    public void draw(Graphics2D graphics, Vector2D offset, Vector2D frameSize, int frame) {
        Vector2D position = this.getPosition().translate(offset);
        graphics.setPaint(color);
        if(this.gradient != null) {
            GradientPaint gradient = new GradientPaint(new Point2D.Double(this.gradient.getPoint1().getX() + offset.getX(), this.gradient.getPoint1().getY() + offset.getY()), this.gradient.getColor1(), new Point2D.Double(this.gradient.getPoint2().getX() + offset.getX(), this.gradient.getPoint2().getY() + offset.getY()), this.gradient.getColor2());
            graphics.setPaint(gradient);
        }
        if(filled) {
            graphics.fill(new java.awt.Rectangle(position.getX(), position.getY(), getSize().getX(), getSize().getY()));
        } else {
            graphics.fill(new java.awt.Rectangle(position.getX(), position.getY(), getSize().getX(), getSize().getY()));
        }
    }
}

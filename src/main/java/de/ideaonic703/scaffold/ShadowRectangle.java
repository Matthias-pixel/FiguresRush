package de.ideaonic703.scaffold;

import de.ideaonic703.math.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ShadowRectangle extends Element {
    private Color color, shadow;
    private GradientPaint gradient;
    private boolean filled = true;
    private GradientPaint shadowGradient;
    public ShadowRectangle(Vector2D position, Vector2D size, Vector2D velocity, Color color, Color shadow) {
        this.position = position;
        this.size = size;
        this.velocity = velocity;
        this.color = color;
        this.shadow = shadow;
    }
    public ShadowRectangle(Vector2D position, Vector2D size, Color color, Color shadow) {
        this(position, size, new Vector2D(0, 0), color, shadow);
    }
    public ShadowRectangle(int x, int y, int width, int height, int xVel, int yVel, Color color, Color shadow) {
        this(new Vector2D(x, y), new Vector2D(width, height), new Vector2D(xVel, yVel), color, shadow);
    }
    public ShadowRectangle(int x, int y, int width, int height, Color color, Color shadow) {
        this(new Vector2D(x, y), new Vector2D(width, height), new Vector2D(0, 0), color, shadow);
    }
    public void setColor(Color color, Color shadow) {
        this.color = color;
        this.shadow = shadow;
    }
    public Color getColor() {
        return color;
    }
    public Color getShadow() {
        return shadow;
    }
    public void setGradient(GradientPaint gradient, GradientPaint shadowGradient) {
        this.gradient = gradient;
        this.shadowGradient = shadowGradient;
    }
    public GradientPaint getGradient() {
        return gradient;
    }
    public GradientPaint getShadowGradient() {
        return shadowGradient;
    }
    @Override
    public void draw(Graphics2D graphics, Vector2D offset, Vector2D frameSize, int frame) {
        Vector2D position = this.getPosition().translate(offset);
        graphics.setPaint(color);
        if(this.gradient != null) {
            GradientPaint gradient = new GradientPaint(new Point2D.Double(this.gradient.getPoint1().getX() + offset.getX(), this.gradient.getPoint1().getY() + offset.getY()), this.gradient.getColor1(), new Point2D.Double(this.gradient.getPoint2().getX() + offset.getX(), this.gradient.getPoint2().getY() + offset.getY()), this.gradient.getColor2());
            graphics.setPaint(gradient);
        }
        graphics.fillRect(position.getX(), position.getY(), getSize().getX(), getSize().getY());
        graphics.setPaint(shadow);
        if(this.shadowGradient != null) {
            GradientPaint gradient = new GradientPaint(new Point2D.Double(this.shadowGradient.getPoint1().getX() + offset.getX(), this.shadowGradient.getPoint1().getY() + offset.getY()), this.shadowGradient.getColor1(), new Point2D.Double(this.shadowGradient.getPoint2().getX() + offset.getX(), this.shadowGradient.getPoint2().getY() + offset.getY()), this.shadowGradient.getColor2());
            graphics.setPaint(gradient);
        }
        graphics.fillRect(position.getX()+getSize().getX()-5, position.getY()+5, 5, getSize().getY()-5);
        graphics.fillRect(position.getX()+5, position.getY()+getSize().getY()-5, getSize().getX()-5, 5);
    }
}

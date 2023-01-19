package de.ideaonic703.scaffold;

import de.ideaonic703.GeometryDash;
import de.ideaonic703.logic.RelativityDescriptor;
import de.ideaonic703.math.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public abstract class Element {
    protected Vector2D position, size, velocity, frameSize = GeometryDash.DEFAULT_SIZE;
    protected RelativityDescriptor[] relativityDescriptors = new RelativityDescriptor[0];
    public Vector2D getPosition() {
        Vector2D absolutePosition = position.copy();
        for(RelativityDescriptor relativityDescriptor : relativityDescriptors) {
            switch (relativityDescriptor) {
                case ALIGN_LEFT, ALIGN_TOP -> {}
                case ALIGN_RIGHT -> {
                    absolutePosition.add(frameSize.getX(), 0);
                }
                case ALIGN_BOTTOM -> {
                    absolutePosition.add(0, frameSize.getY());
                }
            }
        }
        return absolutePosition;
    }
    public Vector2D getSize() {
        Vector2D absoluteSize = size.copy();
        for(RelativityDescriptor relativityDescriptor : relativityDescriptors) {
            switch (relativityDescriptor) {
                case EXPAND_WIDTH -> {
                    absoluteSize.add(frameSize.getX(), 0);
                }
                case EXPAND_HEIGHT -> {
                    absoluteSize.add(0, frameSize.getY());
                }
            }
        }
        return absoluteSize;
    }
    public Vector2D getVelocity() {
        return velocity;
    }
    public int getX() {
        return getPosition().getX();
    }
    public int getY() {
        return getPosition().getY();
    }
    public int getWidth() {
        return getSize().getX();
    }
    public int getHeight() {
        return getSize().getY();
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
    public void setRelativity(RelativityDescriptor[] relativityDescriptors) {
        this.relativityDescriptors = relativityDescriptors;
    }
    public void update(int frame, Vector2D frameSize) {
        this.frameSize = frameSize;
        position.add(velocity);
    }
    public abstract void draw(Graphics2D graphics, Vector2D offset, Vector2D frameSize, int frame);
}

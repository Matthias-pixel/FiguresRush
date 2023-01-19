package de.ideaonic703.math;

import java.awt.*;

/**
 * Represents an aspect ratio in form of a fraction
 * @author Ideaonic703
 */
public final class AspectRatio {
    private final int width, height;

    /**
     * @author Ideaonic703
     * @param width Numerator of the fraction describing the aspect ratio
     * @param height Denominator of the fraction describing the aspect ratio
     */
    public AspectRatio(int width, int height) {
        this.width = width;
        this.height = height;
        if(height == 0) throw new IllegalArgumentException("The height is zero.");
        if(width == 0) throw new IllegalArgumentException("The width is zero.");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth(int height) {
        return height/this.height*this.width;
    }
    public int getHeight(int width) {
        return width/this.width*this.height;
    }
    public Vector2D fixedWidth(int width) {
        return new Vector2D(width, this.getHeight(width));
    }
    public Vector2D fixedHeight(int height) {
        return new Vector2D(this.getWidth(height), height);
    }
    public boolean matches(int width, int height) {
        return width/height == this.width/this.height;
    }
}

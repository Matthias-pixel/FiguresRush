package de.ideaonic703.logic;

import de.ideaonic703.GeometryDash;
import de.ideaonic703.math.Vector2D;

import java.awt.*;
import java.util.Vector;

public class GameLogic {
    private GeometryDash gd;
    private int width, height;
    private RectBody demoRect;
    public GameLogic(GeometryDash gd) {
        this.gd = gd;
        width = 0;
        height = 0;
        demoRect = new RectBody(0, 0, 20, 20, 3, 3);
    }
    public void tick(int frame, int width, int height) {
        if(frame % GeometryDash.FPS == 0) System.out.printf("Tick %d!%n", frame);
        this.width = width;
        this.height = height;
        updateDemoRect();
    }
    private void updateDemoRect() {
        demoRect.tick();
        if(demoRect.getX() <= 0) demoRect.reverseX();
        if(demoRect.getX() + demoRect.getWidth() >= width) demoRect.reverseX();
        if(demoRect.getY() <= 0) demoRect.reverseY();
        if(demoRect.getY() + demoRect.getHeight() >= height) demoRect.reverseY();
    }
    public RectBody getDemoRect() {
        return demoRect;
    }
}

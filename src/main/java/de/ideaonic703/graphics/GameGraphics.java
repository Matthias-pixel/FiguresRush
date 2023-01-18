package de.ideaonic703.graphics;

import de.ideaonic703.GeometryDash;
import de.ideaonic703.logic.GameLogic;
import de.ideaonic703.logic.RectBody;

import javax.swing.*;
import java.awt.*;

public class GameGraphics {
    private int width, height;
    private GeometryDash gd;
    private Graphics2D graphics;
    private int frame;
    public GameGraphics(GeometryDash gd) {
        this.gd = gd;
        width = 0;
        height = 0;
    }
    public void paint(Graphics2D graphics, int width, int height, int frame) {
        this.width = width;
        this.height = height;
        this.graphics = graphics;
        this.frame = frame;
        initGraphics();
        drawDemoRect();
    }
    private void initGraphics() {
        graphics.setBackground(new Color(0, 0, 0));
    }
    private void drawDemoRect() {
        RectBody dr = gd.getGameLogic().getDemoRect();
        graphics.setColor(new Color(200, 50, 50));
        graphics.fillRect(dr.getX(), dr.getY(), dr.getWidth(), dr.getHeight());
    }
}

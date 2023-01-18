package de.ideaonic703;

import de.ideaonic703.graphics.GameGraphics;
import de.ideaonic703.logic.GameLogic;
import de.ideaonic703.math.AspectRatio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class GeometryDash {
    private JFrame frame;
    private GamePanel gamePanel;
    private GameLogic gameLogic;
    private GameGraphics gameGraphics;
    public final static AspectRatio ASPECT_RATIO = new AspectRatio(16, 9);
    public final static int FPS = 60;
    public GeometryDash() {
        gameLogic = new GameLogic(this);
        gameGraphics = new GameGraphics(this);
        frame = new JFrame();
        frame.setSize(ASPECT_RATIO.fixedWidth(1000));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gamePanel = new GamePanel(this);
        frame.add(gamePanel);
        frame.addComponentListener(new ComponentListener() {
            private int oldWidth, oldHeight;
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension bounds = e.getComponent().getSize();
                int setWidth = (int) bounds.getWidth();
                int setHeight = (int) bounds.getHeight();
                int newWidth = setWidth;
                int newHeight = setHeight;
                boolean widthChanged = setWidth != oldWidth;
                boolean heightChanged = setHeight != oldHeight;
                if(widthChanged && heightChanged) {
                    int widthDifference = Math.abs(setWidth-oldWidth);
                    int heightDifference = Math.abs(setHeight-oldHeight);
                    if(widthDifference > heightDifference) {
                        newHeight = ASPECT_RATIO.getHeight(setWidth);
                    } else {
                        newWidth = ASPECT_RATIO.getWidth(setHeight);
                    }
                } else if (widthChanged) {
                    newHeight = ASPECT_RATIO.getHeight(setWidth);
                } else if (heightChanged) {
                    newWidth = ASPECT_RATIO.getWidth(setHeight);
                }
                e.getComponent().setSize(newWidth, newHeight);
                oldWidth = newWidth;
                oldHeight = newHeight;
            }

            @Override
            public void componentMoved(ComponentEvent e) {}
            @Override
            public void componentShown(ComponentEvent e) {}
            @Override
            public void componentHidden(ComponentEvent e) {}
        });
        frame.setVisible(true);
        gamePanel.start();
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }
    public GameGraphics getGameGraphics() {
        return gameGraphics;
    }
}
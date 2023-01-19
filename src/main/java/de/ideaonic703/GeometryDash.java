package de.ideaonic703;

import de.ideaonic703.logic.GameLogic;
import de.ideaonic703.logic.RelativityDescriptor;
import de.ideaonic703.math.AspectRatio;
import de.ideaonic703.math.Vector2D;
import de.ideaonic703.scaffold.Element;
import de.ideaonic703.scaffold.Scaffold;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class GeometryDash {
    private JFrame frame;
    private GamePanel gamePanel;
    private GameLogic gameLogic;
    private Scaffold rootScaffold;
    public final static AspectRatio ASPECT_RATIO = new AspectRatio(16, 10);
    public final static Vector2D DEFAULT_SIZE = ASPECT_RATIO.fixedWidth(1000);
    public final static int FPS = 60;
    public GeometryDash() {
        rootScaffold = new Scaffold(new Vector2D(0, 0), ASPECT_RATIO.fixedWidth(1000));
        rootScaffold.setRelativity(new RelativityDescriptor[]{RelativityDescriptor.EXPAND_WIDTH, RelativityDescriptor.EXPAND_HEIGHT});
        gameLogic = new GameLogic(this);
        frame = new JFrame();
        frame.setSize(DEFAULT_SIZE.toDimension());
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

    public Scaffold getRootScaffold() {
        return rootScaffold;
    }

    public Vector2D getSize() {
        return frame != null ? Vector2D.fromDimension(frame.getSize()) : DEFAULT_SIZE;
    }
}
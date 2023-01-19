package de.ideaonic703;

import de.ideaonic703.math.Vector2D;
import de.ideaonic703.scaffold.Element;
import de.ideaonic703.scaffold.Scaffold;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class GamePanel extends JPanel implements ActionListener {
    private GeometryDash gd;
    private Timer timer;
    private int frame;
    public GamePanel(GeometryDash gd) {
        this.setFocusable(true);
        this.gd = gd;
        frame = 0;
        timer = new Timer(1000/GeometryDash.FPS, this);
    }
    public void start() {
        timer.start();
    }
    @Serial
    private static final long serialVersionUID = 1L;
    @Override
    public Dimension getMinimumSize() {
        return GeometryDash.ASPECT_RATIO.fixedWidth(400).toDimension();
    }
    @Override
    public Dimension getPreferredSize() {
        return GeometryDash.ASPECT_RATIO.fixedWidth(1000).toDimension();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gd.getRootScaffold().draw(g2, Vector2D.ZERO, Vector2D.fromDimension(getSize()), frame);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        gd.getGameLogic().tick(frame, Vector2D.fromDimension(getSize()));
        repaint();
        frame++;
    }
}

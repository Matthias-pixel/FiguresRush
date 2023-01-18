package de.ideaonic703;

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
        return GeometryDash.ASPECT_RATIO.fixedWidth(400);
    }
    @Override
    public Dimension getPreferredSize() {
        return GeometryDash.ASPECT_RATIO.fixedWidth(1000);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dim = getSize();
        int width = (int) dim.getWidth();
        int height = (int) dim.getHeight();
        gd.getGameGraphics().paint((Graphics2D) g, width, height, frame);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Dimension dim = getSize();
        int width = (int) dim.getWidth();
        int height = (int) dim.getHeight();
        gd.getGameLogic().tick(frame, width, height);
        repaint();
        frame++;
    }
}

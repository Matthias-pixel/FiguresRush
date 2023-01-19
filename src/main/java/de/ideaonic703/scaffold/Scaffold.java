package de.ideaonic703.scaffold;

import de.ideaonic703.GeometryDash;
import de.ideaonic703.logic.RelativityDescriptor;
import de.ideaonic703.math.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Scaffold extends Element {
    private ArrayList<Element> elements;
    public Scaffold(Vector2D position, Vector2D size, Vector2D velocity) {
        this.position = position;
        this.size = size;
        this.velocity = velocity;
        elements = new ArrayList<>();
    }
    public Scaffold(Vector2D position, Vector2D size) {
        this(position, size, new Vector2D(0, 0));
    }
    public Scaffold(int x, int y, int width, int height, int xVel, int yVel) {
        this(new Vector2D(x, y), new Vector2D(width, height), new Vector2D(xVel, yVel));
    }
    public Scaffold(int x, int y, int width, int height) {
        this(new Vector2D(x, y), new Vector2D(width, height), new Vector2D(0, 0));
    }
    public Element add(Element element) {
        this.elements.add(element);
        return element;
    }
    public Scaffold add(Scaffold scaffold) {
        this.elements.add(scaffold);
        return scaffold;
    }
    public boolean remove(Element element) {
        return this.elements.remove(element);
    }
    @Override
    public void update(int frame, Vector2D frameSize) {
        super.update(frame, frameSize);
        for(Element e : this.elements) {
            e.update(frame, getSize().limit(frameSize));
        }
    }
    @Override
    public void draw(Graphics2D graphics, Vector2D offset, Vector2D frameSize, int frame) {
        for (Element e : this.elements) {
            e.draw(graphics, getPosition(), this.getSize().limit(frameSize), frame);
        }
    }
}

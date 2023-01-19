package de.ideaonic703.logic;

import de.ideaonic703.GeometryDash;
import de.ideaonic703.components.Background;
import de.ideaonic703.math.Vector2D;
import de.ideaonic703.scaffold.Element;
import de.ideaonic703.scaffold.Rectangle;
import de.ideaonic703.scaffold.Scaffold;

import java.awt.*;

public class GameLogic {
    private GeometryDash gd;
    private Vector2D size;
    private Background background;
    public final static int SCROLL_SPEED = 8;
    public static final float BACKGROUND_SCROLL_SPEED_MODIFIER = 0.12f;
    public final static float HUE_CHANGE_FACTOR = 0.2f;
    public GameLogic(GeometryDash gd) {
        this.gd = gd;
        size = gd.getSize();
        background = (Background)gd.getRootScaffold().add(new Background(gd));
    }
    public void tick(int frame, Vector2D size) {
        if(frame % GeometryDash.FPS == 0) System.out.printf("Tick %d!%n", frame);
        this.size = size;
        gd.getRootScaffold().update(frame, size);
    }
}

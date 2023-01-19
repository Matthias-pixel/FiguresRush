package de.ideaonic703.components;

import de.ideaonic703.GeometryDash;
import de.ideaonic703.logic.BackgroundTileGenerator;
import de.ideaonic703.logic.GameLogic;
import de.ideaonic703.logic.RelativityDescriptor;
import de.ideaonic703.math.Vector2D;
import de.ideaonic703.scaffold.Element;
import de.ideaonic703.scaffold.Rectangle;
import de.ideaonic703.scaffold.Scaffold;
import de.ideaonic703.scaffold.ShadowRectangle;

import java.awt.*;

public class Background extends Scaffold {
    private Floor floor;
    private GeometryDash gd;
    private Rectangle background;
    private ShadowRectangle[] squares = new ShadowRectangle[0];
    private float hue = 0;
    public final static float BACKGROUND_SATURATION = 0.86f;
    public Background(GeometryDash gd) {
        super(0, 0, 0, 0);
        this.gd = gd;
        this.relativityDescriptors = new RelativityDescriptor[]{RelativityDescriptor.EXPAND_WIDTH, RelativityDescriptor.EXPAND_HEIGHT};
        this.background = (Rectangle) add(new Rectangle(0, 0, 0, -Floor.FLOOR_HEIGHT, Color.gray));
        this.background.setRelativity(new RelativityDescriptor[]{RelativityDescriptor.EXPAND_WIDTH, RelativityDescriptor.EXPAND_HEIGHT});
        squares = BackgroundTileGenerator.genSquares(this);
        this.floor = (Floor)add(new Floor());
        updateHue();
    }
    @Override
    public void update(int frame, Vector2D frameSize) {
        super.update(frame, frameSize);
        hue = ((frame*GameLogic.HUE_CHANGE_FACTOR+5)%360)/360f;
        updateHue();
        GradientPaint squareGradient = new GradientPaint(0, 0, Color.getHSBColor(hue, Background.BACKGROUND_SATURATION, 0.56f), 0, getHeight(), Color.getHSBColor(hue, Background.BACKGROUND_SATURATION, 0.84f));
        GradientPaint squareShadowGradient = new GradientPaint(0, 0, Color.getHSBColor(hue, Background.BACKGROUND_SATURATION, 0.44f), 0, getHeight(), Color.getHSBColor(hue, Background.BACKGROUND_SATURATION, 0.72f));
        for(ShadowRectangle square : squares) {
            square.setGradient(squareGradient, squareShadowGradient);
        }
    }
    public void updateHue() {
        this.background.setGradient(new GradientPaint(0, 0, Color.getHSBColor(hue, BACKGROUND_SATURATION, 0.64f), 0, getHeight(), Color.getHSBColor(hue, BACKGROUND_SATURATION, 1.0f)));
    }

    private class Floor extends Scaffold {
        private Rectangle background;
        private ShadowRectangle[] squares;
        private Rectangle topSeperatorLeft, topSeperatorRight;
        private int oldWidth = 0;
        private float hue = 0.7f;
        public final static int FLOOR_HEIGHT = 140;
        private final static int SQUARE_PADDING = 10;
        private final static int SQUARE_SIZE = FLOOR_HEIGHT;
        private final float SATURATION = 0.8f;
        public Floor() {
            super(0, -FLOOR_HEIGHT, 0, FLOOR_HEIGHT);
            this.setRelativity(new RelativityDescriptor[]{RelativityDescriptor.EXPAND_WIDTH, RelativityDescriptor.ALIGN_BOTTOM});
            this.background = (Rectangle)add(new Rectangle(0, 0, 0, FLOOR_HEIGHT, Color.gray));
            this.background.setRelativity(new RelativityDescriptor[]{RelativityDescriptor.EXPAND_WIDTH});
            this.topSeperatorLeft = (Rectangle)add(new Rectangle(0, 0, 500, 2, Color.gray));
            this.topSeperatorLeft.setGradient(new GradientPaint(0, 0, new Color(255, 255, 255, 0), 500, 0, new Color(255, 255, 255, 255)));
            this.topSeperatorRight = (Rectangle)add(new Rectangle(500, 0, 500, 2, Color.gray));
            this.topSeperatorRight.setGradient(new GradientPaint(500, 0, new Color(255, 255, 255, 255), 1000, 0, new Color(255, 255, 255, 0)));
            int squareCount = getWidth()/(SQUARE_SIZE+SQUARE_PADDING*2)+1;
            onResize();
        }
        public void onResize() {
            int squareCount = getWidth()/(SQUARE_SIZE+SQUARE_PADDING*2)+2;
            squares = new ShadowRectangle[squareCount];
            for(int i = 0; i < squareCount; i++) {
                squares[i] = (ShadowRectangle)add(new ShadowRectangle(SQUARE_PADDING+i*(SQUARE_SIZE+SQUARE_PADDING*2), SQUARE_PADDING, SQUARE_SIZE, SQUARE_SIZE, Color.gray, Color.gray) {
                    private Vector2D positionOffset = Vector2D.ZERO;
                    @Override
                    public void update(int frame, Vector2D frameSize) {
                        super.update(frame, frameSize);
                        int scaledFrame = frame* GameLogic.SCROLL_SPEED;
                        scaledFrame %= SQUARE_SIZE+SQUARE_PADDING*2;
                        positionOffset = new Vector2D(-scaledFrame, 0);
                    }

                    @Override
                    public Vector2D getPosition() {
                        return super.getPosition().translate(positionOffset);
                    }
                });
            }
            this.updateHue();
        }
        @Override
        public void update(int frame, Vector2D frameSize) {
            super.update(frame, frameSize);
            if(frameSize.getX() != oldWidth) {
                oldWidth = frameSize.getX();
                this.onResize();
            }
            hue = ((frame*GameLogic.HUE_CHANGE_FACTOR)%360)/360f;
            updateHue();
        }
        public void updateHue() {
            this.background.setGradient(new GradientPaint(getWidth()/2, 0, Color.getHSBColor(hue, SATURATION, 0.75f), getWidth()/2, getHeight()-1, Color.getHSBColor(hue, SATURATION, 0.4f)));
            for(ShadowRectangle square : squares) {
                square.setGradient(
                        new GradientPaint(square.getWidth()/2, 0, Color.getHSBColor(hue, SATURATION, 0.63f), square.getWidth()/2, square.getHeight()-1, Color.getHSBColor(hue, SATURATION, 0.34f)),
                        new GradientPaint(square.getWidth()/2, 0, Color.getHSBColor(hue, SATURATION, 0.54f), square.getWidth()/2, square.getHeight()-1, Color.getHSBColor(hue, SATURATION, 0.29f)));
            }
        }
    }
}
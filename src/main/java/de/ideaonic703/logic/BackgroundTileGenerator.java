package de.ideaonic703.logic;

import de.ideaonic703.GeometryDash;
import de.ideaonic703.components.Background;
import de.ideaonic703.math.Vector2D;
import de.ideaonic703.scaffold.Rectangle;
import de.ideaonic703.scaffold.Scaffold;
import de.ideaonic703.scaffold.ShadowRectangle;

import java.awt.*;
import java.util.ArrayList;

public class BackgroundTileGenerator {
    private static final int TILE_BODY = 64;
    private static final int TILE_PADDING = 16;
    private static final int TILE_SIZE = TILE_BODY+TILE_PADDING;
    private static final int SQUARE_COUNT = (GeometryDash.ASPECT_RATIO.fixedHeight(1800).getX())/TILE_SIZE+5;
    public static ShadowRectangle[] genSquares(Background background) {
        Vector2D size = GeometryDash.ASPECT_RATIO.fixedHeight(1800);
        size.divide(TILE_SIZE);
        int[][] grid = new int[size.getX()+5][size.getY()];
        for(int x = 0; x < grid.length; x++) {
            for(int y = 0; y < grid[0].length; y++) {
                grid[x][y] = -1;
            }
        }
        ArrayList<SimpleSquare> list = new ArrayList<>();
        for(int i = 0; i < 1*6; i++) {
            SimpleSquare.addSameRandomIfPossible(grid, list, 4, 6);
        }
        for(int i = 0; i < 8*6; i++) {
            SimpleSquare.addSameRandomIfPossible(grid, list, 3, 6);
        }
        SimpleSquare.fillAllFreeSpace(grid, list, 2);
        SimpleSquare.fillAllFreeSpace(grid, list, 1);
        ShadowRectangle[] rectangles = new ShadowRectangle[list.size()];
        for(int i = 0; i < rectangles.length; i++) {
            rectangles[i] = (ShadowRectangle) background.add(list.get(i).toShadowRectangle());
        }
        return rectangles;
    }

    private record SimpleSquare(int x, int y, int size) {
        public static boolean addSameRandomIfPossible(int[][] grid, ArrayList<SimpleSquare> list, int size, int tries) {
            for (; tries >= 0; tries--) {
                if (addRandomIfPossible(grid, list, size)) return true;
            }
            return false;
        }

        public static boolean addRandomIfPossible(int[][] grid, ArrayList<SimpleSquare> list, int size) {
            if (grid.length == 0) return false;
            int x = (int) (Math.random() * grid.length - 0.1);
            int y = (int) (Math.random() * grid[0].length - 0.1);
            SimpleSquare square = new SimpleSquare(x, y, size);
            return square.addIfPossible(grid, list);
        }

        public static boolean addFirstFreeSpaceIfPossible(int[][] grid, ArrayList<SimpleSquare> list, int size) {
            if (grid.length == 0) return false;
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    if (new SimpleSquare(x, y, size).addIfPossible(grid, list)) return true;
                }
            }
            return false;
        }

        public static void fillAllFreeSpace(int[][] grid, ArrayList<SimpleSquare> list, int size) {
            if (grid.length == 0) return;
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    new SimpleSquare(x, y, size).addIfPossible(grid, list);
                }
            }
        }

        public boolean isSpaceOccupied(int[][] grid) {
            if(x+size > grid.length) return true;
            if(y+size > grid[0].length) return true;
            boolean occupied = false;
            outer:
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    if (grid[this.x + x][this.y + y] > -1) {
                        occupied = true;
                        break outer;
                    }
                }
            }
            return occupied;
        }

        public boolean addIfPossible(int[][] grid, ArrayList<SimpleSquare> list) {
            if (isSpaceOccupied(grid)) return false;
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    grid[this.x + x][this.y + y] = list.size();
                }
            }
            list.add(this);
            list.add(new SimpleSquare(x+grid.length, y, size));
            return true;
        }

        public ShadowRectangle toShadowRectangle() {
            return new ShadowRectangle(this.x * TILE_SIZE - 200, this.y * TILE_SIZE -270, TILE_BODY+(TILE_SIZE*(size-1)), TILE_BODY+(TILE_SIZE*(size-1)), Color.gray, Color.gray) {
                private Vector2D positionOffset = Vector2D.ZERO;
                @Override
                public void update(int frame, Vector2D frameSize) {
                    super.update(frame, frameSize);
                    int scaledFrame = (int) (frame*GameLogic.SCROLL_SPEED*GameLogic.BACKGROUND_SCROLL_SPEED_MODIFIER);
                    scaledFrame %= SQUARE_COUNT*TILE_SIZE;
                    positionOffset = new Vector2D(-scaledFrame, 0);
                }
                @Override
                public Vector2D getPosition() {
                    return super.getPosition().translate(positionOffset);
                }
            };
        }
    }
}

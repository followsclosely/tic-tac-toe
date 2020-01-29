package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.impl.MutableBoard;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

public class TicTacToePanelDynamic extends TicTacToePanel {

    protected int cellHeight = 100;
    protected int cellWidth = 100;
    private final ComponentAdapter componentAdapter = new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
            int width = getWidth();
            int height = getHeight();

            cellHeight = height / board.getSize();
            cellWidth = width / board.getSize();
        }
    };
    private boolean initialized = false;

    public TicTacToePanelDynamic(MutableBoard board) {
        super(board);
        this.addComponentListener(componentAdapter);
    }

    @Override
    public void paint(Graphics g) {

        if (!initialized) {
            initialized = true;
            componentAdapter.componentResized(null);
        }

        int width = getWidth();
        int height = getHeight();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.GRAY);
        for (int i = 1, h = board.getSize(); i < h; i++) {
            g.fillRoundRect(10, cellHeight * i - 5, width - 20, 10, 5, 5);
        }

        for (int i = 1, w = board.getSize(); i < w; i++) {
            g.fillRoundRect(cellWidth * i - 5, 10, 10, height - 20, 5, 5);
        }

        //Draw all the already played pieces.
        for (int x = 0, w = board.getSize(); x < w; x++) {
            for (int y = 0, h = board.getSize(); y < h; y++) {
                Piece piece = board.getPiece(x, y);
                if (piece != null) {
                    drawShape(x, y, g, COLORS[piece.ordinal()], piece);
                }
            }
        }

        //Draw an X or O under the current mouse position.
        if (hoverX >= 0 && hoverY >= 0) {
            Piece piece = board.getPiece(hoverX, hoverY);
            if (piece == null) {
                drawShape(hoverX, hoverY, g, COLORS[2], Piece.X);
            }
        }
    }

    private void drawShape(int x, int y, Graphics g, Color color, Piece shape) {

        g.setColor(color);

        switch (shape) {
            case O -> {
                g.fillRoundRect((x * cellWidth + 15), (y * cellHeight + 15), cellWidth - 30, cellHeight - 30, cellWidth, cellHeight);
                g.setColor(getBackground());
                g.fillRoundRect((x * cellWidth + 25), (y * cellHeight + 25), cellWidth - 50, cellHeight - 50, cellWidth - 20, cellHeight - 20);
            }
            case X -> {
                Polygon polygon = new Polygon();
                polygon.addPoint((x * cellWidth + 11), (y * cellHeight + 17));
                polygon.addPoint((x * cellWidth + 17), (y * cellHeight + 11));
                polygon.addPoint((x * cellWidth + (cellWidth - 15)), (y * cellHeight + (cellHeight - 15)));
                polygon.addPoint((x * cellWidth + (cellWidth - 15)), (y * cellHeight + (cellHeight - 10)));
                g.fillPolygon(polygon);
                polygon = new Polygon();
                polygon.addPoint(((x + 1) * cellWidth - 15), (y * cellHeight + 18));
                polygon.addPoint(((x + 1) * cellWidth - 18), (y * cellHeight + 11));
                polygon.addPoint((x * cellWidth + 11), ((y + 1) * cellHeight - 15));
                polygon.addPoint((x * cellWidth + 17), ((y + 1) * cellHeight - 10));
                g.fillPolygon(polygon);
            }
        }
    }

    public void setHoverLocation(MouseEvent event) {
        setHoverLocation(Math.min(event.getX() / cellWidth, board.getSize() - 1), Math.min(event.getY() / cellHeight, board.getSize() - 1));
    }

    public boolean setPiece(MouseEvent event, Piece shape) {
        return setPiece(Math.min(event.getX() / cellWidth, board.getSize() - 1), Math.min(event.getY() / cellHeight, board.getSize() - 1), shape);
    }
}
package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.impl.MutableBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToePanel extends JPanel {

    protected final static Dimension DEFAULT_MINIMUM_SIZE = new Dimension(300, 300);
    protected final static Color[] COLORS = {Color.LIGHT_GRAY, Color.RED, Color.BLUE};
    protected final MutableBoard board;
    protected int hoverX = -1;
    protected int hoverY = -1;
    protected int currentShape = 1;

    public TicTacToePanel(MutableBoard board) {
        this.board = board;
    }

    @Override
    public Dimension getPreferredSize() {
        return DEFAULT_MINIMUM_SIZE;
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 300, 300);

        g.setColor(Color.GRAY);

        //Draw the horizontal lines on the board.
        g.fillRoundRect(10, 95, 280, 10, 5, 5);
        g.fillRoundRect(10, 199, 280, 10, 5, 5);

        //Draw the vertical lines on the board.
        g.fillRoundRect(95, 10, 10, 280, 5, 5);
        g.fillRoundRect(195, 10, 10, 280, 5, 5);

        //Draw all the already played pieces.
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int piece = board.getPiece(x, y);
                if (piece > 0) {
                    drawShape(x, y, g, COLORS[piece], piece);
                }
            }
        }

        //Draw an X or O under the current mouse position.
        if (hoverX >= 0 && hoverY >= 0) {
            int piece = board.getPiece(hoverX, hoverY);
            if (piece == 0) {
                drawShape(hoverX, hoverY, g, COLORS[piece], currentShape);
            }
        }
    }

    private void drawShape(int x, int y, Graphics g, Color color, int shape) {
        g.setColor(color);

        switch (shape) {
            case 1 -> { //Draw a O
                g.fillRoundRect((x * 100 + 15), (y * 100 + 15), 70, 70, 70, 70);
                g.setColor(getBackground());
                g.fillRoundRect((x * 100 + 25), (y * 100 + 25), 50, 50, 50, 50);
            }
            case 2 -> { //Draw an X
                Polygon polygon = new Polygon();
                polygon.addPoint((x * 100 + 11), (y * 100 + 17));
                polygon.addPoint((x * 100 + 17), (y * 100 + 11));
                polygon.addPoint((x * 100 + 85), (y * 100 + 85));
                polygon.addPoint((x * 100 + 82), (y * 100 + 90));
                g.fillPolygon(polygon);
                polygon = new Polygon();
                polygon.addPoint((x * 100 + 85), (y * 100 + 17));
                polygon.addPoint((x * 100 + 82), (y * 100 + 11));
                polygon.addPoint((x * 100 + 11), (y * 100 + 85));
                polygon.addPoint((x * 100 + 17), (y * 100 + 90));
                g.fillPolygon(polygon);
            }
        }
    }

    public void setHoverLocation(MouseEvent event) {
        setHoverLocation(Math.min(event.getX() / 100, board.getWidth() - 1), Math.min(event.getY() / 100, board.getHeight() - 1));
    }

    protected void setHoverLocation(int x, int y) {
        if (this.hoverX != x || this.hoverY != y) {
            this.hoverX = x;
            this.hoverY = y;
            SwingUtilities.invokeLater(this::repaint);
        }
    }

    public boolean setPiece(MouseEvent event, int shape) {
        return setPiece(Math.min(event.getX() / 100, board.getWidth() - 1), Math.min(event.getY() / 100, board.getHeight() - 1), shape);
    }

    protected boolean setPiece(int x, int y, int shape) {
        boolean played;

        if (played = (board.getPiece(x, y) == 0)) {
            board.playPiece(x, y, currentShape);
            //this.currentShape = currentShape == 1 ? 2 : 1;
            SwingUtilities.invokeLater(this::repaint);
        }

        return played;
    }
}
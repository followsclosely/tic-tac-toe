package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;

/**
 * Holds the state of the game, the model.
 */
public class MutableBoard extends AbstractBoard {

    private Coordinate lastMove;
    private int movesLeft = 0;

    public MutableBoard(Board board) {
        super(board);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (state[x][y] == 0) {
                    movesLeft++;
                }
            }
        }
    }

    public MutableBoard(int width, int height) {
        super(width, height);
    }

    public boolean playPiece(int x, int y, int piece) {
        boolean canPlay = (state[x][y] == 0);

        if (canPlay) {
            movesLeft--;
            state[x][y] = piece;
            lastMove = new Coordinate(x, y);
        }

        return canPlay;
    }

    public boolean undo(int x, int y) {
        boolean canUndo = (state[x][y] != 0);
        if (canUndo) {
            movesLeft++;
            state[x][y] = 0;
        }
        return canUndo;
    }

    public void reset() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                state[x][y] = 0;
            }
        }
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public Coordinate getLastMove() {
        return lastMove;
    }
}
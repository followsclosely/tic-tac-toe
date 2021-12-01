package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the state of the game, the model.
 */
public class MutableBoard extends AbstractBoard {

    private final List<Coordinate> moves = new ArrayList<>();
    //private Coordinate lastMove;
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
            moves.add(new Coordinate(x, y));
        }

        return canPlay;
    }

    public boolean undo() {
        if (moves.size() > 0) {
            Coordinate lastMove = moves.remove(moves.size() - 1);
            movesLeft++;
            state[lastMove.getX()][lastMove.getY()] = 0;
            return true;
        } else return false;
    }

    public void reset() {
        moves.clear();
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
        return moves.size() > 0 ? moves.get(moves.size() - 1) : null;
    }
}
package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the state of the game, the model.
 */
public class MutableBoard extends AbstractBoard {

    private final List<Coordinate> moves = new ArrayList<>();
    private int movesLeft = 0;

    public MutableBoard() {
        super(3);
    }

    public MutableBoard(int size) {
        super(size);
    }

    public MutableBoard(Board board) {
        super(board);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (state[x][y] == null) {
                    movesLeft++;
                }
            }
        }
    }


    public boolean playPiece(int x, int y, Piece piece) {
        boolean canPlay = (state[x][y] == null);

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
            state[lastMove.getX()][lastMove.getY()] = null;
            return true;
        } else return false;
    }

    public void reset() {
        moves.clear();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                state[x][y] = null;
            }
        }
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public Coordinate getLastMove() {
        return moves.size() > 0 ? moves.get(moves.size() - 1) : null;
    }

    public List<Coordinate> getMoves() {
        return moves;
    }
}
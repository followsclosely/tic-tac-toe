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

    private final List<Turn> moves = new ArrayList<>();

    public MutableBoard() {
        super(3);
    }

    public MutableBoard(int size) {
        super(size);
    }

    public MutableBoard(Board board) {
        super(board);
    }

    public boolean playPiece(Coordinate c, Piece piece) {
        return playPiece(c.getX(), c.getY(), piece);
    }

    public boolean playPiece(int x, int y, Piece piece) {
        boolean canPlay = (state[x][y] == null);

        if (canPlay) {
            movesLeft--;
            state[x][y] = piece;
            moves.add(new Turn(piece, new Coordinate(x, y)));
        }

        return canPlay;
    }

    public boolean undo() {
        if (moves.size() > 0) {
            Turn lastMove = moves.remove(moves.size() - 1);
            movesLeft++;
            state[lastMove.getCoordinate().getX()][lastMove.getCoordinate().getY()] = null;
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

    public Turn getLastMove() {
        return moves.size() > 0 ? moves.get(moves.size() - 1) : null;
    }

    public List<Turn> getMoves() {
        return moves;
    }
}
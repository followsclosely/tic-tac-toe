package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.DummyAI;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.impl.TicTacToeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StinkAI extends AbstractAI {
    private Random random = new Random();

    public StinkAI(Piece piece) {
        super(piece);
    }

    @Override
    public Coordinate yourTurn(Board b) {

        MutableBoard board = new MutableBoard(b);

        int size = board.getSize();

        //Try and play in the center...
        Coordinate center = new Coordinate(size / 2, size / 2);
        if (board.getPiece(center.getX(), center.getY()) == null) {
            return center;
        }

        //If you can win, then win!
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (board.playPiece(x, y, getShape())) {
                    Coordinate coordinate = new Coordinate(x, y);
                    TicTacToeUtils.TurnDetails turnDetails = TicTacToeUtils.getTurnDetails(board, coordinate);
                    board.undo();

                    if (turnDetails.wonGame()) {
                        return coordinate;
                    }
                }
            }
        }

        //If your opponent can win, block!
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (board.playPiece(x, y, opponent)) {
                    Coordinate coordinate = new Coordinate(x, y);
                    TicTacToeUtils.TurnDetails turnDetails = TicTacToeUtils.getTurnDetails(board, coordinate);
                    board.undo();

                    if (turnDetails.wonGame()) {
                        return coordinate;
                    }
                }
            }
        }

        List<Coordinate> moves = new ArrayList<>();
        //Get all the valid moves...
        for (int x = 0; x < size; x += 1) {
            for (int y = 0; y < size; y += 1) {
                if (board.getPiece(x, y) == null) {
                    moves.add(new Coordinate(x, y));
                }
            }
        }
        if (moves.size() > 1) {
            int maxNumberAdjacent = 0;
            Coordinate bestMove = null;
            for (Coordinate corner : moves) {
                int count = 0;
                //Get the number of opponent pieces next to this.
                for (int dx = -1; dx <= 1; dx += 1) {
                    for (int dy = -1; dy <= 1; dy += 1) {
                        int x = corner.getX() + dx;
                        int y = corner.getY() + dy;
                        if (x >= 0 && x < board.getSize() && y >= 0 && y < board.getSize() && board.getPiece(x, y) == opponent) {
                            count++;
                        }
                    }
                }

                if (maxNumberAdjacent < count) {
                    bestMove = corner;
                    maxNumberAdjacent = count;
                }
            }

            if (bestMove != null) {
                return bestMove;
            }
        }

        //Return a random spot.
        return new DummyAI(shape).yourTurn(board);
    }
}
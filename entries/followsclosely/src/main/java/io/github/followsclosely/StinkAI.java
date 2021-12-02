package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.ai.DummyAI;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.impl.TicTacToeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StinkAI extends DummyAI {
    private Random random = new Random();

    public StinkAI(int shape) {
        super(shape);
    }

    @Override
    public Coordinate yourTurn(Board b) {

        MutableBoard board = new MutableBoard(b);

        int width = board.getWidth();
        int height = board.getHeight();

        //Try and play in the center...
        Coordinate center = new Coordinate(width / 2, height / 2);
        if (board.getPiece(center.getX(), center.getY()) == 0) {
            return center;
        }

        //If you can win, then win!
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board.playPiece(x, y, getOpponent())) {
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
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                if (board.getPiece(x, y) == 0) {
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
                        if (x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight() && board.getPiece(x, y) == getOpponent()) {
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
        return super.yourTurn(board);
    }
}
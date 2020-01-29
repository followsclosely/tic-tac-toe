package io.github.followsclosely.ttt.stink;

import io.github.followsclosely.ttt.BoardEvaluator;
import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.AbstractAI;
import io.github.followsclosely.ttt.ai.DummyAI;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.impl.TicTacToeUtils;

public class StinkAI extends AbstractAI {

    private BoardEvaluator evaluator = null;

    @Override
    public void initialize(Piece you, Piece opponent) {
        super.initialize(you, opponent);
        this.evaluator = new BoardEvaluator(Piece.X, Piece.O);
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

        //Look for a move that give you the highest score using the BoardEvaluator
        int maxScore = Integer.MIN_VALUE;
        Coordinate bestSpot = null;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (board.playPiece(x, y, opponent)) {
                    int score = evaluator.score(b);
                    if (score > maxScore) {
                        maxScore = score;
                        bestSpot = new Coordinate(x, y);
                    }
                    board.undo();
                }
            }
        }
        if (bestSpot != null) {
            return bestSpot;
        }

        //Return a random spot.
        return new DummyAI().yourTurn(board);
    }
}
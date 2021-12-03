package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.AbstractAI;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.impl.TicTacToeUtils;

import java.util.Collection;

public class MinMaxWithPruningAI extends AbstractAI {

    private BoardEvaluator evaluator = new BoardEvaluator();

    public MinMaxWithPruningAI(Piece shape) {
        super(shape);
    }

    @Override
    public Coordinate yourTurn(Board b) {

        //Try and play in the center...
        Coordinate center = new Coordinate(b.getSize() / 2, b.getSize() / 2);
        if (b.getPiece(center.getX(), center.getY()) == null) {
            return center;
        }

        return minimax(new MutableBoard(b), 2, Piece.X, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private MinMaxCoordinate minimax(MutableBoard b, int depth, Piece player, int alpha, int beta) {

        Collection<Coordinate> nextMoves = TicTacToeUtils.getEmptySquares(b);

        if (nextMoves.isEmpty() || depth == 0) {
            //bestScore = evaluator.score(b);
            return new MinMaxCoordinate(evaluator.score(b));
        } else {
            MinMaxCoordinate best = new MinMaxCoordinate((player == this.shape) ? Integer.MIN_VALUE : Integer.MAX_VALUE);
            for (Coordinate move : nextMoves) {
                if (b.playPiece(move, player)) {
                    if (player == this.shape) {
                        int currentScore = minimax(b, depth - 1, opponent, alpha, beta).getValue();
                        if (currentScore > alpha) {
                            alpha = currentScore;
                            best = new MinMaxCoordinate(move, currentScore);
                        }
                    } else {
                        int currentScore = minimax(b, depth - 1, this.shape, alpha, beta).getValue();
                        if (currentScore < beta) {
                            beta = currentScore;
                            best = new MinMaxCoordinate(move, currentScore);
                        }
                    }
                    b.undo();

                    if (alpha >= beta){
                        return new MinMaxCoordinate(best, (player == this.shape) ? alpha : beta);
                    }
                }
            }

            return best;
        }
    }

    class MinMaxCoordinate extends Coordinate {
        private final int value;

        public MinMaxCoordinate(int value) {
            super(-1, -1);
            this.value = value;
        }

        public MinMaxCoordinate(Coordinate parent, int value) {
            super(parent.getX(), parent.getY());
            this.value = value;
        }

        public MinMaxCoordinate(int x, int y, int value) {
            super(x, y);
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
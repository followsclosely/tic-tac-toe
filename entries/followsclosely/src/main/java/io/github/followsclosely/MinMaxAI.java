package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.AbstractAI;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.impl.TicTacToeUtils;

import java.util.Collection;

public class MinMaxAI extends AbstractAI {

    private BoardEvaluator evaluator = new BoardEvaluator();

    public MinMaxAI(Piece shape) {
        super(shape);
    }

    @Override
    public Coordinate yourTurn(Board b) {
        Collection<Coordinate> nextMoves = TicTacToeUtils.getEmptySquares(b);
        if( nextMoves.size() == 1){
            return nextMoves.stream().findFirst().get();
        }
        return minimax(new MutableBoard(b), Math.min(2, nextMoves.size()), Piece.X);
    }

    private MinMaxCoordinate minimax(MutableBoard b, int depth, Piece player) {

        Collection<Coordinate> nextMoves = TicTacToeUtils.getEmptySquares(b);

        if (nextMoves.isEmpty() || depth == 0) {
            //bestScore = evaluator.score(b);
            return new MinMaxCoordinate(-1, -1, evaluator.score(b));
        } else {
            MinMaxCoordinate best = new MinMaxCoordinate(-1, -1, (player == this.shape) ? Integer.MIN_VALUE : Integer.MAX_VALUE);
            for (Coordinate move : nextMoves) {
                if (b.playPiece(move, player)) {
                    if (player == this.shape) {
                        int currentScore = minimax(b, depth - 1, opponent).getValue();
                        if (currentScore > best.getValue()) {
                            best = new MinMaxCoordinate(move.getX(), move.getY(), currentScore);
                        }
                    } else {
                        int currentScore = minimax(b, depth - 1, this.shape).getValue();
                        if (currentScore < best.getValue()) {
                            best = new MinMaxCoordinate(move.getX(), move.getY(), currentScore);
                        }
                    }
                    b.undo();
                }
            }
            return best;
        }
    }

    class MinMaxCoordinate extends Coordinate {
        private final int value;

        public MinMaxCoordinate(int x, int y, int value) {
            super(x, y);
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
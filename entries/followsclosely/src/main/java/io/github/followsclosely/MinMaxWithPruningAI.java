package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.AbstractAI;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.impl.TicTacToeUtils;

import java.util.Collection;

/**
 *
 */
public class MinMaxWithPruningAI extends AbstractAI {

    private int depth = 2;
    private BoardEvaluator evaluator = new BoardEvaluator();

    public MinMaxWithPruningAI(Piece shape) {
        super(shape);
    }
    public MinMaxWithPruningAI(Piece shape, int depth) {
        super(shape);
        this.depth = depth;
    }

    @Override
    public Coordinate yourTurn(Board b) {
        Collection<Coordinate> nextMoves = TicTacToeUtils.getEmptySquares(b);
        if( nextMoves.size() == 1){
            return nextMoves.stream().findFirst().get();
        }
        return minimax(new MutableBoard(b), depth, Piece.X, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private MinMaxCoordinate minimax(MutableBoard b, int depth, Piece player, int alpha, int beta) {
        Collection<Coordinate> nextMoves = TicTacToeUtils.getEmptySquares(b);

        if (nextMoves.isEmpty() || depth == 0 || TicTacToeUtils.getTurnDetails(b).wonGame()) {
            return new MinMaxCoordinate(b.getLastMove(), evaluator.score(b));
        } else {
            //This will never stay null as the condition above catches the case.
            MinMaxCoordinate best = null;
            for (Coordinate move : nextMoves) {
                if (b.playPiece(move, player)) {
                    if (player == this.shape) {
                        int currentScore = minimax(b, depth - 1, opponent, alpha, beta).getValue();
                        if (best == null || currentScore > alpha) {
                            alpha = currentScore;
                            best = new MinMaxCoordinate(move, currentScore);
                        }
                    } else {
                        int currentScore = minimax(b, depth - 1, this.shape, alpha, beta).getValue();
                        if (best == null || currentScore < beta) {
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

}
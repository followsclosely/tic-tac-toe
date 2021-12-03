package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.impl.MutableBoard;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MinMaxAI extends AbstractAI {

    public MinMaxAI(Piece shape) {
        super(shape);
    }

    @Override
    public Coordinate yourTurn(Board b) {

        MutableBoard board = new MutableBoard(b);

        //Try and play in the center...
        Coordinate center = new Coordinate(board.getSize() / 2, board.getSize() / 2);
        if (board.getPiece(center.getX(), center.getY()) == null) {
            return center;
        }

        return getBestMove(board);
    }

    /**
     * Evaluate every legal move on the board and return the best one.
     *
     * @param board Board to evaluate
     * @return Coordinates of the best move
     */
    public Coordinate getBestMove(MutableBoard board) {

        Coordinate best = null;
        int bestScore = Integer.MIN_VALUE;

        for (int y = 0, height = board.getSize(); y < height; y++) {
            for (int x = 0, width = board.getSize(); x < width; x++) {
                if (board.playPiece(x, y, shape)) {
                    int score = min(board, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    board.undo();
                    if (score > bestScore) {
                        best = new Coordinate(x, y);
                        bestScore = score;
                    }
                }
            }
        }

        return best;
    }

    public int max(MutableBoard board, int depth, int alpha, int beta) {
        int score = score(board);
        if (score != 0 || board.getMovesLeft() == 0) {
            //In this max method the last move was the opponent, so flip it to negative.
            return score * -1;
        }

        int highestScore = Integer.MIN_VALUE;
        for (int y = 0, height = board.getSize(); y < height; y++) {
            for (int x = 0, width = board.getSize(); x < width; x++) {
                if (board.playPiece(x, y, shape)) {
                    highestScore = Math.max(highestScore, min(board, depth + 1, alpha, beta));
                    board.undo();
                    alpha = Math.max(alpha, highestScore);
                    if (alpha >= beta) {
                        return highestScore;
                    }
                }
            }
        }

        return highestScore;
    }

    public int min(MutableBoard board, int depth, int alpha, int beta) {
        int score = score(board);
        if (score != 0 || board.getMovesLeft() == 0) {
            return score;
        }

        int lowestScore = Integer.MAX_VALUE;
        for (int y = 0, height = board.getSize(); y < height; y++) {
            for (int x = 0, width = board.getSize(); x < width; x++) {
                if (board.playPiece(x, y, opponent)) {
                    lowestScore = Math.min(lowestScore, max(board, depth + 1, alpha, beta));
                    board.undo();
                    alpha = Math.min(alpha, lowestScore);
                    if (beta <= alpha) {
                        return lowestScore;
                    }
                }
            }
        }

        return lowestScore;
    }

    public int score(Board b) {
        int score = 0;

        Collection<Collection<Coordinate>> lines = getLines(b, shape);
        for (Collection<Coordinate> line : lines) {

            //Stream out the coordinates that have a value of zero
            List<Coordinate> myPieces = line.stream().filter(p -> b.getPiece(p.getX(), p.getY()) != null).collect(Collectors.toList());
            if (myPieces.size() == b.getGoal()) {
                // 50 points for a win
                score += (b.getGoal() * 10);
            } else {
                // 2 points for a line of 2, if a larger board was used you would get 4 points for a line of 4
                score += myPieces.size();
            }
        }

        return score;
    }
}
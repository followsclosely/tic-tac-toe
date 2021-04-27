package io.github.followsclosely;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.impl.TicTacToeUtils;

public class MinMaxAI implements ArtificialIntelligence {

    private int leaves = 0;

    private int opponent;
    private int shape;

    public MinMaxAI(int shape) {
        this.shape = shape;
    }

    @Override
    public void initialize(int opponent) {
        this.opponent = opponent;
    }

    @Override
    public int getShape() {
        return shape;
    }

    @Override
    public Coordinate yourTurn(Board b) {

        MutableBoard board = new MutableBoard(b);

        int width = board.getWidth();
        int height = board.getHeight();

        //Try and play in the center...
        Coordinate center = new Coordinate(width/2, height/2);
        if( board.getPiece(center.getX(), center.getY()) == 0){
            return center;
        }

        return getBestMove(board);
    }

    /**
     * Evaluate every legal move on the board and return the best one.
     * @param board Board to evaluate
     * @return Coordinates of the best move
     */
    public Coordinate getBestMove(MutableBoard board) {

        Coordinate best = null;
        int bestScore = Integer.MIN_VALUE;

        for (int y = 0, height = board.getHeight(); y < height; y++) {
            for (int x = 0, width = board.getWidth(); x < width; x++) {
                if( board.playPiece(x, y, shape)) {
                    int score = min(board, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    board.undo(x,y);
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
            return score*-1;
        }

        int highestScore = Integer.MIN_VALUE;
        for (int y = 0, height = board.getHeight(); y < height; y++) {
            for (int x = 0, width = board.getWidth(); x < width; x++) {
                if( board.playPiece(x, y, shape)) {
                    highestScore = Math.max(highestScore, min(board, depth+1, alpha, beta));
                    board.undo(x,y);
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
        for (int y = 0, height = board.getHeight(); y < height; y++) {
            for (int x = 0, width = board.getWidth(); x < width; x++) {
                if( board.playPiece(x, y, opponent)) {
                    lowestScore = Math.min(lowestScore, max(board, depth+1, alpha, beta));
                    board.undo(x,y);
                    alpha = Math.min(alpha, lowestScore);
                    if (beta <= alpha) {
                        return lowestScore;
                    }
                }
            }
        }

        return lowestScore;
    }

    public int score(MutableBoard board){
        return ( TicTacToeUtils.getTurnDetails(board, board.getLastMove()).wonGame() ) ? 10 : 0;
    }
}
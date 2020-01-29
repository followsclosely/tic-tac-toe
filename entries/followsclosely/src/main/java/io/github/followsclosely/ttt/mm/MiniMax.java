package io.github.followsclosely.ttt.mm;

import io.github.followsclosely.ttt.BoardEvaluator;
import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.AbstractAI;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.impl.TicTacToeUtils;

/**
 * <b>Minimax</b> (sometimes <b>Minimax</b>, <b>MM</b>[1] or <b>saddle point</b>[2]) is a decision rule used in
 * artificial intelligence, decision theory, game theory, statistics, and philosophy for minimizing the possible
 * loss for a worst case (maximum loss) scenario. When dealing with gains, it is referred to as "maximin"—to
 * maximize the minimum gain. Originally formulated for n-player zero-sum game theory, covering both the cases
 * where players take alternate moves and those where they make simultaneous moves, it has also been extended to
 * more complex games and to general decision-making in the presence of uncertainty.
 * <p>
 * - https://en.wikipedia.org/wiki/Minimax
 */
public class MiniMax extends AbstractAI {

    private final int depth;
    private BoardEvaluator evaluator = null;

    public MiniMax() {
        this(9);
    }

    public MiniMax(int depth) {
        this.depth = depth;
    }

    @Override
    public void initialize(Piece you, Piece opponent) {
        super.initialize(you, opponent);
        this.evaluator = new BoardEvaluator(you, opponent);
    }

    /**
     * Evaluate every legal move on the board and return the best one.
     *
     * @param board Board to evaluate
     * @return Coordinates of best move
     */
    @Override
    public Coordinate yourTurn(Board board) {

        int size = board.getSize();

        //Try and play in the center if open. This saves a lot 16% cpu when depth is at 9 and still wins or ties all
        Coordinate center = new Coordinate(size / 2, size / 2);
        if (board.getPiece(center.getX(), center.getY()) == null) {
            return center;
        }

        MutableBoard b = new MutableBoard(board);
        Coordinate bestMove = null;

        int bestValue = Integer.MIN_VALUE;

        //Play each piece and locate the bestValue play.
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (b.playPiece(x, y, this.shape)) {
                    int moveValue = min(b, depth - 1);
                    if (moveValue > bestValue) {
                        bestMove = new Coordinate(x, y);
                        bestValue = moveValue;
                    }
                    b.undo();
                }
            }
        }

        return bestMove;
    }

    /**
     * Play moves on the board alternating between playing as <code>shape</code> (maximising player) and
     * <code>opponent</code> (minimizing player), analysing the board each time to return the value of the highest
     * value move for the maximising player. Do not search nodes if player's
     * alternatives are better than the current node. Return the highest value move when a terminal node or the maximum
     * search depth is reached.
     *
     * @param b     Board to play on and evaluate
     * @param depth The maximum depth of the game tree to search
     * @return Value of the board
     * @see #min(MutableBoard, int)
     */
    public int max(MutableBoard b, int depth) {
        if (depth == 0 || b.getMovesLeft() == 0 || TicTacToeUtils.getTurnDetails(b).wonGame()) {
            return evaluator.score(b);
        }

        int highestVal = Integer.MIN_VALUE;
        for (int x = 0, size = b.getSize(); x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (b.playPiece(x, y, this.shape)) {
                    highestVal = Math.max(highestVal, min(b, depth - 1));
                    b.undo();
                }
            }
        }

        return highestVal;
    }

    /**
     * Play moves on the board alternating between playing as <code>opponent</code> (minimizing player) and
     * <code>shape</code> (maximising player), analysing the board each time to return the value of the lowest
     * value move for the maximising player. Do not search nodes if player's
     * alternatives are better than the current node. Return the lowest value move when a terminal node or the maximum
     * search depth is reached.
     *
     * @param b     Board to play on and evaluate
     * @param depth The maximum depth of the game tree to search
     * @return Value of the board
     * @see #max(MutableBoard, int)
     */
    public int min(MutableBoard b, int depth) {
        if (depth == 0 || b.getMovesLeft() == 0 || TicTacToeUtils.getTurnDetails(b).wonGame()) {
            return evaluator.score(b);
        }

        int lowestVal = Integer.MAX_VALUE;
        for (int x = 0, size = b.getSize(); x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (b.playPiece(x, y, this.opponent)) {
                    lowestVal = Math.min(lowestVal, max(b, depth - 1));
                    b.undo();
                }
            }
        }

        return lowestVal;
    }
}
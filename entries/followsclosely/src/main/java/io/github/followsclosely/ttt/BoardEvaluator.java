package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Piece;

public class BoardEvaluator {

    public static final int WINNER = 1000;

    protected Piece shape, opponent;

    public BoardEvaluator(Piece shape, Piece opponent) {
        this.shape = shape;
        this.opponent = opponent;
    }

    /**
     * Calculates the current value of the board from the perspective of shape
     *
     * @param b The board to evaluate
     * @return The score of shape - the score of opponent
     */
    public int score(Board b) {
        int scoreShape = score(b, shape);
        if (scoreShape == WINNER) return WINNER;

        int scoreOpponent = score(b, opponent);
        if (scoreOpponent == WINNER) return -WINNER;

        if (scoreShape - scoreOpponent == -999) {
            System.out.println("Stink");
        }
        return scoreShape - scoreOpponent;
    }

    public int score(Board board, Piece p) {

        boolean allMineOrEmpty = true;
        int allMineOrEmptyTotal = 0;

        int total = 0;

        int size = board.getSize();

        // Check rows for winner.
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size && allMineOrEmpty; y++) {
                Piece piece = board.getPiece(x, y);
                if (piece == p) {
                    total++;
                } else if (piece != null) {
                    total = 0;
                    allMineOrEmpty = false;
                }
            }

            if (total == size) {
                return 1000;
            } else if (allMineOrEmpty) {
                allMineOrEmptyTotal += total;
            }

            total = 0;
            allMineOrEmpty = true;
        }

        // Check columns for winner.
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size && allMineOrEmpty; x++) {
                Piece piece = board.getPiece(x, y);
                if (piece == p) {
                    total++;
                } else if (piece != null) {
                    total = 0;
                    allMineOrEmpty = false;
                }
            }
            if (total == size) {
                return 1000;
            } else if (allMineOrEmpty) {
                allMineOrEmptyTotal += total;
            }
            total = 0;
            allMineOrEmpty = true;
        }

        // Check top-left to bottom-right diagonal.
        for (int i = 0; i < size && allMineOrEmpty; i++) {
            Piece piece = board.getPiece(i, i);
            if (piece == p) {
                total++;
            } else if (piece != null) {
                total = 0;
                allMineOrEmpty = false;
            }
        }
        if (total == size) {
            return 1000;
        } else if (allMineOrEmpty) {
            allMineOrEmptyTotal += total;
        }
        total = 0;
        allMineOrEmpty = true;

        // Check top-right to bottom-left diagonal.
        int sizeMinusOne = size - 1;
        for (int i = 0; i < size && allMineOrEmpty; i++) {
            Piece piece = board.getPiece(i, sizeMinusOne - i);
            if (piece == p) {
                total++;
            } else if (piece != null) {
                total = 0;
                allMineOrEmpty = false;
            }

        }

        if (total == size) {
            return 1000;
        } else if (allMineOrEmpty) {
            allMineOrEmptyTotal += total;
        }

        return allMineOrEmptyTotal;
    }
}
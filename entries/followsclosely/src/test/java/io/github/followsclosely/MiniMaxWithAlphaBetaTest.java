package io.github.followsclosely;

import io.github.followsclosely.ttt.mm.MiniMaxWithAlphaBeta;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.AdvancedArtificialIntelligenceTester;
import io.github.followsclosely.ttt.impl.MutableBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MiniMaxWithAlphaBetaTest extends AdvancedArtificialIntelligenceTester<MiniMaxWithAlphaBeta> {

    public MiniMaxWithAlphaBeta instance(Piece shape, Piece opponent) {
        MiniMaxWithAlphaBeta ai = new MiniMaxWithAlphaBeta();
        ai.initialize(shape, opponent);
        return ai;
    }

    //@Test
    void yourTurn01() {
        MiniMaxWithAlphaBeta ai = new MiniMaxWithAlphaBeta();
        ai.initialize(Piece.X, Piece.O);

        // XO-
        // -X-
        // --O
        MutableBoard b = new MutableBoard();
        b.playPiece(1, 1, Piece.X);
        b.playPiece(1, 0, Piece.O);
        b.playPiece(0, 0, Piece.X);
        b.playPiece(2, 2, Piece.O);

        int[][] values = playAllMoves(b, ai);
        printf(b, values);

        assertEquals(1000, values[0][1], "This cell [0][1](" + values[0][1] + ") should get us a win!");
        assertEquals(1000, values[0][1], "This cell [0][2](" + values[0][2] + ") should get us a win!");

        assertEquals(0, values[1][2], "This cell [1][2](" + values[1][2] + ") is a draw!");
        assertEquals(0, values[2][0], "This cell [2][0](" + values[2][0] + ") is a draw!");
        assertEquals(0, values[2][1], "This cell [2][1](" + values[2][1] + ") is a draw!");
    }

    @Test
    void yourTurn02() {
        MiniMaxWithAlphaBeta ai = new MiniMaxWithAlphaBeta();
        ai.initialize(Piece.O, Piece.X);

        // XO-
        // -XX
        // --O
        MutableBoard b = new MutableBoard();
        b.playPiece(1, 1, Piece.X);
        b.playPiece(1, 0, Piece.O);
        b.playPiece(0, 0, Piece.X);
        b.playPiece(2, 2, Piece.O);
        b.playPiece(2, 1, Piece.X);

        int[][] values = playAllMoves(b, ai);
        printf(b, values);

        assertEquals(0, values[0][1], "This cell [0][1](" + values[0][1] + ") should get us a draw!");

        assertEquals(-1000, values[0][2], "This cell [0][2](" + values[0][2] + ") is a loss!");
        assertEquals(-1000, values[1][2], "This cell [1][2](" + values[1][2] + ") is a loss!");

        assertEquals(-1000, values[2][0], "This cell [2][0](" + values[2][0] + ") is a loss!");
    }

    private void printf(MutableBoard b, int[][] values) {
        int size = values.length;
        System.out.println();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Piece p = b.getPiece(x, y);
                System.out.print("\t" + (p == null ? String.format("%05d", values[x][y]) : ("[ " + p + " ]")));
            }
            System.out.println();
        }
        System.out.println();
    }

    private int[][] playAllMoves(MutableBoard b, MiniMaxWithAlphaBeta ai) {
        int size = b.getSize();
        int[][] values = new int[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (b.playPiece(x, y, ai.getShape())) {
                    int moveValue = values[x][y] = ai.min(b, 3, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    b.undo();
                }
            }
        }

        return values;
    }
}
package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicTacToeUtilsTest {

    @Test
    public void testWonGameOnBottomRow() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(), "" +
                "---" +
                "O-O" +
                "XXX");

        TicTacToeUtils.TurnDetails turnDetails = TicTacToeUtils.getTurnDetails(board, new Coordinate(1, 2));
        assertTrue(turnDetails.wonGame());
    }

    @Test
    public void testWonGameOnRightColumn() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(), "" +
                "--X" +
                "O-X" +
                "XOX");

        TicTacToeUtils.TurnDetails turnDetails = TicTacToeUtils.getTurnDetails(board, new Coordinate(2, 0));
        assertTrue(turnDetails.wonGame());
    }

    @Test
    public void testCatWonGame() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(), "" +
                "XOX" +
                "OOX" +
                "XXO");

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                TicTacToeUtils.TurnDetails turnDetails = TicTacToeUtils.getTurnDetails(board, new Coordinate(x, y));
                assertFalse(turnDetails.wonGame(), String.format("Unexpected win at: [%d,%d]", x, y));
            }
        }
    }
}
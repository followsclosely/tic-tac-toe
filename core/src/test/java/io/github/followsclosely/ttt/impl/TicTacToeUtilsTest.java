package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import io.github.followsclosely.ttt.Coordinate;

public class TicTacToeUtilsTest {

    @Test
    public void testWonGameOnBottomRow(){
        MutableBoard board = TestUtils.initialize(new MutableBoard(3,3),"" +
                "000"+
                "202"+
                "111");

        TicTacToeUtils.TurnDetails turnDetails = TicTacToeUtils.getTurnDetails(board, new Coordinate(1, 2));
        Assert.assertTrue(turnDetails.wonGame());
    }

    @Test
    public void testWonGameOnRightColumn(){
        MutableBoard board = TestUtils.initialize(new MutableBoard(3,3),"" +
                "001"+
                "201"+
                "121");

        TicTacToeUtils.TurnDetails turnDetails = TicTacToeUtils.getTurnDetails(board, new Coordinate(2, 0));
        Assert.assertTrue(turnDetails.wonGame());
    }

    @Test
    public void testCatWonGame(){
        MutableBoard board = TestUtils.initialize(new MutableBoard(3,3),"" +
                "121"+
                "221"+
                "112");

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                TicTacToeUtils.TurnDetails turnDetails = TicTacToeUtils.getTurnDetails(board, new Coordinate(x, y));
                Assert.assertFalse(String.format("Unexpected win at: [%d,%d]", x, y), turnDetails.wonGame());
            }
        }
    }
}
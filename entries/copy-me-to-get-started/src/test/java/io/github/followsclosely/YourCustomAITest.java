package io.github.followsclosely;

import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.ai.DummyAI;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.TestUtils;
import org.junit.Assert;
import org.junit.Test;


public class YourCustomAITest {
    @Test
    public void testYourTurnOnEmptyBoard() {
        MutableBoard board = new MutableBoard(3,3);

        Coordinate turn = new DummyAI(2).yourTurn(board);
        Assert.assertTrue("The spot on the board is expected to be empty.",board.getPiece(turn.getX(), turn.getY()) == 0);
    }

    @Test
    public void testYourTurnOnAlmostFilledBoard() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(3,3),"" +
                "111"+
                "110"+
                "111");

        Coordinate turn = new DummyAI(2).yourTurn(board);

        Assert.assertEquals(2, turn.getX());
        Assert.assertEquals(1, turn.getY());
    }

    @Test
    public void testYourTurnOnFilledBoard() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(3,3),"" +
                "111"+
                "111"+
                "111");

        Coordinate turn = new DummyAI(2).yourTurn(board);

        Assert.assertNull(turn);
    }

}
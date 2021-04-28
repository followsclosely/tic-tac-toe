package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.TestUtils;
import io.github.followsclosely.ttt.impl.MutableBoard;
import org.junit.Assert;
import org.junit.Test;

public abstract class ArtificialIntelligenceTester {

    public abstract ArtificialIntelligence instance(int shape, int opponent);

    @Test
    public void testYourTurnOnEmptyBoard() {
        MutableBoard board = new MutableBoard(3, 3);

        Coordinate turn = instance(2, 1).yourTurn(board);
        Assert.assertTrue("The spot on the board was expected to be empty.", board.getPiece(turn.getX(), turn.getY()) == 0);
    }

    @Test
    public void testYourTurnOnAlmostFilledBoard() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(3, 3), "" +
                "111" +
                "110" +
                "111");

        Coordinate turn = instance(2, 1).yourTurn(board);

        Assert.assertEquals(new Coordinate(2, 1), turn);
    }

    @Test
    public void testYourTurnOnFilledBoard() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(3, 3), "" +
                "111" +
                "111" +
                "111");

        Coordinate turn = instance(2, 1).yourTurn(board);

        Assert.assertNull(turn);
    }
}

package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.TestUtils;
import io.github.followsclosely.ttt.impl.MutableBoard;
import org.junit.Assert;
import org.junit.Test;

public abstract class AdvancedArtificialIntelligenceTester extends ArtificialIntelligenceTester {

    @Test
    public void testYourTurnWinIfYouCan() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(3, 3), "" +
                "122" +
                "121" +
                "100");

        Coordinate turn = instance(2, 1).yourTurn(board);

        Assert.assertEquals("You could have won, but you did not!", new Coordinate(1, 2), turn);
    }

    @Test
    public void testYourTurnBlockWin() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(3, 3), "" +
                "727" +
                "727" +
                "700");

        Coordinate turn = instance(1, 2).yourTurn(board);

        Assert.assertEquals("You should have blocked the win!", new Coordinate(1, 2), turn);
    }

}

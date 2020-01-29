package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.TestUtils;
import io.github.followsclosely.ttt.impl.MutableBoard;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public abstract class AdvancedArtificialIntelligenceTester<AI extends ArtificialIntelligence> extends ArtificialIntelligenceTester {

    @Test
    public void testYourTurnWinIfYouCan() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(), "" +
                "XOO" +
                "XOX" +
                "X--");

        Coordinate turn = instance(Piece.X, Piece.O).yourTurn(board);

        assertEquals(new Coordinate(1, 2), turn, "You could have won, but you did not!");
    }

    @Test
    public void testYourTurnBlockWin() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(), "" +
                "XO-" +
                "XOX" +
                "X--");

        Coordinate turn = instance(Piece.X, Piece.O).yourTurn(board);

        assertEquals(new Coordinate(1, 2), turn, "You should have blocked the win!");
    }

    @Test
    public void testYourTurnBlockWinNextTurn() {
        MutableBoard board = TestUtils.initialize("" +
                "--O" +
                "-X-" +
                "-O-");

        Coordinate turn = instance(Piece.X, Piece.O).yourTurn(board);

        assertTrue(Arrays.asList(new Coordinate(0, 1), new Coordinate(0, 2), new Coordinate(2, 2)).contains(turn), "You should have blocked the future win, not " + turn + "!");
    }

}

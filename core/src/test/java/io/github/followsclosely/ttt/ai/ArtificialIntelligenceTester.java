package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.TestUtils;
import io.github.followsclosely.ttt.impl.MutableBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ArtificialIntelligenceTester<AI extends ArtificialIntelligence> {

    public abstract AI instance(Piece shape);

    @Test
    public void testYourTurnOnEmptyBoard() {
        MutableBoard board = new MutableBoard();

        Coordinate turn = instance(Piece.X).yourTurn(board);
        assertNull(board.getPiece(turn.getX(), turn.getY()), "The spot on the board was expected to be empty.");
    }

    @Test
    public void testYourTurnOnAlmostFilledBoard() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(), "" +
                "XXX" +
                "XX-" +
                "OOO");

        Coordinate turn = instance(Piece.X).yourTurn(board);

        assertEquals(new Coordinate(2, 1), turn);
    }

    @Test
    public void testYourTurnOnFilledBoard() {
        MutableBoard board = TestUtils.initialize(new MutableBoard(), "" +
                "XXX" +
                "XXX" +
                "XXX");

        Coordinate turn = instance(Piece.X).yourTurn(board);

        assertNull(turn);
    }
}

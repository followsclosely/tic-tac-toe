package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.TestUtils;
import io.github.followsclosely.ttt.impl.MutableBoard;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractAITest {

    @Test
    public void testSomething() {
        AbstractAI ai = new AbstractAI(Piece.X) {
            @Override
            public Coordinate yourTurn(Board board) {
                return null;
            }
        };

        MutableBoard board = TestUtils.initialize(new MutableBoard(), """
                OXX
                OXO
                X--""");

        Collection<Collection<Coordinate>> lines = ai.getLines(board, Piece.X);

        //This should give us back 2 lines.
        //  [[0,2], [1,1], [2,0]]
        //  [[1,0], [1,1], [1,2]]
        assertEquals(2, lines.size());
    }
}
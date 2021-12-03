package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.TestUtils;
import io.github.followsclosely.ttt.ai.AbstractAI;
import io.github.followsclosely.ttt.impl.MutableBoard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardEvaluatorTest {
    private static List<Object> provideTestScoreData() {
        return Arrays.asList(
                Arguments.of(TestUtils.initialize("""
                        OXX
                        OXO
                        X-O"""), 32)
                , Arguments.of(TestUtils.initialize("""
                        OXX
                        XOO
                        XXO"""), 0)
                , Arguments.of(TestUtils.initialize("""
                        ---
                        -X-
                        ---"""), 4)
                , Arguments.of(TestUtils.initialize("""
                        X--
                        ---
                        ---"""), 3)
        );
    }

    @Test
    public void testGetLines() {

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

        Collection<Collection<Coordinate>> lines = new BoardEvaluator().getLines(board, Piece.X);

        //This should give us back 2 lines.
        //  [[0,2], [1,1], [2,0]]
        //  [[1,0], [1,1], [1,2]]
        assertEquals(2, lines.size());
    }

    public MinMaxWithPruningAI instance(Piece shape) {
        MinMaxWithPruningAI ai = new MinMaxWithPruningAI(shape);
        ai.initialize(shape);
        return ai;
    }

    @ParameterizedTest
    @MethodSource("provideTestScoreData")
    public void testScore(Board b, Integer expected) {
        BoardEvaluator evaluator = new BoardEvaluator();
        assertEquals(expected, evaluator.score(b, Piece.X));
    }

}
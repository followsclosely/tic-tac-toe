package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.BoardEvaluator;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.TestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardEvaluatorTest {
    private static List<Object> provideTestScoreData() {
        return Arrays.asList(
                Arguments.of("X won and can win one other way.", TestUtils.initialize("""
                        OXX
                        OXO
                        X--"""), 1000)
                , Arguments.of("O won.", TestUtils.initialize("""
                        OXX
                        XOO
                        XXO"""), -1000)
                , Arguments.of("X played center", TestUtils.initialize("""
                        ---
                        -X-
                        ---"""), 4 + 0)
                , Arguments.of("X played upper right.", TestUtils.initialize("""
                        X--
                        ---
                        ---"""), 3 + 0)
                , Arguments.of("Both X an dO can win, but O can win two ways.", TestUtils.initialize("""
                        OXO
                        XOO
                        XX-"""), 2 + -4)
                , Arguments.of("Testing any ones game.", TestUtils.initialize("""
                        OOX
                        OXX
                        ---"""), 4 + -2)
                , Arguments.of("O played center and X played left of center.", TestUtils.initialize("""
                        ---
                        XO-
                        ---"""), 1 + -3)
                , Arguments.of("X played center and O played top right.", TestUtils.initialize("""
                        --O
                        -X-
                        ---"""), 3 + -2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestScoreData")
    public void testScore(String description, Board b, Integer expected) {
        System.out.println(description);
        BoardEvaluator evaluator = new BoardEvaluator(Piece.X, Piece.O);
        assertEquals(expected, evaluator.score(b), description);
    }
}
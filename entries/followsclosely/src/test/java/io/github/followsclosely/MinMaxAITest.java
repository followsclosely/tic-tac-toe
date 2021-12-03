package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.TestUtils;
import io.github.followsclosely.ttt.ai.AdvancedArtificialIntelligenceTester;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinMaxAITest extends AdvancedArtificialIntelligenceTester<MinMaxAI> {

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

    public MinMaxAI instance(Piece shape) {
        MinMaxAI ai = new MinMaxAI(shape);
        ai.initialize(shape);
        return ai;
    }

    @ParameterizedTest
    @MethodSource("provideTestScoreData")
    public void testScore(Board b, Integer expected) {
        MinMaxAI ai = instance(Piece.X);
        assertEquals(expected, ai.score(b));
    }
}
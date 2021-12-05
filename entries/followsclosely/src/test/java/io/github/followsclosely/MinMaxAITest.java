package io.github.followsclosely;

import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.AdvancedArtificialIntelligenceTester;

public class MinMaxAITest extends AdvancedArtificialIntelligenceTester<MinMaxWithPruningAI> {

    public MinMaxWithPruningAI instance(Piece shape) {
        MinMaxWithPruningAI ai = new MinMaxWithPruningAI(shape);
        ai.initialize(shape);
        return ai;
    }

}
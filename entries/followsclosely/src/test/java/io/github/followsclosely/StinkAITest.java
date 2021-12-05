package io.github.followsclosely;

import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.AdvancedArtificialIntelligenceTester;

public class StinkAITest extends AdvancedArtificialIntelligenceTester<StinkAI> {

    public StinkAI instance(Piece shape) {
        StinkAI ai = new StinkAI(shape);
        ai.initialize(shape);
        return ai;
    }
}
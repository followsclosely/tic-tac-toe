package io.github.followsclosely;

import io.github.followsclosely.ttt.stink.StinkAI;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.AdvancedArtificialIntelligenceTester;

public class StinkAITest extends AdvancedArtificialIntelligenceTester<StinkAI> {

    public StinkAI instance(Piece shape, Piece opponent) {
        StinkAI ai = new StinkAI();
        ai.initialize(shape, opponent);
        return ai;
    }
}
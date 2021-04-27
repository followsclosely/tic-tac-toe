package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.ArtificialIntelligence;

public class DummyAITest extends ArtificialIntelligenceTester {
    @Override
    public ArtificialIntelligence instance(int shape, int opponent) {
        ArtificialIntelligence ai = new DummyAI(shape);
        ai.initialize(opponent);
        return ai;
    }
}
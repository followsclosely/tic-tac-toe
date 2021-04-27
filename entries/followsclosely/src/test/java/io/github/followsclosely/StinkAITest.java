package io.github.followsclosely;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.ai.AdvancedArtificialIntelligenceTester;

public class StinkAITest extends AdvancedArtificialIntelligenceTester {
    public ArtificialIntelligence instance(int shape, int opponent) {
        ArtificialIntelligence ai = new StinkAI(shape);
        ai.initialize(opponent);
        return ai;
    }
}
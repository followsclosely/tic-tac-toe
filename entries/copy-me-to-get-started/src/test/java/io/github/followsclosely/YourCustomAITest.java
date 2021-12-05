package io.github.followsclosely;

import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.ArtificialIntelligenceTester;


public class YourCustomAITest extends ArtificialIntelligenceTester<YourCustomAI> {

    @Override
    public YourCustomAI instance(Piece shape) {
        YourCustomAI ai = new YourCustomAI(shape);
        ai.initialize(shape);
        return ai;
    }
}
package io.github.followsclosely;

import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.ArtificialIntelligenceTester;


public class YourCustomAITest extends ArtificialIntelligenceTester<YourCustomAI> {

    @Override
    public YourCustomAI instance(Piece you, Piece opponent) {
        YourCustomAI ai = new YourCustomAI();
        ai.initialize(you, opponent);
        return ai;
    }
}
package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.Piece;

public class DummyAITest extends ArtificialIntelligenceTester<DummyAI> {
    @Override
    public DummyAI instance(Piece you, Piece opponent) {
        DummyAI ai = new DummyAI();
        ai.initialize(Piece.X, Piece.O);
        return ai;
    }
}
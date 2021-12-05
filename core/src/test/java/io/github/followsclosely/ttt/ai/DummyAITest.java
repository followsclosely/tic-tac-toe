package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.Piece;

public class DummyAITest extends ArtificialIntelligenceTester<DummyAI> {
    @Override
    public DummyAI instance(Piece shape) {
        return new DummyAI(shape);
    }
}
package io.github.followsclosely;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.ai.DummyAI;

public class YourCustomAI implements ArtificialIntelligence {

    private final int shape;

    public YourCustomAI(int shape) {
        this.shape = shape;
    }

    @Override
    public int getShape() {
        return shape;
    }

    @Override
    public Coordinate yourTurn(Board board) {
        //Your logic replaces this line below.
        return new DummyAI(shape).yourTurn(board);
    }
}
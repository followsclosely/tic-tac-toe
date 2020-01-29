package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Piece;

public abstract class AbstractAI implements ArtificialIntelligence {

    protected Piece opponent;
    protected Piece shape;

    @Override
    public void initialize(Piece you, Piece opponent) {
        this.shape = you;
        this.opponent = opponent;
    }

    @Override
    public Piece getShape() {
        return shape;
    }

    @Override
    public String toString() {
        return getClass().toString();
    }
}
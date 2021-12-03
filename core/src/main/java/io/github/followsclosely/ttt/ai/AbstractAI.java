package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Piece;

public abstract class AbstractAI implements ArtificialIntelligence {

    protected Piece opponent;
    protected Piece shape;

    public AbstractAI(Piece shape) {
        this.shape = shape;
        this.opponent = (shape == Piece.X) ? Piece.O : Piece.X;
    }

    @Override
    public void initialize(Piece shape) {
        this.shape = shape;
        this.opponent = (shape == Piece.X) ? Piece.O : Piece.X;
    }

    @Override
    public Piece getShape() {
        return shape;
    }
}
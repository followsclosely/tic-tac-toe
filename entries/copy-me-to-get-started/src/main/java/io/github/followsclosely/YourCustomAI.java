package io.github.followsclosely;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.ai.DummyAI;

public class YourCustomAI implements ArtificialIntelligence {

    private Piece shape, opponent;

    public YourCustomAI(Piece shape) {
        this.shape = shape;
        this.opponent = (shape == Piece.X) ? Piece.O : Piece.X;
    }

    @Override
    public Piece getShape() {
        return shape;
    }

    @Override
    public void initialize(Piece shape) {
        this.shape = shape;
        this.opponent = (shape == Piece.X) ? Piece.O : Piece.X;
    }

    @Override
    public Coordinate yourTurn(Board board) {
        //Your logic replaces this line below.
        return new DummyAI(shape).yourTurn(board);
    }
}
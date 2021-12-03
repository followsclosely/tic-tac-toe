package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;

import java.util.Random;

public class DummyAI implements ArtificialIntelligence {

    private Random random = new Random();

    private Piece shape, opponent;

    public DummyAI(Piece shape) {
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

    @Override
    public Coordinate yourTurn(Board board) {

        //Select a random place on the board
        int x = random.nextInt(board.getSize());
        int y = random.nextInt(board.getSize());

        //Keep adding to the random spot until a free spot is found.
        for (int i = 0, width = board.getSize(); i < width; i++, x = (x + 1) % width) {
            for (int j = 0, height = board.getSize(); j < height; j++, y = (y + 1) % height) {
                if (board.getPiece(x, y) == null) {
                    return new Coordinate(x, y);
                }
            }
        }

        return null;
    }
}
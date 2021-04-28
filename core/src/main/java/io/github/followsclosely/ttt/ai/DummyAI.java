package io.github.followsclosely.ttt.ai;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;

import java.util.Random;

public class DummyAI implements ArtificialIntelligence {

    private int shape;
    private int opponent;
    private Random random = new Random();

    public DummyAI(int shape) {
        this.shape = shape;
    }

    @Override
    public void initialize(int opponent) {
        this.opponent = opponent;
    }

    @Override
    public int getShape() {
        return shape;
    }

    public int getOpponent() {
        return opponent;
    }

    @Override
    public Coordinate yourTurn(Board board) {

        //Select a random place on the board
        int x = random.nextInt(board.getWidth());
        int y = random.nextInt(board.getHeight());

        //Keep adding to the random spot until a free spot is found.
        for (int i = 0, width = board.getWidth(); i < width; i++, x = (x + 1) % width) {
            for (int j = 0, height = board.getHeight(); j < height; j++, y = (y + 1) % height) {
                if (board.getPiece(x, y) == 0) {
                    return new Coordinate(x, y);
                }
            }
        }

        return null;
    }
}
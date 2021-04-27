package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.ai.DummyAI;

import java.util.Random;

public class YourCustomAI extends DummyAI {
    private int opponent;
    private Random random = new Random();

    public YourCustomAI(int shape) {
        super(shape);
    }

    @Override
    public Coordinate yourTurn(Board board) {
        //Return a random spot.
        return super.yourTurn(board);
    }
}
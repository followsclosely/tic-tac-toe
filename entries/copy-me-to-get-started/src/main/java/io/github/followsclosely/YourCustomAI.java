package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.ai.AbstractAI;
import io.github.followsclosely.ttt.ai.DummyAI;

public class YourCustomAI extends AbstractAI {

    /**
     * This method is called by the Engine when it is "your" turn to play.
     * It should return the position to draw your X.
     *
     * @param board The current state of the game.
     * @return The Position you want to play your X
     */
    @Override
    public Coordinate yourTurn(Board board) {

        //Your shape is this.shape
        //Your opponent is this.opponent

        //Your logic replaces this line below.
        return new DummyAI().yourTurn(board);
    }
}
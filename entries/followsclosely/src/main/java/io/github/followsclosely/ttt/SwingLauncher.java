package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.mm.MiniMaxWithAlphaBeta;
import io.github.followsclosely.ttt.impl.MutableBoard;

public class SwingLauncher {
    public static void main(String[] args) {

        MutableBoard board = new MutableBoard();

        new SwingSupport()
                .setBoard(board)
                .setArtificialIntelligence(new MiniMaxWithAlphaBeta())
                .setTurn(SwingSupport.PLAYER)
                .run();
    }
}
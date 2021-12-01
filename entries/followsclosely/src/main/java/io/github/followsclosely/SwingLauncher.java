package io.github.followsclosely;

import io.github.followsclosely.ttt.SwingSupport;
import io.github.followsclosely.ttt.impl.MutableBoard;

public class SwingLauncher {
    public static void main(String[] args) {

        MutableBoard board = new MutableBoard(5, 3);

        new SwingSupport()
                .setBoard(board)
                .setArtificialIntelligence(new MinMaxAI(SwingSupport.COMPUTER_COLOR))
                .run();
    }
}
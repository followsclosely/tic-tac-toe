package io.github.followsclosely;

import io.github.followsclosely.ttt.Piece;
import io.github.followsclosely.ttt.Simulation;
import io.github.followsclosely.ttt.ai.DummyAI;

public class ShellLauncher {
    public static void main(String[] args) {
        new Simulation()
                .number(10000)
                .addArtificialIntelligence(new MinMaxAI(Piece.X))
                .addArtificialIntelligence(new DummyAI(Piece.O))
                .run()
                .printSummary();
    }
}

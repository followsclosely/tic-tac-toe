package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.mm.MiniMaxWithAlphaBeta;
import io.github.followsclosely.ttt.ai.DummyAI;

public class ShellLauncher {
    public static void main(String[] args) {
        new Simulation()
                .number(1000)
                .ai1(new MiniMaxWithAlphaBeta())
                .ai2(new DummyAI())
                .run()
                .printSummary();
    }
}

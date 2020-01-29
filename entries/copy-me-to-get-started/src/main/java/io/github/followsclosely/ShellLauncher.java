package io.github.followsclosely;

import io.github.followsclosely.ttt.Simulation;

public class ShellLauncher {
    public static void main(String[] args) {
        new Simulation()
                .number(10000)
                .ai1(new YourCustomAI())
                .run()
                .printSummary();
    }
}

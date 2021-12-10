package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.ai.DummyAI;

/**
 * A simulation is a run of a Game multiple times. It uses a builder pattern and should be used like:
 * <pre>
 *  new Simulation()
 *    .number(1000)
 *    .ai1(new MiniMaxWithAlphaBeta())
 *    .ai2(new DummyAI())
 *    .run()
 *    .printSummary();
 * </pre>
 */
public class Simulation {
    private int numberOfGames = 1000;

    private ArtificialIntelligence ai1, ai2;
    private int wins1 = 0, wins2 = 0, ties = 0;
    private long startTime, endTime;

    public Simulation ai1(ArtificialIntelligence ai1) {
        this.ai1 = ai1;
        return this;
    }

    public Simulation ai2(ArtificialIntelligence ai2) {
        this.ai2 = ai2;
        return this;
    }

    public Simulation number(int simulations) {
        this.numberOfGames = simulations;
        return this;
    }

    public Simulation run() {

        this.startTime = System.nanoTime();

        if (ai1 == null) {
            System.out.println("ERROR: ai not provided, call setAi1()");
            return this;
        } else if (ai2 == null) {
            ai2 = new DummyAI();
        }

        for (int i = 1; i <= numberOfGames; i++) {
            System.out.print("\r" + i + "/" + numberOfGames + "  -  " + ai1.getName() + " vs. " + ai2.getName());
            Game game = (i % 2 == 0) ? new Game(ai1, ai2) : new Game(ai2, ai1);
            ArtificialIntelligence winner = game.play();
            if (winner == ai1) {
                wins1++;
            } else if (winner == ai2) {
                wins2++;
            } else {
                ties++;
            }
        }
        System.out.println("\r" + numberOfGames + "/" + numberOfGames + "  -  " + ai1.getName() + " vs. " + ai2.getName() + "  (" + wins1 + "-" + wins2 + "-" + ties + ")");

        this.endTime = System.nanoTime();

        return this;
    }

    public void printSummary() {
        System.out.println(ai1.getName() + " vs. " + ai2.getName());
        System.out.println(wins1 + "\t " + ai1.getName());
        System.out.println(wins2 + "\t " + ai2.getName());
        System.out.println(ties + "\t Cat/Ties");
        System.out.println("\nDuration: " + (endTime - startTime));
    }

    public String getName() {
        return ai1.toString();
    }

    public float getNumberOfGames() {
        return numberOfGames;
    }

    public Integer getWins() {
        return wins1;
    }

    public Integer getWinsOrTies() {
        return wins1 + ties;
    }

}

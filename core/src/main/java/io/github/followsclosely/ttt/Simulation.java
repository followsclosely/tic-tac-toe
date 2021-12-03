package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.ai.DummyAI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation {
    private int numberOfGames = 1000;

    private Map<Piece, AtomicInteger> counts = new HashMap<>() {
        @Override
        public AtomicInteger get(Object key) {
            AtomicInteger value = super.get(key);
            if (value == null) {
                super.put((Piece) key, value = new AtomicInteger(0));
            }
            return value;
        }
    };

    private List<ArtificialIntelligence> ais = new ArrayList<>();

    public Simulation addArtificialIntelligence(ArtificialIntelligence ai) {
        ais.add(ai);
        return this;
    }

    public Simulation number(int simulations) {
        this.numberOfGames = simulations;
        return this;
    }

    public Simulation run() {

        if (ais.size() == 0) {
            System.out.println("ERROR: ai not provided, call addArtificialIntelligence()");
            return this;
        } else if (ais.size() == 1) {
            ais.add(0, new DummyAI(Piece.X));
        }

        for (int i = 1; i <= numberOfGames; i++) {
            Engine engine = new Engine(ais.toArray(new ArtificialIntelligence[0]));
            Piece winner = engine.startGame(i % ais.size());
            counts.get(winner).getAndIncrement();
            System.out.print("\r" + i + "/" + numberOfGames);

//            if (winner == Piece.O) {
//                System.out.println();
//                for (Coordinate c : engine.getBoard().getMoves()) {
//                    System.out.print(c);
//                }
//                System.out.println(" - You LOST!");
//            }

        }
        System.out.println();

        return this;
    }

    public Simulation printSummary() {

        for (Map.Entry<Piece, AtomicInteger> entry : counts.entrySet()) {
            String b = "Player/Color\t" + entry.getKey() + ": " +
                    (float) (Math.round(entry.getValue().floatValue() / numberOfGames * 10000)) / 100 + "%\t" +
                    entry.getValue();
            System.out.println(b);
        }

        return this;
    }

    public Map<Piece, AtomicInteger> getCounts() {
        return counts;
    }

    public String getName() {
        return ais == null || ais.size() == 0 ? null : ais.get(0).getClass().getName();
    }

    public float getNumberOfGames() {
        return numberOfGames;
    }

    public Integer getWins() {
        try {
            return getWins(Piece.X);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getWinsOrTies() {
        return getWins(Piece.X) + getWins(null);
    }

    public Integer getWins(Piece piece) {
        return (counts == null) ? null : counts.get(piece).intValue();
    }

}

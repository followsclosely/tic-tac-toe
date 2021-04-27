package io.github.followsclosely.ttt;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Match {
    private float numberOfGames;
    private ArtificialIntelligence[] ais;
    private Map<Integer, AtomicInteger> counts;

    public Match(ArtificialIntelligence ai1, ArtificialIntelligence ai2){
        ais = new ArtificialIntelligence[]{ai1, ai2};
    }

    public Match run(int numberOfGames){
        this.numberOfGames = numberOfGames;
        this.counts = new Simulation()
                .number(numberOfGames)
                .addArtificialIntelligence(ais[0])
                .addArtificialIntelligence(ais[1])
                .run()
                .getCounts();
        return this;
    }

    public float getNumberOfGames() {
        return numberOfGames;
    }

    public String getName(){
        return ais[0].getClass().getName();
    }
    public Integer getWins() {
        try { return getWins(ais[0].getShape()); } catch(Exception e){ e.printStackTrace(); }
        return null;
    }
    public Integer getWins(int color) {
        return (counts==null) ?  null : counts.get(color).intValue();
    }
}
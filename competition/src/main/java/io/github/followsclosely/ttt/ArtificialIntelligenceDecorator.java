package io.github.followsclosely.ttt;

import org.apache.commons.lang.time.StopWatch;

public class ArtificialIntelligenceDecorator implements ArtificialIntelligence {

    private long duration = 0;

    private final ArtificialIntelligence parent;

    public ArtificialIntelligenceDecorator(ArtificialIntelligence parent) {
        this.parent = parent;
    }

    @Override
    public void initialize(Piece shape) {
        parent.initialize(shape);
    }

    @Override
    public Piece getShape() {
        return parent.getShape();
    }

    @Override
    public Coordinate yourTurn(Board board) {

        long start = System.currentTimeMillis();
        Coordinate turn = parent.yourTurn(board);
        synchronized (parent) {
            this.duration += System.currentTimeMillis() - start;
        }
        return turn;
    }

    public long getDuration() { return duration; }

    @Override
    public String toString(){ return parent.getClass().toString(); }
}

package io.github.followsclosely.ttt;

/**
 * This decorator keeps track of how long each AI is working.
 */
public class ArtificialIntelligenceDecorator implements ArtificialIntelligence {

    private final ArtificialIntelligence parent;
    private long duration = 0;

    public ArtificialIntelligenceDecorator(ArtificialIntelligence parent) {
        this.parent = parent;
    }

    @Override
    public void initialize(Piece shape, Piece opponent) {
        parent.initialize(shape, opponent);
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

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return parent.getClass().toString();
    }

    public String getName() {
        return parent.getName();
    }
}

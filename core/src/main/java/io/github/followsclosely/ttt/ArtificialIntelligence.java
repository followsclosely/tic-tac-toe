package io.github.followsclosely.ttt;

public interface ArtificialIntelligence {

    /**
     * This method is called before a game starts.
     *
     * @param you      The Piece that the AI is playing as
     * @param opponent The piece of the AI opponent
     */
    void initialize(Piece you, Piece opponent);

    /**
     * Gets the shape that the AI is playing for.
     *
     * @return shape of the AI player
     */
    Piece getShape();

    /**
     * This method is called by the Engine when it is "your" turn to play.
     * It should return the position to draw your X.
     *
     * @param board The current state of the game.
     * @return The Position you want to play your X
     */
    Coordinate yourTurn(Board board);

    /**
     * A getter for the name of this AI
     *
     * @return A name for this AI
     */
    default String getName() {
        return getClass().getSimpleName();
    }
}
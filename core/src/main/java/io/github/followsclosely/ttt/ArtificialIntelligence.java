package io.github.followsclosely.ttt;

public interface ArtificialIntelligence {

    /**
     * This method is called before a game starts.
     *
     * @param opponent Color of the opponent.
     */
    default void initialize(int opponent) {
    }

    /**
     * Gets the shape/color that the AI is playing for.
     *
     * @return shape/color of the AI player
     */
    int getShape();

    /**
     * This method is called by the Engine when it is "your" turn to play.
     * It should return the position to draw your X.
     *
     * @param board The current state of the game.
     * @return The Position you want to play your X
     */
    Coordinate yourTurn(Board board);
}
package io.github.followsclosely.ttt;

public interface ArtificialIntelligence {

    /**
     * This method is called before a game starts.
     */
    void initialize(Piece shape);

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
}
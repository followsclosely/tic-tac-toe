package io.github.followsclosely.ttt;

/**
 * A board holds the state of the game in a two dimensional array.
 * A zero null represents an empty spot.
 */
public interface Board {

    /**
     * A getter for goal
     *
     * @return The number of pieces needed in a row to win the game.
     */
    int getGoal();

    /**
     * A getter for size
     *
     * @return The number of pieces across the square board.
     */
    int getSize();

    /**
     * Gets the value/color of the pieces at [x,y]
     *
     * @param x The X coordinate
     * @param y The y coordinate
     * @return The int value of the pieces at [x,y]
     */
    Piece getPiece(int x, int y);

}
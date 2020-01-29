package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;

/**
 * Turn is a pojo that holds a Piece and a Coordinate.
 */
public class Turn {
    private final Piece piece;
    private final Coordinate coordinate;

    public Turn(Piece piece, Coordinate coordinate) {
        this.piece = piece;
        this.coordinate = coordinate;
    }

    public Piece getPiece() {
        return piece;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Turn{" + piece + ", " + coordinate + '}';
    }
}
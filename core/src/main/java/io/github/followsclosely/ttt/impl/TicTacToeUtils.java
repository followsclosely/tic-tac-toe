package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class TicTacToeUtils {

    private static final Coordinate[] DIRECTIONS_TO_SEARCH = {
            new Coordinate(0, -1),
            new Coordinate(1, -1),
            new Coordinate(1, 0),
            new Coordinate(1, 1)
    };

    public static Collection<Coordinate> getEmptySquares(Board b) {
        Collection<Coordinate> moves = new HashSet<>();
        int size = b.getSize();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if( b.getPiece(x,y) == null) {
                    moves.add(new Coordinate(x, y));
                }
            }
        }
        return moves;
    }

    public static TurnDetails getTurnDetails(MutableBoard b) {
        Coordinate turn = b.getLastMove();
        return (turn == null) ? new TurnDetails() : getTurnDetails(b, turn);
    }

    public static TurnDetails getTurnDetails(Board b, Coordinate c) {

        Piece color = b.getPiece(c.getX(), c.getY());

        TurnDetails turn = new TurnDetails();
        if (color == null) {
            return turn;
        }

        for (Coordinate d : DIRECTIONS_TO_SEARCH) {
            Line line = new Line(c);
            for (int i = -1; i < 2; i += 2) {
                boolean streakAlive = true;
                for (int x = c.getX() + (d.getX() * i), y = c.getY() + (d.getY() * i); streakAlive && x < b.getSize() && x >= 0 && y < b.getSize() && y >= 0; x += (d.getX() * i), y += (d.getY() * i)) {
                    Coordinate currentTurn = new Coordinate(x, y);
                    if (streakAlive && b.getPiece(x, y) == color) {
                        if (i == -1) {
                            line.add(0, currentTurn);
                        } else {
                            line.add(currentTurn);
                        }
                    } else {
                        streakAlive = false;
                    }
                }
            }

            if (line.size() >= b.getGoal()) {
                turn.getLines().add(line);
            }
        }

        return turn;
    }

    public static class TurnDetails {
        private List<Line> lines = new ArrayList<>();

        public boolean wonGame() {
            return !lines.isEmpty();
        }

        public List<Line> getLines() {
            return lines;
        }
    }

    public static class Line extends ArrayList<Coordinate> {
        public Line(Coordinate c) {
            add(c);
        }
    }
}
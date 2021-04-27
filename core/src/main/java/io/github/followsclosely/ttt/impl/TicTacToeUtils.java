package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeUtils {

    private static final Coordinate[] DIRECTIONS_TO_SEARCH = {
            new Coordinate(0, -1),
            new Coordinate(1, -1),
            new Coordinate(1, 0),
            new Coordinate(1, 1)
    };

    public static TurnDetails getTurnDetails(Board b, Coordinate c) {

        int color = b.getPiece(c.getX(), c.getY());

        TurnDetails turn = new TurnDetails();
        for (Coordinate d : DIRECTIONS_TO_SEARCH) {
            Line line = new Line(c);
            for (int i = -1; i < 2; i += 2) {
                boolean streakAlive = true;
                for (int x = c.getX() + (d.getX() * i), y = c.getY() + (d.getY() * i); streakAlive && x < b.getWidth() && x >= 0 && y < b.getHeight() && y >= 0; x += (d.getX() * i), y += (d.getY() * i)) {
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
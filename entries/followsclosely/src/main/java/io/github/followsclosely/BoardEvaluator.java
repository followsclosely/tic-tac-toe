package io.github.followsclosely;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class BoardEvaluator {

    /**
     * Calculates the current value of the board from the perspective of 'X'
     *
     * @param b The board to evaluate
     * @return The score of 'X' - the score of 'O'
     */
    public int score(Board b) {
        return score(b, Piece.X) - score(b, Piece.O);
    }

    /**
     * Calculates the current value of just the pieces specified
     *
     * @param b     The board to evaluate
     * @param piece The piece to evaluate
     * @return The score for the current position
     */
    public int score(Board b, Piece piece) {
        int score = 0;

        Collection<Collection<Coordinate>> lines = getLines(b, piece);
        for (Collection<Coordinate> line : lines) {
            //Filter out the coordinates that have a null piece
            List<Coordinate> myPieces = line.stream().filter(p -> b.getPiece(p.getX(), p.getY()) != null).collect(Collectors.toList());
            if (myPieces.size() == b.getGoal()) {
                // 50 points for a win
                score += (b.getGoal() * 10);
            } else {
                // 2 points for a line of 2, if a larger board was used you would get 4 points for a line of 4
                score += myPieces.size();
            }
        }

        return score;
    }

    public Collection<Collection<Coordinate>> getLines(Board b, Piece xo) {
        Collection<Collection<Coordinate>> lines = new HashSet<>();
        for (int i = 0, size = b.getSize(); i < size; i++) {
            //Check the horizontal lines
            //isClean means that this row has just color or 0 values.
            boolean isClean = true;
            Collection<Coordinate> line = new ArrayList<>();
            for (int x = 0, y = i; x < size && isClean; x++) {
                Piece piece = b.getPiece(x, y);
                if (piece == null || piece == xo) {
                    line.add(new Coordinate(x, y));
                } else {
                    isClean = false;
                }
            }
            if (isClean) {
                //System.out.println("("+i+") Adding line: " + line );
                lines.add(line);
            }

            //Check the vertical lines
            isClean = true;
            line = new ArrayList<>();
            for (int x = i, y = 0; y < size && isClean; y++) {
                Piece piece = b.getPiece(x, y);
                if (piece == null || piece == xo) {
                    line.add(new Coordinate(x, y));
                } else {
                    isClean = false;
                }
            }
            if (isClean) {
                //System.out.println("("+i+") Adding line: " + line );
                lines.add(line);
            }

            //Check the diagonal \ lines
            isClean = true;
            line = new ArrayList<>();
            for (int x = 0, y = 0; x < size && isClean; x++, y++) {
                Piece piece = b.getPiece(x, y);
                if (piece == null || piece == xo) {
                    line.add(new Coordinate(x, y));
                } else {
                    //System.out.println("("+i+") Adding line: " + line );
                    isClean = false;
                }
            }
            if (isClean) {
                lines.add(line);
            }

            //Check the diagonal / lines
            isClean = true;
            line = new ArrayList<>();
            for (int x = 0, y = size - 1; x < size && isClean; x++, y--) {
                Piece piece = b.getPiece(x, y);
                if (piece == null || piece == xo) {
                    line.add(new Coordinate(x, y));
                } else {
                    isClean = false;
                }
            }
            if (isClean) {
                lines.add(line);
            }

        }

        return lines;
    }
}

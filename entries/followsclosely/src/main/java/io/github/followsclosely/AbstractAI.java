package io.github.followsclosely;

import io.github.followsclosely.ttt.ArtificialIntelligence;
import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Coordinate;
import io.github.followsclosely.ttt.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public abstract class AbstractAI implements ArtificialIntelligence {

    protected Piece opponent;
    protected Piece shape;

    public AbstractAI(Piece shape) {
        this.shape = shape;
        this.opponent = (shape == Piece.X) ? Piece.O : Piece.X;
    }

    @Override
    public void initialize(Piece shape) {
        this.shape = shape;
        this.opponent = (shape == Piece.X) ? Piece.O : Piece.X;
    }

    @Override
    public Piece getShape() {
        return shape;
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

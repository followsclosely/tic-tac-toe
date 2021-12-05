package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.Board;
import io.github.followsclosely.ttt.Piece;

public abstract class AbstractBoard implements Board {

    protected final Piece[][] state;
    protected int size;
    protected int goal;

    public AbstractBoard(Board board) {
        this.size = board.getSize();

        this.goal = board.getGoal();
        this.state = new Piece[this.size][this.size];

        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                this.state[x][y] = board.getPiece(x, y);
            }
        }
    }

    public AbstractBoard(int size) {
        this.size = size;
        this.goal = size;
        this.state = new Piece[size][size];
    }

    public int getGoal() {
        return goal;
    }

    public Piece getPiece(int x, int y) {
        return state[x][y];
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        Board board = this;
        StringBuilder b = new StringBuilder("----------------\nboard = ");
        for (int x = 0; x < board.getSize(); x++) {
            b.append(x).append(" ");
        }
        b.append("\n");

        for (int y = 0; y < board.getSize(); y++) {
            b.append("     ").append(y).append(": ");
            for (int x = 0; x < board.getSize(); x++) {
                b.append(board.getPiece(x, y)).append(" ");
            }
            b.append("\n");
        }
        return b.toString();
    }
}

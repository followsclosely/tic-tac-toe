package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.Board;

public abstract class AbstractBoard implements Board {

    protected final int[][] state;
    protected int width, height;
    protected int goal;

    public AbstractBoard(Board board) {
        this.height = board.getHeight();
        this.width = board.getWidth();

        this.goal = board.getGoal();
        this.state = new int[this.width][this.height];

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.state[x][y] = board.getPiece(x, y);
            }
        }
    }

    public AbstractBoard(int width, int height) {
        this.width = width;
        this.height = height;

        this.goal = Math.min(width, height);
        this.state = new int[width][height];
    }

    public int getGoal() {
        return goal;
    }

    public int getPiece(int x, int y) {
        return state[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String toString() {
        Board board = this;
        StringBuilder b = new StringBuilder("----------------\nboard = ");
        for (int x = 0; x < board.getWidth(); x++) {
            b.append(x).append(" ");
        }
        b.append("\n");

        for (int y = 0; y < board.getHeight(); y++) {
            b.append("     ").append(y).append(": ");
            for (int x = 0; x < board.getWidth(); x++) {
                b.append(board.getPiece(x, y)).append(" ");
            }
            b.append("\n");
        }
        return b.toString();
    }
}

package io.github.followsclosely;

import io.github.followsclosely.ttt.Coordinate;

class MinMaxCoordinate extends Coordinate {
    private final int value;

    public MinMaxCoordinate(int value) {
        super(-1, -1);
        this.value = value;
    }

    public MinMaxCoordinate(Coordinate parent, int value) {
        super(parent.getX(), parent.getY());
        this.value = value;
    }

    public MinMaxCoordinate(int x, int y, int value) {
        super(x, y);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

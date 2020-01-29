package io.github.followsclosely.ttt.impl;

import io.github.followsclosely.ttt.Board;

public class ImmutableBoard extends AbstractBoard {

    public ImmutableBoard(Board board) {
        super(board);
    }

    public ImmutableBoard(int size) {
        super(size);
    }
}
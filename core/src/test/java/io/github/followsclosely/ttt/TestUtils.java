package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.impl.MutableBoard;

public class TestUtils {

    public static MutableBoard initialize(String config) {
        return initialize(new MutableBoard(), config.replaceAll("\\s+", ""));
    }

    public static MutableBoard initialize(MutableBoard board, String config) {
        int index = config.length() - 1;
        for (int y = board.getSize() - 1; y >= 0 && index >= 0; y--) {
            for (int x = board.getSize() - 1; x >= 0 && index >= 0; x--, index--) {
                char c = config.charAt(index);
                String value = String.valueOf(c).toUpperCase();
                if (c == 'X' || c == 'O') {
                    Piece piece = Piece.valueOf(value);
                    board.playPiece(x, y, piece);
                }
            }
        }

        return board;
    }
}

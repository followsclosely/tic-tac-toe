package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.impl.ImmutableBoard;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.impl.TicTacToeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class runs a game using ArtificialIntelligence to play the pieces.
 */
public class Engine {

    // The number of players.
    int playerCount;

    // A List of players.
    private List<ArtificialIntelligence> players = new ArrayList<>();

    // The state of the game is held in the MutableBoard.
    private MutableBoard board = new MutableBoard(3, 3);

    /**
     * Constructs and new Engine with a default board.
     *
     * @param ais The players in the game. Can be 2-N.
     */
    public Engine(ArtificialIntelligence... ais) {
        Collections.addAll(players, ais);
        playerCount = players.size();
    }

    /**
     * Runs a simulation of one game.
     */
    public int startGame(int firstIndex) {

        //System.out.println(board.toMatrixString());

        //TODO: This is a hack that needs to be cleaned up...
        ArtificialIntelligence ai0 = players.get(0);
        ArtificialIntelligence ai1 = players.get(1);
        ai0.initialize(ai1.getShape());
        ai1.initialize(ai0.getShape());

        //The total number of turns before the board is full
        int total = board.getWidth() * board.getHeight();
        for (int turn = firstIndex; turn < total; turn++) {
            ArtificialIntelligence player = players.get(turn % playerCount);

            //Pass an immutable board down as to not mess with this standard
            Coordinate coordinate = player.yourTurn(new ImmutableBoard(board));
            board.playPiece(coordinate.getX(), coordinate.getY(), player.getShape());
            //System.out.println(board);

            //Check for a winner, print if found
            TicTacToeUtils.TurnDetails turnDetails = TicTacToeUtils.getTurnDetails(board, coordinate);

            if (turnDetails.wonGame()) {
                //System.out.println(board);
                return player.getShape();
            }
        }

        // System.out.println(board);
        return -1;
    }

    public MutableBoard getBoard() {
        return board;
    }
}

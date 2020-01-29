package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.impl.ImmutableBoard;
import io.github.followsclosely.ttt.impl.MutableBoard;
import io.github.followsclosely.ttt.impl.TicTacToeUtils;

/**
 * This class runs a game using ArtificialIntelligence to play. It should be used as follows:
 * <pre>
 * Game game = new Game(ai1, ai2);
 * ArtificialIntelligence winner = game.play();
 * Board b = game.getBoard();
 * </pre>
 */
public class Game {

    // The state of the game is held in the MutableBoard.
    private final MutableBoard board = new MutableBoard();
    // A List of players.
    private final ArtificialIntelligence[] players;

    /**
     * Constructs and new Engine with a default board.
     *
     * @param ais The players in the game. Can only be 2 at the moment
     */
    public Game(ArtificialIntelligence... ais) {
        assert ais != null && ais.length == 2;
        this.players = ais;
    }

    /**
     * Runs a simulation of one game.
     *
     * @return The winner of the game
     */
    public ArtificialIntelligence play() {

        //TODO: This is a hack that needs to be cleaned up...
        players[0].initialize(Piece.X, Piece.O);
        players[1].initialize(Piece.O, Piece.X);

        //System.out.println(ai0.getName() + "(" + ai0.getShape() + ") vs. (" + ai1.getShape() + ")" + ai1.getName());

        //The total number of turns before the board is full
        int total = board.getSize() * board.getSize();
        for (int turn = 0; turn < total; turn++) {
            ArtificialIntelligence player = players[turn % 2];

            //Pass an immutable board down as to not mess with the standard
            Coordinate coordinate = player.yourTurn(new ImmutableBoard(board));
            board.playPiece(coordinate, player.getShape());

            //Check for a winner
            if (TicTacToeUtils.getTurnDetails(board, coordinate).wonGame()) {
                return player;
            }
        }

        return null;
    }

    public MutableBoard getBoard() {
        return board;
    }
}

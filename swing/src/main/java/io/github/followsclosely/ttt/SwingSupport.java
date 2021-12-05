package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.ai.DummyAI;
import io.github.followsclosely.ttt.impl.MutableBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class assembles the game into a JFrame, registers listeners and displays.
 *
 * @author Matthew L. Wilson
 */
public class SwingSupport {

    public static final Piece PLAYER = Piece.X;
    public static final Piece COMPUTER = Piece.O;

    //Create the model for the game.
    private MutableBoard board;
    private ArtificialIntelligence bot;

    private Piece turn = PLAYER;

    public static void main(String[] args) {
        new SwingSupport()
                .setArtificialIntelligence(new DummyAI(COMPUTER))
                .run();
    }

    public SwingSupport setArtificialIntelligence(ArtificialIntelligence bot) {
        this.bot = bot;
        return this;
    }

    public SwingSupport setBoard(MutableBoard board) {
        this.board = board;
        return this;
    }

    /**
     * Launches the JFrame that contains the BoardPanel to display the game.
     */
    public void run() {
        if (board == null) {
            board = new MutableBoard();
        }

        bot.initialize(COMPUTER);

        //Create the panel that displays the tic-tac-toe board.
        TicTacToePanel boardPanel = new TicTacToePanelDynamic(board);

        JPanel statusPanel = new JPanel(new BorderLayout());
        JTextField status = new JTextField("");
        status.setEditable(false);
        statusPanel.add(status, BorderLayout.CENTER);
        JButton undo = new JButton("<");
        undo.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            board.undo();
            SwingUtilities.invokeLater(boardPanel::repaint);
        }));
        statusPanel.add(undo, BorderLayout.EAST);

        JFrame frame = new JFrame("Connect");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.SOUTH);
        frame.pack();

        //Register a listener so an X or O can be rendered as the mouse moves.
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                boardPanel.setHoverLocation(e);
            }
        });

        //Register a listener to capture when a piece is to be played.
        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (turn == PLAYER) {
                    if (boardPanel.setPiece(e, PLAYER)) {
                        turn = COMPUTER;
                        new Thread(() -> {
                            Coordinate coordinate = bot.yourTurn(board);
                            if (coordinate != null) {
                                board.playPiece(coordinate.getX(), coordinate.getY(), bot.getShape());
                                turn = PLAYER;
                                SwingUtilities.invokeLater(boardPanel::repaint);
                            }
                        }).start();
                    }
                } else {
                    status.setText("Not your turn!");
                    Executors.newSingleThreadScheduledExecutor().schedule(() -> SwingUtilities.invokeLater(() -> status.setText("")), 5, TimeUnit.SECONDS);
                }
            }
        });

        frame.setVisible(true);
    }
}
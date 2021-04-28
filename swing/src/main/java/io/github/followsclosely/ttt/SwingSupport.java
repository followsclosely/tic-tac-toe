package io.github.followsclosely.ttt;

import io.github.followsclosely.ttt.ai.DummyAI;
import io.github.followsclosely.ttt.impl.MutableBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * This class assembles the game into a JFrame, registers listeners and displays.
 *
 * @author Matthew L. Wilson
 */
public class SwingSupport {

    public static final int PLAYER_COLOR = 1;
    public static final int COMPUTER_COLOR = 2;

    //Create the model for the game.
    private MutableBoard board;
    private ArtificialIntelligence bot;

    public static void main(String[] args) {
        new SwingSupport()
                .setArtificialIntelligence(new DummyAI(COMPUTER_COLOR))
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
            board = new MutableBoard(3, 3);
        }

        bot.initialize(PLAYER_COLOR);

        //Create the panel that displays the tic tac toe board.
        TicTacToePanel boardPanel = new TicTacToePanelDynamic(board);

        JPanel statusPanel = new JPanel(new BorderLayout());
        JTextField status = new JTextField("");
        status.setEditable(false);
        statusPanel.add(status, BorderLayout.CENTER);
        JButton undo = new JButton("<");
//        undo.addActionListener((ActionEvent e) -> SwingUtilities.invokeLater(() -> {
//            Coordinate coordinate = board.undo();
//            System.out.println(String.format("Undoing %s...", coordinate));
//            SwingUtilities.invokeLater( () -> boardPanel.repaint());
//        }));
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
                if (boardPanel.setPiece(e, PLAYER_COLOR)) {
                    new Thread(() -> {
//                        try {
//                            Thread.sleep(250);
//                        } catch (InterruptedException ignore) {
//                        }
                        Coordinate coordinate = bot.yourTurn(board);
                        if (coordinate != null) {
                            board.playPiece(coordinate.getX(), coordinate.getY(), bot.getShape());
                            SwingUtilities.invokeLater(boardPanel::repaint);
                        }
                    }).start();
                }
            }
        });

        //Register a listener that resets the board when a key is pressed.
//        frame.addKeyListener(new KeyAdapter() {
//            public void keyTyped(KeyEvent e) {
//                if( e.getKeyChar() == 'r'){
//                    System.out.println(String.format("Restarting board..."));
//                    board.reset();
//                    SwingUtilities.invokeLater( () -> boardPanel.repaint());
//                }
//            }
//        });

        frame.setVisible(true);
    }
}
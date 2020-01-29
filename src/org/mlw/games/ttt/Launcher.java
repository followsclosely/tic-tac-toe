package org.mlw.games.ttt;

import javax.swing.*;
import java.awt.event.*;

/** This class assembles the game into a JFrame, registers listeners and displays.
 *
 * @author Matthew L. Wilson
 */
public class Launcher {
    public static void main(String[] args){
        //Create the model for the game.
        TicTacToeBoard board = new TicTacToeBoard(3,3);

        //Create the panel that displays the tic tac toe board.
        TicTacToePanel panel = new TicTacToePanelDynamic(board);

        //Create a frame to hold the game.
        JFrame frame = new JFrame();

        //Add our custom panel that renders the game.
        frame.add(panel);

        //Close the program when the user clicks the x.
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Show and pack the frame.
        frame.setVisible(true);
        frame.pack();

        //Register a listener so an X or O can be rendered as the mouse moves.
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                panel.setHoverLocation(e);
            }
        });

        //Register a listener to capture when a piece is to be played.
        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                panel.setPiece(e);
            }
        });

        //Register a listener that resets the board when a key is pressed.
        frame.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if( e.getKeyChar() == 'r'){
                    board.reset();
                    SwingUtilities.invokeLater( () -> panel.repaint());
                }
            }
        });
    }
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class gameFrame extends JFrame{

    //creating instance of gamePanel class
    gamePanel panel;

    //Constructor of gameFrame class
    gameFrame(){
        panel= new gamePanel();
        this.add(panel);
        this.setTitle("Ping-Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); //To appear the window in the middle of the screen


    }
}

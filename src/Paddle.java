import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Paddle extends Rectangle{

    //Id 1 for paddle1 & Id 2 for paddle2
    int id;
    //To determine how fast the paddle will move
    int yVelocity;
    //To set speed of the moving paddle
    int speed=14;

    //Constructor of Paddle class
    Paddle(int x,int y,int PADDLE_WIDTH,int PADDLE_HEIGHT,int id){
        //super constructor
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=id;


    }
    public void keyPressed(KeyEvent e){
        //To switch between paddles
        switch (id){
            case 1:
                //To move UP
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(-speed);
                    move();
                }
                //To move DOWN
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                //To move UP
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(-speed);
                    move();
                }
                //To move DOWN
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(speed);
                    move();
                }
                break;

        }

    }
    public void keyReleased(KeyEvent e){

        //To switch between paddles
        switch (id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(0);
                    move();
                }
                break;

        }

    }
    //Function to set coordinates of paddle(vertical axis)
    public void setYDirection(int yDirection){
        yVelocity= yDirection;

    }
    public void move(){
        y= y+ yVelocity;

    }
    public void draw(Graphics g){
        //For player1
        if(id==1){
            g.setColor(Color.RED);
        }
        //For player2
        else{
            g.setColor(Color.GREEN);
        }
        g.fillRect(x,y,width,height);

    }
}

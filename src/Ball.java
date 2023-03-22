import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Ball extends Rectangle {

    //Instance of the Random class
    Random random;

    //Ball Speed Variables
    int xVelocity;
    int yVelocity;
    //Adjusting Ball speed
    int initialSpeed=4;

    //Constructor of Ball class
    Ball(int x,int y,int width,int height){
        super(x,y,width,height);

        random = new Random();
        //If 0 go left, if 1 go right
        int randomXDirection= random.nextInt(2);
        if(randomXDirection==0){
            randomXDirection --;
        }
        setXDirection(randomXDirection*initialSpeed);

        int randomYDirection= random.nextInt(2);
        if(randomYDirection==0){
            randomYDirection --;
        }
        setYDirection(randomYDirection*initialSpeed);

    }
    //Function To set X-coordinates of ball (horizontal axis)
    public void setXDirection(int randomXDirection){
        xVelocity= randomXDirection;

    }
    //Function To set Y-coordinates of ball (vertical axis)
    public void setYDirection(int randomYDirection){
        yVelocity=randomYDirection;

    }
    public void move(){
        x += xVelocity;
        y += yVelocity;

    }
    public void draw(Graphics g){
        g.setColor(Color.cyan);
        g.fillOval(x,y,width,height);

    }
}

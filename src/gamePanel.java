import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class gamePanel extends JPanel implements Runnable{

    //set game-width
    static final int GAME_WIDTH=1000;
    //set game-height
    static final int GAME_HEIGHT=(int)(GAME_WIDTH*(0.5555));
    //To set dimensions using an instance of 'Dimension' class
    static final Dimension SCREEN_SIZE=new Dimension(GAME_WIDTH,GAME_HEIGHT);
    //Set diameter of the ball
    static final int BALL_DIAMETER=25;
    //set paddle width
    static final int PADDLE_WIDTH=25;
    //set paddle height
    static final int PADDLE_HEIGHT=140;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1; //Player1
    Paddle paddle2; //Player2
    Ball ball;
    Score score;

    //Constructor of gamePanel class
    gamePanel(){
        newPaddle();
        newBall();

        //Instance of score class
        score=new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);

        //adding action listener
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread=new Thread(this);
        gameThread.start();

    }
   //Function To create a new ball
    public void newBall(){
        random= new Random();
        //Passing coordinates
        ball=new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);

    }
    //Function To reset a level
    public void newPaddle(){

        paddle1= new Paddle(25,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2= new Paddle((GAME_WIDTH-PADDLE_WIDTH-25),(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);


    }
    public void paint(Graphics g){
        image= createImage(getWidth(),getHeight());
        graphics= image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this); //(0,0) -> Top-Left Corner, this-> gamePanel

    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);

        ball.draw(g);
        score.draw(g);

    }
    // Function To move objects
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();

    }
    //Function To check any collision of the ball
    public void checkCollision(){

        //Bounces ball of Top & Bottom window edges
        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= (GAME_HEIGHT-BALL_DIAMETER)){
            ball.setYDirection(-ball.yVelocity);
        }


        //Bounces Ball of Paddles
        if(ball.intersects(paddle1)){    //For paddle 1
            ball.xVelocity= Math.abs(ball.xVelocity);
            //To increment speed after bounce
//            ball.xVelocity++;  //Optional
            if(ball.yVelocity > 0){
//                ball.yVelocity++;  //Optional
            }
            else{
                ball.yVelocity--;
            }
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)){    //For paddle 2
            ball.xVelocity= Math.abs(ball.xVelocity);
            //To increment speed after bounce
//            ball.xVelocity++;  //Optional
            if(ball.yVelocity > 0){
//                ball.yVelocity++;  //Optional
            }
            else{
                ball.yVelocity--;
            }
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }



        //This stops paddles at window edges
        if(paddle1.y<=0){
            paddle1.y=0;
        }
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
        }

        if(paddle2.y<=0){
            paddle2.y=0;
        }
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;
        }


        //Gives a player 1 point and creates new paddles and ball
            //Gives a player 2 a point if player 1 misses
        if(ball.x <= 0){
            score.player2++;
            newPaddle();
            newBall();
        }
            //Gives a player 1 a point if player 2 misses
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER){
            score.player1++;
            newPaddle();
            newBall();
        }


    }
    public void run(){
        //Game Loop
        long lastTime= System.nanoTime();
        double amountOfTicks= 60.0;  //60 frames per second
        double ns= 1000000000/(amountOfTicks);
        double delta=0;

        while(true){
            long now=System.nanoTime();
            delta += (now-lastTime)/ns;
            lastTime=now;
            if(delta >=1){
                move();
                checkCollision();
                repaint();
                delta--;

            }
        }

    }
    //Inner Class For Action-Listener
    public class AL extends KeyAdapter{
        //KeyPressed Method
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }
        //KeyReleased Method
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }

    }
}

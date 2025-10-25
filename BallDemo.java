import java.awt.Color;
import java.util.Random;
import java.util.Collections;
import java.util.HashMap;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * @ new Author: pasta Pizza
 * Version 2025.21.25 (due 27th)
 * 
 * GOAL make a new method "boxBounce'
 * 
 * note: books says need a collection so arraylist,hashmap or hashset.
 * 2 need random utility for setting random positions for balls.
 * 5 to 30 balls needed
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Box box;
    private Box boxBall;  //need to give the deets but do we need it to be class Box?
    //private HashMap<String,Ball>balls; //basic hashmap, may need to change if no ball class.but got hashmap 
    private HashMap<String, BoxBall> ballMap;  //error but fixed! ok needs to be a field not local variable!
    
    
     
    /**
     * a basic setter for the hash map which will store the balls, the parameter is a hasmap with 
     * @stringkey integer key-partner
     */
    
    public void  setBallMap(HashMap<String,BoxBall>ballMap){
        this.ballMap = ballMap;
        
    }
    
    

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     * 
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        box=new Box (100,100,500,400, myCanvas);
        box.draw();
        
    }

    /**
     * boxBounce - simulate 5-50 balls bouncing within a box
     * 
     * @param numOfBalls number of balls to simulate bouncing, clamped between 5-50. 
     * oh...already pre-made.. ok fill this out.
     * 2param numofBalls (int?)
     * 
     * we ALSO need a new class the "BoxBall class" which balls bounce off wall of edge.
     * 
     * note can use BOUNCE to simulate at moment 2 balls for free...so just figure out how it all works!
     */
    public void boxBounce(int numOfBalls)
    {
           int ballAmount = numOfBalls; //set the number of balls for animation
           //just quick corrector if below 5 balls or over 30
           if(numOfBalls<5){
               numOfBalls = 5;
           } else if(numOfBalls > 30){
               numOfBalls = 30;
           }
           
           //we need to draw the canvas...
            myCanvas = new Canvas("Bouncing balls", 600, 500);
            box=new Box (100,100,500,400, myCanvas);
            box.draw();
          //ok now we use  a loop which takes the hashmap and draws the balls.
             Random rand = new Random();
             ballMap = new HashMap<>();
          //now lets make a random balls iterator!
          for(int i = 1; i <= numOfBalls;i++){
              //now to painfull make these balls work. like all got to be randomized,
               int x = rand.nextInt(400) + 100;
               int y = rand.nextInt(300) + 100;
               int xSpeed = rand.nextInt(7) + 1;
               int ySpeed = rand.nextInt(7) + 1;
               int diameter = rand.nextInt(15) + 10;
               Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
              
              
              BoxBall ball = new BoxBall(x,y,xSpeed,color,box,myCanvas);
              
              ballMap.put("ball"+ i,ball);
              ball.draw();
              
          }
           
           //bounce.();
    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    
}

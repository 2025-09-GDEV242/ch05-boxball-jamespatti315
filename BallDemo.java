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
 * @ new Author: James Patti
 * Version 2025.21.25 (due 27th)
 * 
 * GOAL make a new method "boxBounce'
 * 
 * note: books says need a collection so arraylist,hashmap or hashset.
 * 2 need random utility for setting random positions for balls.
 * 5 to 30 balls needed
 * 
 * overall it good, not all done but tired, this felt worse then a hernia.
 * 
 * I NKNOW the issue the constructors is incorrect. so its 99% there.
 * 
 * ok correction..no where...need to make walls detect balls and basically making a microgame...I wish was better but this was a miserable project.
 * 
 * 
 * I tried, I am gonna work on next now, my brother burnt out, i;m burnt out. I trying to learn but this just too much at once.
 */


public class BallDemo   
{
    private Canvas myCanvas;
    private Box box;
    private Box boxBall;  //need to give the deets but do we need it to be class Box?
    //private HashMap<String,Ball>balls; //basic hashmap, may need to change if no ball class.but got hashmap 
    private HashMap<String, BoxBall> ballMap;  //error but fixed! ok needs to be a field not local variable!
    
    
    
    /**
     * Here lets make the basic for a a boxBall object, setting what we need to shove into it
     * we need: x position, y position, xspeed,y speed, diameter, color, canvas and a box type ...box
     * 
    */
   
   public class BoxBall {
    private int xPosition;  //set x position
    private int yPosition; //ditto y
    private int xSpeed; //set speed on x
    private int ySpeed; //ditto speed on y
    private int diameter; //diameter of the "playfield"
    private Color color; //so can color balls
    private Canvas canvas; //so we can draw canvas
    private Box box;   //a box..so we got the "box" to set balls bouncing.
    
    
    public int getLeft() {
    return xPosition;
}

public int getRight() {
    return xPosition;
}

public int getTop() {
    return yPosition;
}

public int getBottom() {
    return yPosition;
}

private void erase() {
    canvas.eraseCircle(xPosition, yPosition, diameter);
}
    
    public BoxBall(int x, int y, int xSpeed, int ySpeed, int diameter, Color color, Box box, Canvas canvas) {
        this.xPosition = x;
        this.yPosition = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.diameter = diameter;
        this.color = color;
        this.canvas = canvas;
        this.box = box;
    }
    //set positions and diameter down below.
    public void draw(){
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition,yPosition,diameter);
    }
   //erase balls to draw them. this ..THIS is literally a , someday may get good but not today.
      public void move() {
        erase();
        
        xPosition += xSpeed;
        yPosition += ySpeed;
   
   //now lets set the walls
   
   if(xPosition <= getLeft() || xPosition >= getRight() - diameter){
       xSpeed = -xSpeed;
   }
   
   if(yPosition <=getTop() || yPosition >= getBottom() - diameter){
       ySpeed = -ySpeed;
       }
       draw();
    }
}
     
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
            //myCanvas = new Canvas("Bouncing balls", 600, 500);
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
              
               //fudge it, local variable
               int speed = rand.nextInt(7)+1;
              
              BoxBall ball = new BoxBall(x, y, xSpeed, ySpeed, diameter, color, box, myCanvas);
              //WELP THIS won't work. I don't know,.  
            
              ballMap.put("ball"+ i,ball);
              ball.draw();
              
           
              
            }
                 // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(10);           // small delay
           for(BoxBall ball : ballMap.values())
           ball.move();
    }
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


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CTIS
 */
public class Stick {
    private int s_length;
    private int s_height = 15;
    private boolean isLocked=false;

    //... Instance variables
    private int s_x;           // x and y coordinates 
    private int s_y= 508;

    private int s_velocityX =15;   // Pixels to move each time it moves

    private int s_rightBound;  // Maximum permissible x value.

    private Color c;



    //constructor
    public Stick(){
        s_length= 60;
        s_x= 400- (s_length/2);       
        c=Color.GREEN;
        s_rightBound= 800-s_length;
    }

    //boundaries
    public void setBound(int width) {
        s_rightBound  = width- s_length;
    }

    //movement of fish
    public void moveRight() {   
        if(s_x < 800-s_length)
            s_x += s_velocityX;
        else
            s_x= 800-s_length;
     
    }
    public void moveLeft() {        
        if (s_x > 0) { 
           s_x -= s_velocityX; 
        }
        else
            s_x= 0;
    }

    public void drawS(Graphics g) {
        Graphics2D gra= (Graphics2D) g;//Get a 2D device context

        gra.setPaint(c ); //pick color
        gra.fillRect(s_x, s_y, s_length,s_height); // boundary
       
}
    public int getSX() { return s_x;}
    public int getLength(){ return s_length;}
    public boolean getIsLocked() { return isLocked;}
    
    public void setSX(int a){s_x=a;}
    public void setLocked(boolean a){isLocked=a;}
    
    public void setColor( Color newC){ // for others
        c=newC;
    }
}

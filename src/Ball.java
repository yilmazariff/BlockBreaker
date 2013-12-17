
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
public class Ball {
    
    private int radius = 20;

    //... Instance variables
    private int m_x;           // x and y coordinates 
    private int m_y;

    private int m_velocityX =4;   // Pixels to move each time move() is called.
    private int m_velocityY =-8;

    private int m_rightBound;  // Maximum permissible x, y values.
    private int m_bottomBound;
    
    private boolean changeX;
    private boolean changeY;
    private Color c;



    //constructor
    public Ball(){
        m_x=225;
        m_y = 490;
        changeX=false;
        changeY=false;
        c=Color.WHITE;
    }

    //boundaries
    public void setBounds(int width, int height) {
        m_rightBound  = width  - radius;
        m_bottomBound = height - radius;
    }

    //movement of fish
    public void move(int x,int y) {
        m_x += m_velocityX;
        m_y += m_velocityY;
        
       
        
        
        
        


        if (m_x < 0) {                  // If at or beyond left side
            m_x= 0;                     // Place against edge and
            setVelocity();           //set velocities again to not stop 
      /*
            if(duvar random bonusu){
                m_velocityX =(int) -(m_velocityX*(Math.random()+0.5)); // reverse x direction with random velocity.
                m_velocityY -= (int) (m_velocityY*(Math.random()+0.5));//  y direction with random velocity.
            }else
      */      
            m_velocityX = -m_velocityX; // reverse x direction.
            changeX=!changeX;
  

        } else if (m_x > m_rightBound) { // If at or beyond right side
            m_x         = m_rightBound;    // Place against right edge.
            setVelocity();           //set velocities again to not stop 
            
              /*
            if(duvar random bonusu){
                m_velocityX =(int) -(m_velocityX*(Math.random()+0.5)); // reverse x direction with random velocity.
                m_velocityY -= (int) (m_velocityY*(Math.random()+0.5));//  y direction with random velocity.
            }else
      */  
            m_velocityX = -m_velocityX; // reverse x direction.
            changeX=!changeX;
        }

        if (m_y < 0) {                 // if we're at top
            m_y       = 0;
            setVelocity();       //set velocities again to not stop 
               /*
            if(duvar random bonusu){
                m_velocityY = (int) -(m_velocityY*(Math.random()+0.5));// reverse y direction with random velocity.
                m_velocityX = (int) (m_velocityX*(Math.random()+0.5));  //  x direction with the random velocity.
            }else
      */  
            m_velocityY = -m_velocityY; // reverse y direction.
            changeY=!changeY;
         }
         else if (m_y > 550) { // if we're at bottom
            
            System.out.println("yarraklara geldi aşkımız ikimizde ŞAŞKINIZ!!!");
            
        } 
         else if (m_y > 490) { // if we're at bottom
            
          
            if(m_x>x && m_x<x+y){
                
            
            setVelocity();       //set velocities again to not stop
           
                    /*
            if(duvar random bonusu){
                m_velocityY = (int) -(m_velocityY*(Math.random()+0.5));// reverse y direction with random velocity.
                m_velocityX = (int) (m_velocityX*(Math.random()+0.5));  //  x direction with the random velocity.
            }else
      */  
            
            m_velocityY = -m_velocityY; // reverse y direction.
            changeY=!changeY;
            }
        }
       

    }

    public boolean isCatched(){
        boolean flag=false;
 //           if(m_y== )
        
        return flag;
    }

    public void draw(Graphics g) {
        Graphics2D gra= (Graphics2D) g;//Get a 2D device context

        gra.setPaint(c ); //pick color
        gra.fillOval(m_x, m_y, radius,radius); // boundary
       
}

    public int  getRadius() { return radius;}
    public int  getX()        { return m_x;}
    public int  getY()        { return m_y;}

    public void setPosition(int x, int y) {
        m_x = x;
        m_y = y;
    }
    public void setVelocity(){
        if(changeX)
            m_velocityX-=1;
        else
            m_velocityX+=1;
        if(changeY)
            m_velocityY+=1;
        else
            m_velocityY-=1;
    }
    public void setRadius(int x){
        radius=x;
    }
    public void setColor( Color newC){ // for fireball
        c=newC;
    }

}

    
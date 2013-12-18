
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

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
    
    private int radius = 16;

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
        m_x=400-radius/2;
        m_y = 508-radius;
        changeX=false;
        changeY=false;
        c=Color.WHITE;
    }

    //boundaries
    public void setBounds(int width, int height) {
        m_rightBound  = width  - (radius);
        m_bottomBound = height - radius;
    }

    //movement of fish
    public void move(int x,int st_len, Image [][] a) {
        m_x += m_velocityX;
        m_y += m_velocityY;
        
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++){
                if(a[i][j]!=null){
                    if(m_x+radius>=42+i*72 && m_x<= 38+(i+1)*72 && m_y+radius>=82+j*24 && m_y<=78+(j+1)*24){                      
                        if(changeY && m_x+radius/2>40+i*72 && m_x+radius/2< 40+(i+1)*72){                                  
                            changeY= false;
                            setVelocity();
                            a[i][j]=null;
                            GameManager.score+=10;
                            System.out.println("üstten  "+GameManager.score);
                        }
                        else if(!changeY && m_x+radius/2>40+i*72 && m_x+radius/2< 40+(i+1)*72){                              
                            changeY= true;
                            setVelocity();
                            a[i][j]=null;
                            GameManager.score+=10;
                            System.out.println("alttan  "+GameManager.score);
                        }
                        else if(changeX && m_y+radius/2>=80+j*24 && m_y+radius/2<=80+(j+1)*24){
                            changeX= false;
                            setVelocity();
                            a[i][j]=null;
                            GameManager.score+=10;
                            System.out.println("soldan  "+GameManager.score);
                        }
                        else if(!changeX && m_y+radius/2>=80+j*24 && m_y+radius/2<=80+(j+1)*24){       
                            changeX= true;
                            setVelocity();
                            a[i][j]=null;
                            GameManager.score+=10;
                            System.out.println("sağdan  "+GameManager.score);
                        }
                        ///// çarpmalarda border belirle!!!!
                   }
                }            
            }            
        if (m_x < 0) {                  // If at or beyond left side
            m_x= 0;                     // Place against edge and
            changeX=!changeX;
            setVelocity();           //set velocities again to not stop 
      /*
            if(duvar random bonusu){
                m_velocityX =(int) -(m_velocityX*(Math.random()+0.5)); // reverse x direction with random velocity.
                m_velocityY -= (int) (m_velocityY*(Math.random()+0.5));//  y direction with random velocity.
            }else
      */                 
        }if (m_x > m_rightBound) { // If at or beyond right side
            m_x         = m_rightBound;    // Place against right edge.
            changeX=!changeX;
            setVelocity();           //set velocities again to not stop            
              /*
            if(duvar random bonusu){
                m_velocityX =(int) -(m_velocityX*(Math.random()+0.5)); // reverse x direction with random velocity.
                m_velocityY -= (int) (m_velocityY*(Math.random()+0.5));//  y direction with random velocity.
            }else
      */             
        }
        if (m_y < 0) {                 // if we're at top
            m_y       = 0;
            changeY=!changeY;
            setVelocity();       //set velocities again to not stop 
               /*
            if(duvar random bonusu){
                m_velocityY = (int) -(m_velocityY*(Math.random()+0.5));// reverse y direction with random velocity.
                m_velocityX = (int) (m_velocityX*(Math.random()+0.5));  //  x direction with the random velocity.
            }else
      */            
         }
        if (m_y > 560) { // if we're at bottom
            
            System.out.println("yarraklara geldi aşkımız ikimizde ŞAŞKINIZ!!!");
            
        } 
             
        if(m_x+radius>=x && m_x<= x+st_len && m_y+radius>=510 && m_y<=506){                      
            if(changeY && m_x+radius/2>x && m_x+radius/2< x+st_len){                                  
                changeY= false;
                setVelocity();                            
            }
            else if(changeX && m_y+radius/2>=508 && m_y+radius/2<=513){
                changeX= false;
                setVelocity();
            }
            else if(!changeX && m_y+radius/2>=508 && m_y+radius/2<=513){       
                changeX= true;
                setVelocity();
            }
        }
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
            m_velocityX=-Math.abs(m_velocityX);
        if(!changeX)
            m_velocityX=Math.abs(m_velocityX);
        if(changeY)
            m_velocityY=Math.abs(m_velocityY);
        if(!changeY)
            m_velocityY=-Math.abs(m_velocityY);
    }
    public void setRadius(int x){
        radius=x;
    }
    public void setColor( Color newC){ // for fireball
        c=newC;
    }

}

    


import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.swing.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CTIS
 */
public class GameManager extends JPanel{
    
    public Stick s;
    public Ball b;
    Image img;
    //... Instance variables for the animiation
    private final int   m_interval  = 60;  // Milliseconds between updates.
    private final Timer m_timer;           // Timer fires to anmimate one step.

    

    public GameManager() {
        
        setPreferredSize(new Dimension(450, 600));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        img = Toolkit.getDefaultToolkit().createImage("background.png");        
        b=new Ball();
        s=new Stick();
        s.setBound(getWidth());
        setFocusable(true);
        m_timer = new Timer(m_interval, (ActionListener) new TimerAction() {});
        
        addMouseMotionListener(new MouseMotionAdapter(){
        @Override
            public void mouseMoved(MouseEvent ev){
                int temp= ev.getX()-(s.getLength()/2);
                s.setSX(temp);
            }});
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent ev){
                setAnimation(true);
                int temp= ev.getX()-(s.getLength()/2);
                s.setSX(temp);
            }           
        });
                
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {               
                System.out.println("tuşa bastık mı la?");
                if (e.getKeyCode()== KeyEvent.VK_RIGHT) 
                    s.moveRight();  
                if (e.getKeyCode()== KeyEvent.VK_LEFT) 
                    s.moveLeft();                   
                }
        });     
        
        
        
        
    }


    public void setAnimation(boolean turnOnOff) {
        if (turnOnOff) {
            m_timer.start();  // start animation by starting the timer.
        } else {
            m_timer.stop();   // stop timer
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Paint background, border
        g.drawImage(img, 0, 0, null);
        b.draw(g);
        s.drawS(g);
    }

    class TimerAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                b.setBounds(getWidth(),getHeight());
                b.move(s.getSX(),s.getLength());       // Move 
                repaint();      // Repaint indirectly calls paintComponent.
               
        }
    }

}

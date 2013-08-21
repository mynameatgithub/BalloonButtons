/*
 
 Author: Teresa Fanchiang 
 Date  : 03/11/13
 Program Name: BalloonButtons.java
 Objective: hills.ccsf.edu/~tfanchia/BalloonButtons.html
 
*/
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class BalloonButtons extends Applet 
             implements ActionListener
{
   final int SIZE = 50;
   final int MAX_INFLATE = 200;
   final int MAX_DEFLATE = 1;
   final int PLACE_CHANGE = 10;
   final int SIZE_CHANGE = 2;
   final int X_W_BORDER = 10;
   final int Y_N_BORDER = 75;
   int c, x, y, w, h;
   Button left,up,down,right,inflate,deflate;
//*********************************init()*********************************
   public void init()
   {
     Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
     setSize(new Dimension(d));
     Graphics g = getGraphics();
     c = 0;
     x = 665;  //Balloon's positioned in the middle
     y = 300;
     w = h = SIZE;
     setForeground(Color.black); //Color of labels
     left = new Button("LEFT");
     up = new Button("UP");
     down = new Button("DOWN");
     right = new Button("RIGHT");
     inflate = new Button("INFLATE");
     deflate = new Button("DEFLATE");
     add(left);add(up);add(down);add(right);
     add(inflate);add(deflate);
     left.addActionListener(this);
     up.addActionListener(this);
     down.addActionListener(this);
     right.addActionListener(this);
     inflate.addActionListener(this);
     deflate.addActionListener(this);
   }
//***************************actionPerformed()****************************
   public void actionPerformed(ActionEvent ae)
   {
     Dimension d = getSize();
     if(ae.getSource() == left) toLeft();
     else if(ae.getSource() == up) toUp();
     else if((ae.getSource() == down) 
            && ((y + h) < ((int)d.getHeight() - 20))) toDown();
     else if((ae.getSource() == right) 
            && ((x + w) < ((int)d.getWidth() - 10))) toRight();
     else if((ae.getSource() == inflate) 
            && ((y + h) < ((int)d.getHeight() - 20)) 
            && ((x + w) < ((int)d.getWidth() - 10)))
            grow();
/*These lines check for when the balloon coordinates
AND its size are less than the boundaries of the applet
to limit the growth to those boundaries.*/
     else if(ae.getSource() == deflate) shrink();
     else repaint();
  }
//********************************paint()*********************************
  public void paint(Graphics g)
  {
     g.setColor(Color.red);  //Color of balloon
     g.drawOval(x,y,w,h);
     g.fillOval(x,y,w,h); 
  }
//********************************toLeft()********************************
  public void toLeft()
  {
     if(x <= X_W_BORDER)c = 0;
     else
     {
        do
        {
           x -= PLACE_CHANGE;
           repaint();
           c = 0;
        }
        while(c != 0);
     }
  }
//*********************************toUp()*********************************
  public void toUp()
  {
     if(y <= Y_N_BORDER)c = 0;
     else 
     {
        do
        {
           y -= PLACE_CHANGE;
           repaint();
           c = 0;
        }
        while(c != 0);
     }
  }
//********************************toDown()********************************
  public void toDown()
  {
      do
      {
          y += PLACE_CHANGE;
          repaint();
          c = 0;
      }
      while(c != 0);        
  }
//*******************************toRight()********************************
  public void toRight()
  {
      do
      {
          x += PLACE_CHANGE;
          repaint();
          c = 0;
      }
      while(c != 0);
  } 
//*********************************grow()*********************************
  public void grow()
  {
      if((w <= MAX_INFLATE) && (h <= MAX_INFLATE))
      {
          do
          {
              w += SIZE_CHANGE; h += SIZE_CHANGE;
              repaint();
              c = 0;
          }
          while(c != 0); 
      }
      else c = 0;
  }
//********************************shrink()********************************
  public void shrink()
  {
     if((w > MAX_DEFLATE) && (h > MAX_DEFLATE))
     {
        do
        {
           w -= SIZE_CHANGE; h -= SIZE_CHANGE;
           repaint();
           c = 0;
        }
        while(c != 0);
     }
  }
}

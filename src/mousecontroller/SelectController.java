package mousecontroller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import gui.Canva;
import objects.BaseObject;
import tools.Singleton;

public class SelectController extends IMouseAdapter {
 
  private SelectController mode;

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    Point pressPoint = e.getPoint();
    mode = model.decideSelectMode(pressPoint);
    mode.pressedAction(e);
    canva.repaint();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    Point point = e.getPoint();
    mode.releasedAction(e);
    mode = null;
    canva.repaint();
  }


  public void mouseDragged(MouseEvent e) {
    Point point = e.getPoint();
    mode.draggedAction(e);
    canva.repaint();
  }
  
  @Override
  public void draw(Graphics g) {
    // TODO Auto-generated method stub
    super.draw(g);
    if(mode != null) {
      mode.drawSelectRec(g);
    }
  }
  
  protected void pressedAction(MouseEvent e) {}
  protected void releasedAction(MouseEvent e) {}
  protected void draggedAction(MouseEvent e) {}
  
  protected void drawSelectRec(Graphics g) {}
  

}

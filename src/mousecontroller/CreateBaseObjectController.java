package mousecontroller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import gui.ButtonList;
import gui.Canva;
import objects.BaseObject;
import objects.Class;
import objects.UseCase;
import tools.Factory;
import tools.Singleton;

public class CreateBaseObjectController extends IMouseAdapter{
  
  
  public CreateBaseObjectController(String s) {
    // TODO Auto-generated constructor stub
    super(s);
  }
  
  @Override
  public void mousePressed(MouseEvent e) {
    Point point = e.getPoint();
    model.createBaseObject(type, point);
    canva.repaint();
  }
  
  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

}

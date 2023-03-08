package mousecontroller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import objects.ConnectPort;
import objects.Lines;

public class CreateLinesController extends IMouseAdapter{
  
  private Lines line;
  
  public CreateLinesController(String s) {
    super(s);
    // TODO Auto-generated constructor stub
  }
  
  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    Point point = e.getPoint();
    ConnectPort port = model.findPort(point);
    if(port != null) {
      line = model.createLines(type, port);
      canva.repaint();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    Point point = e.getPoint();
    if(line != null) {
      ConnectPort port = model.findPort(point);
      line.setEndPort(port);
      line.setEndPoint(null);
      if(!model.isLineLegal(line)) {
        model.removeLines(line);
      }
      line = null;
    }
    canva.repaint();
  }
      
  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
    if(line != null) {
      line.setEndPoint(e.getPoint());
    }
    canva.repaint();
  }
}

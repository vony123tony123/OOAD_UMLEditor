package modes;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Main.Canva;
import objects.BaseObject;
import objects.ConnectPort;
import objects.Lines;

public class CreateLinesMode extends Mode{
  
  private ArrayList<Lines> lines;
  private Lines line;
  
  public CreateLinesMode(String s) {
    super(s);
    // TODO Auto-generated constructor stub
  }
  
  @Override
  public void setCanva(Canva canva) {
    // TODO Auto-generated method stub
    super.setCanva(canva);
    lines = canva.getLines();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    super.mousePressed(e);
    Point point = e.getPoint();
    for (BaseObject object : objects) {
      if (object.getRange().contains(point)) {
        ConnectPort port = object.judgePort(point);
        // draw line
        if(port != null) {
          line = Factory.getLines(type, port);
          line.setEndPoint(point);
          line.setDepth(canva.getDepthCount());
          canva.minusDepthCount(1);
          lines.add(line);
          canva.repaint();
          break;
        }
      }
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    super.mouseReleased(e);
    Point point = e.getPoint();
    Boolean isEndInObject = false;
    if(line != null) {
      for (BaseObject object : objects) {
        if (object.getRange().contains(point)) {
          ConnectPort port = object.judgePort(point);
          line.setEndPort(port);
          line.setEndPoint(null);
          isEndInObject = true;
          break;
        }
      }
      if(!isEndInObject || line.getEndPort() == line.getStartPort()||line.getEndPort()==null) {
        lines.remove(line);
      }
      line = null;
    }
    canva.repaint();
  }
      
  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
    super.mouseDragged(e);
    if(line !=null) {
      line.setEndPoint(e.getPoint());
    }
    canva.repaint();
  }
}

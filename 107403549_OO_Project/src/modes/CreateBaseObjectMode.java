package modes;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Main.Canva;
import Main.ModeList;
import objects.BaseObject;
import objects.Class;
import objects.UseCase;

public class CreateBaseObjectMode extends Mode{
  
  
  public CreateBaseObjectMode(String s) {
    // TODO Auto-generated constructor stub
    super(s);
  }
  
  @Override
  public void setCanva(Canva canva) {
    // TODO Auto-generated method stub
    super.setCanva(canva);
  }
  
  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    super.mouseClicked(e);
    Point point = e.getPoint();
    BaseObject object = Factory.getBaseObject(type, point);
    object.setName("Object");
    object.setDepth(canva.getDepthCount());
    canva.minusDepthCount(1);
    objects.add(object);
    canva.repaint();
  }

}

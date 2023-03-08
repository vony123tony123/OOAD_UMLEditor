package mousecontroller;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class MoveController extends SelectController {
  
  private Point previousPoint;

  public MoveController() {
    // TODO Auto-generated constructor stub
    super();
  }
  
  @Override
  public void pressedAction(MouseEvent e) {
    // TODO Auto-generated method stub
    previousPoint = e.getPoint();
  }
  
  @Override
  public void draggedAction(MouseEvent e) {
    // TODO Auto-generated method stub
    Point moved = model.calculateMove(previousPoint, e.getPoint());
    model.moveSelectedObject(moved);
    previousPoint = e.getPoint();
  }
  

}

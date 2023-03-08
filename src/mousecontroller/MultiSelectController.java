package mousecontroller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class MultiSelectController extends SelectController {
  
  private Rectangle selectGroupRectangle;
  private Point selectGroupRectangleStartPoint;
  
  public MultiSelectController() {
    // TODO Auto-generated constructor stub
    super();
  }
  
  @Override
  public void pressedAction(MouseEvent e) {
    // TODO Auto-generated method stub
    model.clearSelected();
    selectGroupRectangleStartPoint = e.getPoint();
    selectGroupRectangle = new Rectangle();
  }
  
  @Override
  public void draggedAction(MouseEvent e) {
    // TODO Auto-generated method stub
    model.calculateRectangleBound(selectGroupRectangle, selectGroupRectangleStartPoint, e.getPoint());
  }
  
  @Override
  public void releasedAction(MouseEvent e) {
    // TODO Auto-generated method stub
    if (!selectGroupRectangle.isEmpty()) {
      model.calculateRectangleBound(selectGroupRectangle, selectGroupRectangleStartPoint, e.getPoint());
      model.selectMultiObject(selectGroupRectangle);
    }
    selectGroupRectangle = null;
  }
  
  @Override
  protected void drawSelectRec(Graphics g) {
    // TODO Auto-generated method stub
    if (selectGroupRectangle != null && !selectGroupRectangle.isEmpty()) {
      Graphics2D g2d = (Graphics2D)g;
      g2d.draw(selectGroupRectangle);
    }
  }
  


}

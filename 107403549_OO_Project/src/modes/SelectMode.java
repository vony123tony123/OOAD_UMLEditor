package modes;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import Main.Canva;
import objects.BaseObject;

public class SelectMode extends Mode {

  private SelectGroupRect selectGroupRectangle;
  private ArrayList<BaseObject> selectedObjects;
  private enum SelectModes {SingleSelect, MultSelect, Moving};
  private SelectModes mode;
  private Point previousPoint;

  public SelectMode() {}

  @Override
  public void setCanva(Canva canva) {
    // TODO Auto-generated method stub
    super.setCanva(canva);
    selectGroupRectangle = canva.getSelectGroupRectangle();
    selectedObjects = canva.getSelectedObjects();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    Point pressPoint = e.getPoint();
    mode = decideMode(pressPoint);
    switch (mode) {
      case SingleSelect:
        canva.clearSelected();
        break;
        
      case MultSelect:
        canva.clearSelected();
        selectGroupRectangle.setStartPoint(pressPoint);
        break;
        
      case Moving:
        previousPoint = pressPoint;
        break;
    }
    canva.repaint();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    super.mouseReleased(e);
    Point point = e.getPoint();
    
    switch (mode) {
      case SingleSelect:
        for (BaseObject object : objects) {
          if (object.getRange().contains(e.getPoint())) {
            object.setSelect(true);
            selectedObjects.add(object);
            break;
          }
        }
        break;

      case MultSelect:
        if (!selectGroupRectangle.isEmpty()) {
          for (BaseObject object : objects) {
            selectGroupRectangle.setBounds(point);
            if (selectGroupRectangle.intersects(object.getRange())) {
              object.setSelect(true);
              selectedObjects.add(object);
            }
          }
        }
        selectGroupRectangle.setBounds(0, 0, 0, 0);
        selectGroupRectangle.setDrawing(false);
        break;
    }
    mode = null;
    canva.repaint();
  }


  public void mouseDragged(MouseEvent e) {
    super.mouseDragged(e);
    Point point = e.getPoint();
    switch (mode) {
      case SingleSelect:
        
        break;

      case MultSelect:
        selectGroupRectangle.setBounds(point);
        break;
        
      case Moving:
        Point moved = calculateMove(previousPoint, point);
        previousPoint = point;
        for(BaseObject object : selectedObjects) {
          object.move(moved.x, moved.y);
        }
        break;
    }
    canva.repaint();
  };

  //計算移動距離
  private Point calculateMove(Point original, Point movedPoint) {
    int moveX = movedPoint.x - original.x;
    int moveY = movedPoint.y - original.y;
    return new Point(moveX, moveY);
  }
  
  //判斷mode
  //point在物件中但物件沒有被選擇= SingleSelect
  //point不在任何物件中 = MultSelect
  //point在物件中且物件已被選 = Moving
  private SelectModes decideMode(Point point) {
    boolean isInObject = false;
    for (BaseObject object : objects) {
      if (object.getRange().contains(point)) {
        if (object.isSelected()) {
          return SelectModes.Moving;
        }
        isInObject = true;
        break;
      }
    }
    if(isInObject == true && mode != SelectModes.Moving) {
      return SelectModes.SingleSelect;
    }else if(isInObject == false) {
      return SelectModes.MultSelect;
    }
    return null;
  }

}

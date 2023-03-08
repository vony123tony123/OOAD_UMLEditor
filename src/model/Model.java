package model;


import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import mousecontroller.MoveController;
import mousecontroller.MultiSelectController;
import mousecontroller.SelectController;
import mousecontroller.SingleSelectController;
import objects.BaseObject;
import objects.ConnectPort;
import objects.Lines;
import objects.Object;
import tools.Factory;
import tools.Singleton;

public class Model {
  
  private ArrayList<Lines> lines;
  private ArrayList<BaseObject> baseObjects;
  private Comparator<Object> comparator;

  public Model() {
    // TODO Auto-generated constructor stub
    baseObjects = Singleton.getBaseObjects();
    lines = Singleton.getLines();
    comparator = Singleton.getComparator();
  }
  
  public ArrayList<Object> sortAllObjects() {
    ArrayList<Object> list = new ArrayList<Object>();
    list.addAll(baseObjects);
    list.addAll(lines);
    list.sort(comparator.reversed());
    baseObjects.sort(comparator);
    lines.sort(comparator);
    return list;
  }
  
  public BaseObject createBaseObject(String type, Point p) {
    BaseObject object = Factory.getBaseObject(type, p);
    object.setName("Object");
    object.setDepth(Singleton.getDeepcount());
    Singleton.Deepcountminusone();
    baseObjects.add(object);
    return object;
  }
  
  public ConnectPort findPort(Point point) {
    ConnectPort port = null;
    for (BaseObject object : baseObjects) {
      if (object.getRange().contains(point)) {
        port = object.judgePort(point);
        break;
      }
    }
    return port;
  }
  
  public BaseObject findBaseObject(Point point) {
    for (BaseObject object : baseObjects) {
      if (object.getRange().contains(point)) {
          return object;
        }
      }
    return null;
  }
  
  public Lines createLines(String type, ConnectPort startport) {
    Lines line = Factory.getLines(type, startport);
    line.setEndPoint(startport.getCentralPoint());
    line.setDepth(Singleton.getDeepcount());
    Singleton.Deepcountminusone();
    lines.add(line);
    return line;
  }
  
  public void setLinesEndPoint(Lines lines, Point point) {
    lines.setEndPoint(point);
  }
  
  public void setLinesEndPort(Lines lines, ConnectPort port) {
    lines.setEndPort(port);
  }
  
  public boolean isLineLegal(Lines line) {
    if(line.getEndPort() == null ||line.getStartPort() == line.getEndPort()) 
      return false;
    return true;
  }
  
  public void removeLines(Lines line) {
    lines.remove(line);
  }
  
  public void clearSelected() {
    for(BaseObject baseObject: baseObjects) {
      baseObject.setSelect(false);
    }
  }
  
  public void calculateRectangleBound(Rectangle r, Point startPoint, Point point) {
    r.setBounds(point.x<startPoint.x? point.x:startPoint.x,
        point.y<startPoint.y? point.y:startPoint.y,
            Math.abs(point.x-startPoint.x), 
            Math.abs(point.y-startPoint.y));
  }
  
  public void selectMultiObject(Rectangle r) {
    for (BaseObject object : baseObjects) {
      if (r.intersects(object.getRange())) {
        object.setSelect(true);
      }
    }
  }
  
  public Point calculateMove(Point original, Point movedPoint) {
    int moveX = movedPoint.x - original.x;
    int moveY = movedPoint.y - original.y;
    return new Point(moveX, moveY);
  }
  
  public void moveSelectedObject(Point moved) {
    for(BaseObject object : baseObjects) {
      if(object.isSelected()) {
        object.move(moved.x, moved.y);
      }
    }
  }
  
  //decide_mode
  //point在unselected Object中= SingleSelect
  //point不在object中 = MultSelect
  //point在selected Object中 = Moving
  public SelectController decideSelectMode(Point point) {
    boolean isInObject = false;
    for (BaseObject object : baseObjects) {
      if (object.getRange().contains(point)) {
        if (object.isSelected()) {
          return new MoveController();
        }
        isInObject = true;
        break;
      }
    }
    if(isInObject == true) {
      return new SingleSelectController();
    }else if(isInObject == false) {
      return new MultiSelectController();
    }
    return null;
  }
  

}

package objects;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Comparator;

public class Group extends BaseObject {
  protected ArrayList<BaseObject> objects;
  protected Comparator<UMLMark> comparator;

  public Group(ArrayList<BaseObject> objectlists, Comparator<UMLMark> comparator) {
    // TODO Auto-generated constructor stub
    this.comparator = comparator;
    objects = objectlists;
    objects.sort(comparator);
    range = new Rectangle(0,0,-1,-1);
    calculateRectangle();
  }
  
  public void setDepth() {
    // TODO Auto-generated method stub
    int sum = 0;
    for(BaseObject object: objects) {
      sum+=object.getDepth();
    }
    depth = sum/objects.size();
  }
  
  public ArrayList<BaseObject> getObjects() {
    return objects;
  }

  private void calculateRectangle() {
    for (BaseObject object : objects) {
      range = range.union(object.getRange());
    }
  }
  
  @Override
  public void move(int x, int y) {
    // TODO Auto-generated method stub
    range.setLocation(range.x + x, range.y + y);
    for(BaseObject object : objects) {
      object.move(x, y);
    }
  }

  @Override
  public void draw(Graphics graphic) {
    // TODO Auto-generated method stub
    objects.sort(comparator.reversed());
    for (BaseObject object : objects) {
      object.draw(graphic);
    }
    if(isSelected) {
      Graphics2D g2d = (Graphics2D) graphic;
      g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
          0, new float[]{9}, 0));
      graphic.drawRect(range.x, range.y, range.width, range.height);
      g2d.setStroke(new BasicStroke());
    }
    objects.sort(comparator);
  }

}

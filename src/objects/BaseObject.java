package objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class BaseObject extends Object {
  protected int height;
  protected int width;
  protected boolean isSelected = false;
  protected ConnectPort connectPorts[];
  protected Polygon portsRange[];
  protected Rectangle range;
  protected String name;
  
  public BaseObject() {
    // TODO Auto-generated constructor stub;
  }
  
  public void move(int x, int y) {
    range.setLocation(range.x + x, range.y + y);
    setPorts();
  }
  

  public void setPorts() {
    int halfPortSize = ConnectPort.getSize()/2;
    int halfWidth = width / 2;
    int halfHeight = height/2;
    if(connectPorts!=null) {
      connectPorts[0].resetCentralPoint(range.x + halfWidth,range.y - halfPortSize);
      connectPorts[1].resetCentralPoint(range.x + width + halfPortSize, range.y + halfHeight);
      connectPorts[2].resetCentralPoint(range.x + halfWidth,range.y + height + halfPortSize);
      connectPorts[3].resetCentralPoint(range.x - halfPortSize, range.y + halfHeight);
    }else {
      connectPorts = new ConnectPort[] {
          new ConnectPort(range.x + halfWidth,range.y - halfPortSize),
          new ConnectPort(range.x + width + halfPortSize, range.y + halfHeight),
          new ConnectPort(range.x + halfWidth,range.y + height + halfPortSize),
          new ConnectPort(range.x - halfPortSize, range.y + halfHeight) };
    }
    portsRange = new Polygon[] {
        new Polygon(new int[] {range.x, range.x+width, range.x+halfWidth},
            new int[] {range.y, range.y, range.y+halfHeight}, 3),
        new Polygon(new int[] {range.x+width, range.x+width, range.x+halfWidth},
            new int[] {range.y, range.y+height, range.y+halfHeight}, 3),
        new Polygon(new int[] {range.x+width, range.x, range.x+halfWidth},
            new int[] {range.y+height, range.y+height, range.y+halfHeight}, 3),
        new Polygon(new int[] {range.x, range.x, range.x+halfWidth},
            new int[] {range.y+height, range.y, range.y+halfHeight}, 3)
    };
  }
  
  public ConnectPort judgePort(Point point) {
    if(portsRange != null) {
      for(int i=0; i<portsRange.length;i++) {
        if(portsRange[i].contains(point))
          return connectPorts[i];
      }
      System.err.println("Pressing point didn't find any range in this object");
    }
    return null;
  }

  @Override
  public void draw(Graphics g) {
    if (isSelected) {
      for (ConnectPort cp : connectPorts) {
        cp.draw(g);
      }
    }
  }
  
  public void setSelect(boolean bool) {
    isSelected = bool;
  }
  
  public int getHeight() {
    return height;
  }
  
  public int getWidth() {
    return width;
  }
  
  public Rectangle getRange() {
    return range;
  }
  
  public boolean isSelected() {
    return isSelected;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public void calculateDepth() {}
  
  public ArrayList<BaseObject> getObjects(){ return null; }
  
}

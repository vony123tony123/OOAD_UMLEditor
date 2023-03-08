package objects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class UMLMark {
  
  protected int depth;

  public UMLMark() {
    // TODO Auto-generated constructor stub
  }
  
  public abstract void draw(Graphics graphic);
  
  public void setDepth(int depth) {
    this.depth = depth;
  }
  
  public int getDepth() {
    return depth;
  }

}

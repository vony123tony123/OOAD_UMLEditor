package modes;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class SelectGroupRect extends Rectangle{
  
  Point startPoint;
  boolean isDrawing = false;

  public SelectGroupRect(int x, int y, int width, int height) {
    // TODO Auto-generated constructor stub
    super(x, y, width, height);
    startPoint = new Point(0,0);
  }
  
  public void setBounds(Point point) {
    // TODO Auto-generated method stub
    super.setBounds(point.x<startPoint.x? point.x:startPoint.x,
        point.y<startPoint.y? point.y:startPoint.y,
        Math.abs(point.x-startPoint.x), 
        Math.abs(point.y-startPoint.y));
  }
  
  public void setStartPoint(Point startPoint) {
    this.startPoint = startPoint;
  }
  
  public Point getStartPoint() {
    return startPoint;
  }

  /**
   * @param isDrawing the isDrawing to set
   */
  public void setDrawing(boolean isDrawing) {
    this.isDrawing = isDrawing;
  }
  
  public boolean isDrawing() {
    return isDrawing;
  }

  public void draw(Graphics g) {
    g.drawRect(x, y, width, height);
  }

}

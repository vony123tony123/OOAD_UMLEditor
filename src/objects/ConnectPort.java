package objects;

import java.awt.Graphics;
import java.awt.Point;

public class ConnectPort {
  private int central_x;
  private int central_y;
  private static int size=10;
  
  public ConnectPort(int x, int y) {
    // TODO Auto-generated constructor stub
    central_x = x;
    central_y = y;
  }
  
  public void draw(Graphics g) {
    g.fillRect(central_x-size/2, central_y-size/2, size, size);
  }
  
  public static int getSize() {
    return size;
  }
  
  /**
   * @param size the size to set
   */
  public static void setSize(int size) {
    ConnectPort.size = size;
  }

  public Point getCentralPoint() {
    return new Point(central_x,central_y);
  }
  
  public void resetCentralPoint(int x, int y) {
    central_x = x;
    central_y = y;
  }

}

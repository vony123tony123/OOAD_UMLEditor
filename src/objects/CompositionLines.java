package objects;

import java.awt.Color;
import java.awt.Graphics;

public class CompositionLines extends Lines {

  public CompositionLines(ConnectPort port) {
    super(port);
    // TODO Auto-generated constructor stub
  }
  @Override
  public void draw(Graphics graphic) {
    // TODO Auto-generated method stub
    int x1 = startPort.getCentralPoint().x;
    int y1 = startPort.getCentralPoint().y;
    int x2;
    int y2;
    if(endPort != null) {
      x2 = endPort.getCentralPoint().x;
      y2 = endPort.getCentralPoint().y;
    }
    else {
      x2 = endPoint.x;
      y2 = endPoint.y;
    }
    int width = 15;
    int height = 10;
    int distanceX = x2 - x1, distanceY = y2 - y1;
    double D = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
    if(D == 0.0) {
      D = 1;
    }
    double x3 = D - width, x4 = x3, y3 = height, y4 = -height, x5 = D-width*2, y5, x;
    double sin = distanceY / D, cos = distanceX / D;

    x = x3*cos - y3*sin + x1;
    y3 = x3*sin + y3*cos + y1;
    x3 = x;

    x = x4*cos - y4*sin + x1;
    y4 = x4*sin + y4*cos + y1;
    x4 = x;
    
    x = x5*cos + x1;
    y5 = x5*sin + y1;
    x5 = x;
    
    int[] xpoints = {x2, (int)x3, (int)x5, (int)x4};
    int[] ypoints = {y2, (int)y3, (int)y5, (int)y4};
    graphic.setColor(Color.white);
    graphic.fillPolygon(xpoints,ypoints,xpoints.length);
    graphic.setColor(Color.black);
    graphic.drawPolygon(xpoints,ypoints,xpoints.length);
    graphic.drawLine(x1, y1, (int)x5, (int)y5);
    
  }

}

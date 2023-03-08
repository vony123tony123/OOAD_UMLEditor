package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GeneralizationLines extends Lines {

  public GeneralizationLines(ConnectPort port) {
    super(port);
    // TODO Auto-generated constructor stub
  }
  
  @Override
  public void draw(Graphics graphic) {
    // TODO Auto-generated method stub
    Graphics2D g = (Graphics2D) graphic;
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
    double xm = D - width, xn = xm, ym = height, yn = -height, x;
    double sin = distanceY / D, cos = distanceX / D;

    x = xm*cos - ym*sin + x1;
    ym = xm*sin + ym*cos + y1;
    xm = x;

    x = xn*cos - yn*sin + x1;
    yn = xn*sin + yn*cos + y1;
    xn = x;

    int[] xpoints = {x2, (int) xm, (int) xn};
    int[] ypoints = {y2, (int) ym, (int) yn};

    g.drawLine(x1, y1, x2, y2);
    g.setColor(Color.white);
    g.fillPolygon(xpoints, ypoints, 3);
    g.setColor(Color.black);
    g.drawPolygon(xpoints, ypoints, 3);
  }

}

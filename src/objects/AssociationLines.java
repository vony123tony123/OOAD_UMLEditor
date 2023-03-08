package objects;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class AssociationLines extends Lines {

  public AssociationLines(ConnectPort port) {
    super(port);
    // TODO Auto-generated constructor stub
  }
  
  @Override
  public void draw(Graphics graphic) {
    // TODO Auto-generated method stub
    Graphics2D g = (Graphics2D)graphic;
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
    int d = 15;
    int h = 10;
    int dx = x2 - x1, dy = y2 - y1;
    double D = Math.sqrt(dx*dx + dy*dy);
    if(D == 0.0) {
      D = 1;
    }
    double xm = D - d, xn = xm, ym = h, yn = -h, x;
    double sin = dy / D, cos = dx / D;

    x = xm*cos - ym*sin + x1;
    ym = xm*sin + ym*cos + y1;
    xm = x;

    x = xn*cos - yn*sin + x1;
    yn = xn*sin + yn*cos + y1;
    xn = x;

    g.drawLine(x1, y1, x2, y2);
    g.drawLine(x2, y2, (int)xm, (int)ym);
    g.drawLine(x2, y2, (int)xn, (int)yn);
  }

}

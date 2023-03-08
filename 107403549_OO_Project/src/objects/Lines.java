package objects;

import java.awt.Point;

public abstract class Lines extends UMLMark {
  
  protected ConnectPort startPort;
  protected ConnectPort endPort;
  protected Point endPoint;

  public Lines(ConnectPort port) {
    // TODO Auto-generated constructor stub
    startPort = port;
  }
  
  public void setEndPort(ConnectPort port) {
    endPort = port;
  }
  
  public void setEndPoint(Point point) {
    endPoint = point;
  }
  
  public ConnectPort getEndPort() {
    return endPort;
  }
  
  public ConnectPort getStartPort() {
    return startPort;
  }
  
  

}

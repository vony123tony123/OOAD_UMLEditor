package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class UseCase extends BaseObject {

  public UseCase(Point point) {
    width = 100;
    height = 70;
    range = new Rectangle(point.x, point.y, width, height);
    setPorts();
  }

  @Override
  public void draw(Graphics graphic) {
    // TODO Auto-generated method stub
    super.draw(graphic);
    graphic.setColor(Color.white);
    graphic.fillOval(range.x, range.y, width, height);
    graphic.setColor(Color.black);
    graphic.drawOval(range.x, range.y, width, height);
    graphic.drawString(name, range.x+width/3, range.y+height/2);
  }

}

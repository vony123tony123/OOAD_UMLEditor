package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Class extends BaseObject {

  public Class(Point point) {
    // TODO Auto-generated constructor stub
    height = 150;
    width = 120;
    range = new Rectangle(point.x, point.y, width, height);
    setPorts();
  }

  @Override
  public void draw(Graphics graphic) {
    // TODO Auto-generated method stub
    super.draw(graphic);
    graphic.setColor(Color.white);
    graphic.fillRect(range.x, range.y, width, height);
    graphic.setColor(Color.black);
    graphic.drawRect(range.x, range.y, width, height);
    graphic.drawLine(range.x, range.y + height / 3, range.x + width,
        range.y + height / 3);
    graphic.drawLine(range.x, range.y + (height / 3) * 2, range.x + width,
        range.y + (height / 3) * 2);
    graphic.drawString(name, range.x+width/3, range.y+height/6);
  }

}

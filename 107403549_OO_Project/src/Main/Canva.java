package Main;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JPanel;
import modes.SelectGroupRect;
import objects.BaseObject;
import objects.Group;
import objects.Lines;
import objects.UMLMark;

public class Canva extends JPanel {

  private ArrayList<BaseObject> objects;
  private ArrayList<Lines> lines;
  private ArrayList<Group> groups;
  private ArrayList<BaseObject> selectedObjects;
  private SelectGroupRect selectGroupRectangle;
  private int depthCount = 99;
  private Comparator<UMLMark> comparator= new Comparator<UMLMark>() {
    @Override
    public int compare(UMLMark o1, UMLMark o2) {
      // TODO Auto-generated method stub
      if(o1.getDepth() < o2.getDepth()) {
       return -1;
      }
      else if (o1.getDepth() == o2.getDepth()) {
        return 0;
      }else {
        return 1;
      }
    }
  };
  
  public Canva() {
    // TODO Auto-generated constructor stub
    setBackground(Color.white);
    objects = new ArrayList<BaseObject>();
    lines = new ArrayList<Lines>();
    groups = new ArrayList<Group>();
    selectedObjects = new ArrayList<BaseObject>();
    selectGroupRectangle = new SelectGroupRect(0,0,0,0);
  }
  
  public Comparator<UMLMark> getComparator() {
    return comparator;
  }
  
  
  /**
   * @return the objects
   */
  public ArrayList<BaseObject> getObjects() {
    return objects;
  }


  /**
   * @return the lines
   */
  public ArrayList<Lines> getLines() {
    return lines;
  }



  /**
   * @return the groups
   */
  public ArrayList<Group> getGroups() {
    return groups;
  }



  /**
   * @return the selectedObjects
   */
  public ArrayList<BaseObject> getSelectedObjects() {
    return selectedObjects;
  }



  /**
   * @return the selectGroupRectangle
   */
  public SelectGroupRect getSelectGroupRectangle() {
    return selectGroupRectangle;
  }
  
  /**
   * @return the depthCount
   */
  public int getDepthCount() {
    return depthCount;
  }


  public void minusDepthCount(int num) {
    this.depthCount -= num;
  }
  
  //取消所有選取物件
  public void clearSelected() {
    for (BaseObject object : selectedObjects) {
      object.setSelect(false);
    }
    selectedObjects.clear();
    repaint();
  }


  @Override
  public void paint(Graphics g) {
    // TODO Auto-generated method stub
    super.paint(g);
    g.setColor(Color.black);
    ArrayList<UMLMark> list = new ArrayList<UMLMark>();
    list.addAll(objects);
    list.addAll(lines);
    list.sort(comparator.reversed());
    for(UMLMark mark: list) {
      mark.draw(g);
    }
    if (!selectGroupRectangle.isEmpty()) {
      selectGroupRectangle.draw(g);
    }
    objects.sort(comparator);
    lines.sort(comparator);
    selectedObjects.sort(comparator);
  }
}

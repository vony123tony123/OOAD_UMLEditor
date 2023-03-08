package mousecontroller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import gui.Canva;
import model.Model;
import objects.BaseObject;
import objects.Object;
import tools.Singleton;

public abstract class IMouseAdapter extends MouseAdapter{
  
  protected Canva canva;
  protected String type;
  protected Model model;
  
  public IMouseAdapter() {
    model = new Model();
  }

  public IMouseAdapter(String s) {
    // TODO Auto-generated constructor stub
    this();
    type = s;
  }
  
  public void setCanva(Canva canva) {
    this.canva = canva;
    if(canva.getMouseListeners().length != 0) {
      canva.removeMouseListener(canva.getMouseListeners()[0]);
      canva.removeMouseMotionListener(canva.getMouseMotionListeners()[0]);
    }
    canva.addMouseListener(this);
    canva.addMouseMotionListener(this);
  }
  
  public void draw(Graphics g) {
    Comparator<Object> comparator = Singleton.getComparator();
    g.setColor(Color.black);
    ArrayList<Object> list = model.sortAllObjects();
    for(Object mark: list) {
      mark.draw(g);
    }
  }
  
  public void clearSelected() {
    model.clearSelected();
  }
  
  public ArrayList<BaseObject> getSelectedObjects() { return null;}
  
  @Override
  public abstract void mousePressed(MouseEvent e);
  public abstract void mouseDragged(MouseEvent e);
  public abstract void mouseReleased(MouseEvent e);
}

package modes;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import Main.Canva;
import objects.BaseObject;

public class Mode extends MouseAdapter{
  
  protected Canva canva;
  protected ArrayList<BaseObject> objects;
  protected String type;
  
  public Mode() {}

  public Mode(String s) {
    // TODO Auto-generated constructor stub
    type = s;
  }
  
  public void setCanva(Canva canva) {
    this.canva = canva;
    objects = canva.getObjects();
  }

}

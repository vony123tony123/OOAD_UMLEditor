package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import gui.Canva;
import objects.BaseObject;
import objects.Group;
import tools.Singleton;

public class UnGroupBtnController implements ActionListener{
  
  Canva canva;

  public UnGroupBtnController(Canva canva) {
    // TODO Auto-generated constructor stub
    this.canva = canva;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    ArrayList<BaseObject> objects = Singleton.getBaseObjects();
    int objectsNum = objects.size();
    for(int i=0; i<=objectsNum;i++) {
      BaseObject object = objects.get(i);
      if(object.isSelected() && object.getObjects()!=null) {
        objects.addAll(object.getObjects());
        objects.remove(object);
      }
    }
    canva.repaint();
  }

}

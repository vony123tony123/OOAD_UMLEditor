package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import gui.Canva;
import objects.BaseObject;
import objects.Group;
import objects.Lines;
import tools.Singleton;

public class GroupBtnController  implements ActionListener{
  
  Canva canva;

  public GroupBtnController(Canva canva) {
    // TODO Auto-generated constructor stub
    this.canva = canva;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    ArrayList<BaseObject> groupObjects = new ArrayList<BaseObject>();
    ArrayList<BaseObject> objects = Singleton.getBaseObjects();
    ArrayList<Lines> lines = Singleton.getLines();
    for(BaseObject object : objects) {
      if(object.isSelected()) {
        groupObjects.add(object);
        object.setSelect(false);
      }
    }
    if(groupObjects.size() > 1) {
      Group group = new Group(groupObjects);
      group.calculateDepth();
      objects.removeAll(groupObjects);
      objects.add(group);
    }else if(groupObjects.size() == 1) {
      groupObjects.get(0).setSelect(true);
    }
    canva.repaint();
  }

}

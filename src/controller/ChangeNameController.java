package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import gui.Canva;
import objects.BaseObject;
import tools.Singleton;

public class ChangeNameController implements ActionListener{
  
  private Canva canva;

  public ChangeNameController(Canva canva) {
    // TODO Auto-generated constructor stub
    this.canva = canva;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    for(BaseObject object: Singleton.getBaseObjects()) {
      if(object.isSelected()) {
        String string = JOptionPane.showInputDialog("New Object name: ");
        if(string != null) {
          object.setName(string);
        }
      }
    }
    canva.repaint();
  }

}

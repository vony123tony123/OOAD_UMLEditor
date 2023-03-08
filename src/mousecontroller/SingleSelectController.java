package mousecontroller;

import java.awt.event.MouseEvent;
import objects.BaseObject;

public class SingleSelectController extends SelectController {
  
  @Override
  public void pressedAction(MouseEvent e) {
    // TODO Auto-generated method stub
    model.clearSelected();
  }
  
  @Override
  public void releasedAction(MouseEvent e) {
    // TODO Auto-generated method stub
    BaseObject baseObject = model.findBaseObject(e.getPoint());
    if(baseObject!=null) baseObject.setSelect(true);
  }

}

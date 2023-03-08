package gui;

import javax.swing.ImageIcon;
import mousecontroller.CreateBaseObjectController;
import mousecontroller.CreateLinesController;
import mousecontroller.IMouseAdapter;
import mousecontroller.SelectController;

public enum ButtonList {
  SELECT(new SelectController(), new ImageIcon(".\\img\\Select.png")),
  ASSOCIATE(new CreateLinesController("Association"), new ImageIcon(".\\img\\Association.png")),
  GENERALIZATION(new CreateLinesController("Generalization"), new ImageIcon(".\\img\\Generalization.png")),
  COMPOSITION(new CreateLinesController("Composition"), new ImageIcon(".\\img\\Composition.png")),
  CLASS(new CreateBaseObjectController("Class"), new ImageIcon(".\\img\\Class.png")),
  USECASE(new CreateBaseObjectController("UseCase"), new ImageIcon(".\\img\\UseCase.png"));
  
  private IMouseAdapter mode;
  private ImageIcon imageIcon;
  
  private ButtonList(IMouseAdapter adapter, ImageIcon icon) {
    // TODO Auto-generated constructor stub
    mode = adapter;
    imageIcon = icon;
  }
  
  public IMouseAdapter getMode() {
    return mode;
  }
  
  public ImageIcon getImageIcon() {
    return imageIcon;
  }
  
}

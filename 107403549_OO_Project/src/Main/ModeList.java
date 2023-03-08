package Main;

import javax.swing.ImageIcon;
import modes.CreateBaseObjectMode;
import modes.CreateLinesMode;
import modes.Mode;
import modes.SelectMode;

public enum ModeList {
  SELECT(new SelectMode(), new ImageIcon(".\\img\\Select.png")),
  ASSOCIATE(new CreateLinesMode("Association"), new ImageIcon(".\\img\\Association.png")),
  GENERALIZATION(new CreateLinesMode("Generalization"), new ImageIcon(".\\img\\Generalization.png")),
  COMPOSITION(new CreateLinesMode("Composition"), new ImageIcon(".\\img\\Composition.png")),
  CLASS(new CreateBaseObjectMode("Class"), new ImageIcon(".\\img\\Class.png")),
  USECASE(new CreateBaseObjectMode("UseCase"), new ImageIcon(".\\img\\UseCase.png"));
  
  private Mode mouseAdapter;
  private ImageIcon imageIcon;
  
  private ModeList(Mode adapter, ImageIcon icon) {
    // TODO Auto-generated constructor stub
    mouseAdapter = adapter;
    imageIcon = icon;
  }
  
  public Mode getMouseAdapter() {
    return mouseAdapter;
  }
  
  public ImageIcon getImageIcon() {
    return imageIcon;
  }
  
}

package modes;

import java.awt.Point;
import objects.AssociationLines;
import objects.BaseObject;
import objects.Class;
import objects.CompositionLines;
import objects.ConnectPort;
import objects.GeneralizationLines;
import objects.Lines;
import objects.UseCase;

public class Factory {
  
  public static BaseObject getBaseObject(String s, Point point) {
    switch (s) {
      case "Class":
        return new Class(point);
      case "UseCase":
        return new UseCase(point);
      default:
        System.err.println("Factory Class Error: undefine String " + s);
        break;
    }
    return null;
  }
  
  public static Lines getLines(String s, ConnectPort connectPort) {
    switch (s) {
      case "Association":
        return new AssociationLines(connectPort);
      case "Composition":
        return new CompositionLines(connectPort);
      case "Generalization":
        return new GeneralizationLines(connectPort);
      default:
        System.err.println("Factory Class Error: undefine String " + s);
        break;
    }
    return null;
  }

}

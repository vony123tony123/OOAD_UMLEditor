package tools;

import java.util.ArrayList;
import java.util.Comparator;
import objects.BaseObject;
import objects.Lines;
import objects.Object;

public class Singleton {
  
  private static ArrayList<BaseObject> baseObjects;
  private static ArrayList<Lines> lines;
  private static int deepcount = 0;
  private static  Comparator<Object> comparator;

  private Singleton() {
    // TODO Auto-generated constructor stub
  }
  
  public static ArrayList<Lines> getLines() {
    if(lines == null) lines = new ArrayList<Lines>();
    return lines;
  }
  
  public static ArrayList<BaseObject> getBaseObjects(){
    if(baseObjects == null) baseObjects = new ArrayList<BaseObject>();
    return baseObjects;
  }
  
  public static int getDeepcount() {
    return deepcount;
  }
  
  public static void Deepcountminusone() {
    deepcount++;
  }
  
  public static Comparator<Object> getComparator() {
    if(comparator == null) {
      comparator = new Comparator<Object>() {
        @Override
        public int compare(Object o1, Object o2) {
          // TODO Auto-generated method stub
          if(o1.getDepth() < o2.getDepth()) {
           return 1;
          }
          else if (o1.getDepth() == o2.getDepth()) {
            return 0;
          }else {
            return -1;
          }
        }
      };
    }
    return comparator;
  }

}

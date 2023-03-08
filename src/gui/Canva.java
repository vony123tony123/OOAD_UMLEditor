package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import mousecontroller.IMouseAdapter;

public class Canva extends JPanel {
  
  IMouseAdapter mode;
  
  public Canva() {
    // TODO Auto-generated constructor stub
    setBackground(Color.white);
  }
  
  public synchronized void addMouseListener(IMouseAdapter l) {
    // TODO Auto-generated method stub
    super.addMouseListener(l);
    mode = l;
  }

  @Override
  public void paint(Graphics g) {
    // TODO Auto-generated method stub
    super.paint(g);
    mode.draw(g);
  }
}

package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modes.Mode;
import objects.BaseObject;
import objects.Group;

public class Windows {

  private JFrame frame;
  private ArrayList<JButton> buttons;
  private Canva canva;
  private Mode mode;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Windows window = new Windows();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public Windows() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {

    buttons = new ArrayList<JButton>();

    frame = new JFrame();
    frame.setBounds(100, 100, 1138, 740);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    canva = new Canva();
    frame.add(canva, BorderLayout.CENTER);

    JPanel editBarPanel = new JPanel();
    frame.getContentPane().add(editBarPanel, BorderLayout.WEST);
    editBarPanel.setLayout(new GridLayout(0, 1, 0, 0));

    for (int i = 0; i < ModeList.values().length ; i++) {
      JButton button = new JButton(ModeList.values()[i].getImageIcon());
      button.setBackground(Color.white);
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // TODO Auto-generated method stub
          ChangeMode((JButton) e.getSource());
        }
      });
      if (i == 0) {
        button.setSelected(true);
        button.setBackground(Color.DARK_GRAY);
        mode = ModeList.SELECT.getMouseAdapter();
        mode.setCanva(canva);
        canva.addMouseListener(mode);
        canva.addMouseMotionListener(mode);
      }
      editBarPanel.add(button);
      buttons.add(button);
    }

    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);

    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);

    JMenu editMenu = new JMenu("Edit");
    menuBar.add(editMenu);

    JMenuItem changeObjectNameMenuItem = new JMenuItem("Change object name");
    changeObjectNameMenuItem.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(canva.getSelectedObjects().size() == 1) {
          String string = JOptionPane.showInputDialog("New Object name: ");
          if(string != null) {
            canva.getSelectedObjects().get(0).setName(string);
          }
        }
        canva.repaint();
      }
    });
    editMenu.add(changeObjectNameMenuItem);

    JMenuItem groupMenuItem = new JMenuItem("Group");
    groupMenuItem.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        ArrayList<BaseObject> arrayList = new ArrayList<BaseObject>();
        for(BaseObject object : canva.getObjects()) {
          if(object.isSelected()) {
            arrayList.add(object);
            object.setSelect(false);
          }
        }
        if(arrayList.size() > 1) {
          Group group = new Group(arrayList,canva.getComparator());
          group.setDepth();
          canva.getObjects().removeAll(arrayList);
          canva.getObjects().add(group);
          canva.getGroups().add(group);
        }else if(arrayList.size() == 1) {
          arrayList.get(0).setSelect(true);
        }
        canva.repaint();
      }
    });
    editMenu.add(groupMenuItem);

    JMenuItem ungroupMenuItem = new JMenuItem("Ungroup");
    ungroupMenuItem.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        int selectNum = 0;
        ArrayList<BaseObject> objects = null;
        Group selectedGroup = null;
        for(Group group : canva.getGroups()) {
          if(group.isSelected()) {
            objects = group.getObjects();
            selectedGroup = group;
            selectNum++;
          }
        }
        if(selectNum == 1) {
          canva.getObjects().remove(selectedGroup);
          canva.getObjects().addAll(objects);
          canva.getGroups().remove(selectedGroup);
        }
        canva.repaint();
      }
    });
    editMenu.add(ungroupMenuItem);
  }

  private void ChangeMode(JButton button) {
    for (JButton b : buttons) {
      b.setSelected(false);
      b.setBackground(Color.white);
    }
    canva.removeMouseListener(mode);
    canva.removeMouseMotionListener(mode);
    mode = ModeList.values()[buttons.indexOf(button)].getMouseAdapter();
    mode.setCanva(canva);
    canva.addMouseListener(mode);
    canva.addMouseMotionListener(mode);
    button.setSelected(true);
    button.setBackground(Color.darkGray);
    canva.clearSelected();
  }



}

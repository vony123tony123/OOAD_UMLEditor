package gui;

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
import javax.swing.JPanel;
import controller.ChangeNameController;
import controller.GroupBtnController;
import controller.UnGroupBtnController;
import mousecontroller.IMouseAdapter;

public class Windows {

  private JFrame frame;
  private ArrayList<JButton> buttons;
  private Canva canva;
  private IMouseAdapter mode;

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

    for (int i = 0; i < ButtonList.values().length ; i++) {
      JButton button = new JButton(ButtonList.values()[i].getImageIcon());
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
        mode = ButtonList.SELECT.getMode();
        mode.setCanva(canva);
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
    changeObjectNameMenuItem.addActionListener(new ChangeNameController(canva));
    editMenu.add(changeObjectNameMenuItem);

    JMenuItem groupMenuItem = new JMenuItem("Group");
    groupMenuItem.addActionListener(new GroupBtnController(canva));
    editMenu.add(groupMenuItem);

    JMenuItem ungroupMenuItem = new JMenuItem("Ungroup");
    ungroupMenuItem.addActionListener(new UnGroupBtnController(canva));
    editMenu.add(ungroupMenuItem);
  }

  private void ChangeMode(JButton button) {
    for (JButton b : buttons) {
      b.setSelected(false);
      b.setBackground(Color.white);
    }
    mode = ButtonList.values()[buttons.indexOf(button)].getMode();
    mode.setCanva(canva);
    button.setSelected(true);
    button.setBackground(Color.darkGray);
    mode.clearSelected();
  }



}

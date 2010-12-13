package networknew;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Überschrift:   Chatclient - Test der Aufgabe 2
 * Beschreibung:
 * Copyright:     Copyright (c) 2002
 * Organisation:  HAW
 * @author G. Klemke
 * @version 1.0
 */
public class ChatClientFrame extends JFrame {
  boolean connectionStart = false;
  ConnectControl conCtrl;
  JPanel contentPane;

  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  GridLayout gridLayout2 = new GridLayout();
  JButton jButton1 = new JButton();
  JTextField jTextField3 = new JTextField();
  JPanel jPanel7 = new JPanel();
  JList jList1 = new JList();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel3 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel1 = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  BorderLayout borderLayout6 = new BorderLayout();

  /**Den Frame konstruieren*/
  public ChatClientFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  /**Initialisierung der Komponenten*/
  private void jbInit() throws Exception  {
    //setIconImage(Toolkit.getDefaultToolkit().createImage(ChatClientFrame.class.getResource("[Ihr Symbol]")));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout6);
    this.setSize(new Dimension(400, 300));
    this.setTitle("Der Frame für den Chat Client");
    jMenuFile.setText("Datei");
    jMenuFileExit.setText("Beenden");
    jMenuFileExit.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuFileExit_actionPerformed(e);
      }
    });
    jMenuHelp.setText("Hilfe");
    jMenuHelpAbout.setText("Info");
    jMenuHelpAbout.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuHelpAbout_actionPerformed(e);
      }
    });
    jPanel1.setLayout(borderLayout3);
    jPanel2.setLayout(borderLayout5);
    jPanel5.setLayout(gridLayout2);
    gridLayout2.setColumns(1);
    jButton1.setText("Connect");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        connect_actionPerformed(e);
      }
    });
    jPanel4.setLayout(borderLayout4);
    jPanel7.setLayout(borderLayout2);
    jPanel6.setLayout(borderLayout1);
    jPanel3.setLayout(gridLayout1);
    gridLayout1.setRows(2);
    gridLayout1.setColumns(2);
    gridLayout1.setHgap(10);
    gridLayout1.setVgap(10);
    jTextField2.setText("192.168.1.1");
    jTextField1.setText("willi");
    jLabel2.setText("Server-Adresse");
    jLabel1.setText("Chat-Name");
    jList1.setMaximumSize(new Dimension(1000, 1000));
    jList1.setPreferredSize(new Dimension(200, 100));
    jTextField3.setToolTipText("");
    jTextField3.setText("Hallo");
    jTextArea1.setDoubleBuffered(true);
    jTextArea1.setEditable(false);
    jScrollPane1.setAutoscrolls(true);
    jScrollPane1.setDoubleBuffered(true);
    jScrollPane1.setToolTipText("");
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuHelp);
    contentPane.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jPanel4, BorderLayout.NORTH);
    jPanel4.add(jTextField3, BorderLayout.CENTER);
    jPanel2.add(jPanel5, BorderLayout.CENTER);
    jPanel5.add(jButton1, null);
    contentPane.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jScrollPane1, BorderLayout.CENTER);
    contentPane.add(jPanel7, BorderLayout.NORTH);
    jPanel7.add(jPanel6, BorderLayout.WEST);
    jPanel6.add(jList1, BorderLayout.CENTER);
    jPanel7.add(jPanel3, BorderLayout.EAST);
    jPanel3.add(jLabel1, null);
    jPanel3.add(jTextField1, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(jTextField2, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
    this.setJMenuBar(jMenuBar1);
  }
  /**Aktion Datei | Beenden durchgeführt*/
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }
  /**Aktion Hilfe | Info durchgeführt*/
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    ChatClientFrame_Infodialog dlg = new ChatClientFrame_Infodialog(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
  }
  /**Überschrieben, so dass eine Beendigung beim Schließen des Fensters möglich ist.*/
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }
  void connect_actionPerformed(ActionEvent e) {
    // Überprüfen gültiger Server- und Chatname
    if (jTextField2.getText().length() >0 && jTextField3.getText().length() > 0
         && connectionStart == false) {
      // Chatname, Servername, TextArea für Eintrag der Verbindungen
      conCtrl = new ConnectControl(jTextField1.getText(),jTextField2.getText(),
                                  jList1,jTextField3, jTextArea1);
      conCtrl.start();
      connectionStart = true;
    }
  }
}
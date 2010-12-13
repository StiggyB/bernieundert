package networknew;

import java.util.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Überschrift:   Chatclient - Test der Aufgabe 2
 * Beschreibung:
 * Copyright:     Copyright (c) 2002
 * Organisation:  HAW
 * @author G. Klemke
 * @version 1.0
 */

public class ConnectControl extends Thread {
  private String chatName;
  private Map ipMap;
  private String serverName;
  private JList jTextListe;
  private JTextField mytext;
  private JTextArea gotTextArea;
  private InetAddress local;
  private InetAddress addr;
  private Socket socket;
  private boolean stop = false;

  private static final String CRLF = "\n\r";


  public ConnectControl(String chatName, String serverName, JList liste,
                                                JTextField tf, JTextArea a) {
    this.chatName = chatName;
    this.serverName = serverName;
    this.jTextListe = liste;
    mytext = tf;
    gotTextArea = a;
  }

  public void terminate() { stop = true; }

  public void run() {
    // Verbindung mit dem Server herstellen
    try {
      local = InetAddress.getLocalHost();
      addr = InetAddress.getByName(serverName);
      socket = new Socket(addr, 47110);
      System.out.println("Socket zum Server erstellt.");
    }
    catch (IOException ioEx) {
      System.err.println(ioEx.getMessage());
      System.err.println("No such Server - Thread Killed. ");
      stop = true;
    }
    if (!stop) {
     try {
      BufferedReader in = new BufferedReader(
                             new InputStreamReader(
                                socket.getInputStream()));
      PrintWriter out = new PrintWriter(
                             new BufferedWriter(
                                   new OutputStreamWriter(
                                       socket.getOutputStream())),true);
      String first = in.readLine();
      System.out.println("Server meldet: " + first);
      out.println("MYDATA: " + local.getHostAddress() + " " + chatName);
      ReceiveThread receive = new ReceiveThread(gotTextArea);
      receive.start();
      mytext.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent event) {
          String txt = event.getActionCommand();
          mytext.setText("");
          Set set = ipMap.keySet();
          Iterator it = set.iterator();
          String toSend = "<"+chatName+"> "+txt +CRLF;

          while(it.hasNext()) {
            String s = (String)it.next();
            System.out.println("Sende an: " + s + " : " + toSend);
            try {
              InetAddress iadr = InetAddress.getByName(s);
              DatagramPacket p = new DatagramPacket(toSend.getBytes(),toSend.length(),iadr,47120);
              DatagramSocket sock = new DatagramSocket();
              sock.send(p);
           }
           catch (IOException ioex) {}
          }
        }
      }
      );
      while (!stop) {

        out.println("GETLIST ");
        String line = in.readLine();
        System.out.println("Anfrage GETLIST: - Antwort: " + line);
        if (line.length() > 0) {
          StringTokenizer st = new StringTokenizer(line);
          st.nextToken();
          Map m = new HashMap();
          while (st.hasMoreTokens()) {
            String ip = st.nextToken();
            String ch = st.nextToken();
            System.out.println("IP-Adresse hinzufügen : "+ ip + " " + ch);
            m.put(ip,ch);
          }
          Set keySet = m.keySet();
          System.out.println("Set von IP-Adressen: " + keySet);

          Iterator setIt = keySet.iterator();
          Set paarSet = new HashSet();

          while( setIt.hasNext()) {
            String s1 = (String) setIt.next();
            paarSet.add(s1 + " " +(String) m.get(s1));
          }
          Object [] ipListe = paarSet.toArray();
          jTextListe.setListData(ipListe);
          ipMap = m;
        }
        try {
           this.sleep(5000);   // 5 Sekunden warten
        }
        catch (InterruptedException iotime) {
          System.err.println("Exception Ende 1 von ConControl.");
          socket.close();
        }
      }
      System.out.println("ConControl Tread endet ...");
      socket.close();
      receive.terminate();
     }
     catch (IOException ioex) {
       System.err.println("Exception Ende 2 von ConControl.");
     }
    } // if (!stop )
  }
}
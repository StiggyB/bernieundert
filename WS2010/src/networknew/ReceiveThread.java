package networknew;

import java.net.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
/**
 * Überschrift:   Chatclient - Test der Aufgabe 2
 * Beschreibung:
 * Copyright:     Copyright (c) 2002
 * Organisation:  HAW
 * @author G. Klemke
 * @version 1.0
 */

public class ReceiveThread extends Thread{
  private DatagramSocket inSock;
  private JTextArea area;
  private boolean stop = false;

  private byte [] inBytes = new byte[1024];
  public ReceiveThread(JTextArea a) {
    area = a;
    for (int i = 0; i < inBytes.length; i++) inBytes[i] = 0;
  }
  public void terminate() { stop = true; }
  public void run() {
    try {
      inSock = new DatagramSocket(47120);
//      inSock.setSoTimeout(50);

      while (!stop) {
        DatagramPacket p = new DatagramPacket(inBytes, inBytes.length);
        inSock.receive(p);
        if (p.getLength() > 0) {
          System.out.println("Empfangen: " + p.getLength());
          String sentence = new String(p.getData(),0,p.getLength());
          area.append(sentence);
        }
      }
      inSock.close();
      System.out.println("Receive Thread endet ...");
    } catch (IOException ioex) {}
  }
}
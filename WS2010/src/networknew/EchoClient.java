package networknew;
/* EchoClient.java */

import java.net.*;
import java.io.*;

public class EchoClient
{
 // final static int ECHO_PORT = 7;   // Der Standard Echo Port
  public static void main(String[] args)
  {
    if (args.length != 2) {
      System.err.println("Usage: java EchoClient <host> <Port>");
      System.exit(1);
    }
    int port = Integer.parseInt(args[1]);
    System.out.println("Try to connect to " + args[0]
                       + " Port Nr.: " + port + " ...");
    try {
      Socket sock = new Socket(args[0], port);
      BufferedReader  rd = new BufferedReader(
                            new InputStreamReader(sock.getInputStream()));
      PrintWriter wr = new PrintWriter(
                         new BufferedWriter(
                           new OutputStreamWriter(
                            sock.getOutputStream())) ,true);
      //Timeout setzen
      sock.setSoTimeout(300);
      //Ausgabethread erzeugen
      EchoReaderThread th = new EchoReaderThread(rd);
      th.start();
      //Schleife für Benutzereingaben
      BufferedReader conin = new BufferedReader(
                             new InputStreamReader(System.in));
      String line = "";
      while (true) {
        //Eingabezeile lesen
        line = conin.readLine();
        if (line.equalsIgnoreCase("QUIT")) {
          break;
        }
        //Eingabezeile an ECHO-Server schicken
        wr.println(line);
      }
      //Programm beenden
      System.out.println("terminating output thread...");
      th.requestStop();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
      rd.close();
      wr.close();
      sock.close();
    } catch (IOException e) {
      System.err.println(e.toString());
      System.exit(1);
    }
  }
}

class EchoReaderThread extends Thread
{
  BufferedReader rd;
  boolean stoprequested;

  public EchoReaderThread(BufferedReader in)
  {
    super();
    rd = in;
    stoprequested = false;
  }

  public synchronized void requestStop()
  {
    stoprequested = true;
  }

  public void run()
  {
    String str;
    System.out.println("EchoReaderThread Waiting for Echo ...");
    try {
      while (!stoprequested) {
       try {
         str = rd.readLine();
          System.out.println("GOT: "+str);
       } catch (InterruptedIOException iox) {
        // zu lange im read() aufgehalten ...
       }
      }
    } catch (IOException e) {
      System.err.println("EchoReaderThread: " + e.toString());
    }
  }
}
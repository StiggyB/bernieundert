package networknew;
/* SimpleEchoServer.java */

import java.net.*;
import java.io.*;

public class SimpleEchoServer
{
  public static void main(String[] args)
  {
    if (args.length != 1) {
      System.err.println("Usage: java SimpleEchoServer <PortNummer>");
      System.exit(1);
    }
    int port = Integer.parseInt(args[0]);
    try {
      System.out.println("Warte auf Verbindung auf Port " + port + "  ....");
      ServerSocket echod = new ServerSocket(port);
      Socket socket = echod.accept();
      System.out.println("Verbindung hergestellt");
      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();
      int c;
      while ((c = in.read()) != -1) {
        out.write((char)c);
        out.flush();        // Ist notwendig !
        System.out.print((char)c);
      }
      System.out.println("Verbindung beenden");
      socket.close();
      echod.close();
    } catch (IOException e) {
      System.err.println(e.toString());
      System.exit(1);
    }
  }
}
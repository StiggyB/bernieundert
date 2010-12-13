package networknew;
/* Contact2WebServer.java aus GotoJava 2 */



import java.net.*;
import java.io.*;

public class Contact2webserver
{
  public static void main(String[] args)
  {
    if (args.length != 2) {
      System.err.println(
        "Usage: java Contact2WebServer <host> <file>"
      );
      System.exit(1);
    }
    try {
      Socket sock = new Socket(args[0], 80);
      OutputStream out = sock.getOutputStream();
      InputStream in = sock.getInputStream();
      //GET-Kommando senden
      String s = "GET " + args[1] + " HTTP/1.0" + "\r\n\r\n";
      out.write(s.getBytes());
      //Ausgabe lesen und anzeigen
      int len;
      byte[] b = new byte[100];
      while ((len = in.read(b)) != -1) {
        System.out.write(b, 0, len);
      }
      //Programm beenden
      in.close();
      out.close();
      sock.close();
    } catch (IOException e) {
      System.err.println(e.toString());
      System.exit(1);
    }
  }
}
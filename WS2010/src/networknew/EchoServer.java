package networknew;
/* EchoServer.java */

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class EchoServer
{
  public static void main(String[] args)
  {
    if (args.length != 1) {
      System.err.println("Usage: java EchoServer <PortNummer>");
      System.exit(1);
    }
    int port = Integer.parseInt(args[0]);
    int cnt = 0;
    try {
      ExecutorService executor = Executors.newFixedThreadPool(10);
      System.out.println("Warte auf Verbindungen auf Port " + port + " ...");
      ServerSocket echod = new ServerSocket(port);
      while (true) {
        Socket socket = echod.accept();
        executor.submit(new EchoClientThread(++cnt, socket));
      }
    } catch (IOException e) {
      System.err.println(e.toString());
      System.exit(1);
    }
  }
}

class EchoClientThread implements Runnable
{
  private int    name;
  private Socket socket;

  public EchoClientThread(int name, Socket socket)
  {
    this.name   = name;
    this.socket = socket;
  }

  public void run()
  {
    String msg = "EchoServer: Verbindung " + name;
    System.out.println(msg + " hergestellt");
    try {
      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();
      out.write((msg + "\r\n").getBytes());
      int c;
      while ((c = in.read()) != -1) {
        out.write((char)c);
        out.flush();
        System.out.print((char)c);
      }
      System.out.println("Verbindung " + name + " wird beendet");
      socket.close();
    } catch (IOException e) {
      System.err.println(e.toString());
    }
  }
}
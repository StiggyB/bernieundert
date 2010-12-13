/* ChatServer.java */

package networknew;

import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer
{
  public static void main(String[] args)
  {
    int port = 12345;
    int cnt = 0;
    Map connList = new HashMap();
    try {
      System.out.println("Warte auf Verbindungen auf Port " + port + " ...");
      ServerSocket echod = new ServerSocket(port);
      while (true) {
        Socket socket = echod.accept();
        (new ChatServerThread(++cnt, socket, connList)).start();
      }
    } catch (IOException e) {
      System.err.println(e.toString());
      System.exit(1);
    }
  }
}

class ChatServerThread extends Thread
{
  private int name;
  private Socket socket;
  private InetAddress myInetAdr;
  private InetAddress connInetAdr;
  private Map connList;
  private BufferedReader in;
  private PrintWriter out;
  private String address = "";
  private String nick;

  public ChatServerThread(int name, Socket socket, Map connList)
  {
    this.name   = name;
    this.socket = socket;
    this.connList = connList;
  }

  public void run()
  {
    String msg = "ChatServer: Verbindungnr: " + name;
    String recv;
    System.out.println(msg + " hergestellt");
    try {
        myInetAdr = InetAddress.getLocalHost();
    } catch (IOException e) {
      System.err.println(e.toString());
    }
    try {
       in = new BufferedReader(
                 new InputStreamReader(
                   socket.getInputStream()));
       out = new PrintWriter(
                  new BufferedWriter(
                    new OutputStreamWriter(
                      socket.getOutputStream())), true);
      connInetAdr = socket.getInetAddress();
      out.println("HALLO: Meine Adresse ist " + myInetAdr.getHostAddress());
      System.out.println("Sende: HALLO: Meine Adresse ist " + myInetAdr.getHostAddress());
      while (true) {
        recv = in.readLine();
        if (recv == null) break;
        System.out.println("Empfangen: '" + recv + "'");
        if (recv.startsWith("CHATDATA:")) {
         StringTokenizer t = new StringTokenizer(recv);
         if (t.countTokens() == 3) {
           t.nextToken();                      // Kommando überspringen
           address = t.nextToken();
           nick = t.nextToken();
           connList.put(address,nick);
           System.out.println("Aufgenommen: '" + address + "' '"+ nick + "'");
         } else System.out.println("CHATDATA - Format falsch: '" + recv + "'");
        }
        else if (recv.startsWith("GIBINFO")) {
          Iterator it = connList.keySet().iterator();
          String send = "INFO:";
          while ( it.hasNext()) {
            String adr = (String)it.next();
            send = send + " " + adr + " " + connList.get(adr);
         }
         send = send;
         System.out.println("Sende: " + send);
         out.println(send);
        }
        else if (recv.startsWith("BYE")) break;
        else {
          if (recv.length() == 0) System.out.println("Anfrage mit Länge 0 !");
          else
          System.out.println("Falsche Anfrage: '"+ recv + "'"
                                       + " Länge: " + recv.length() + " " );
        }
      }
      System.out.println("Verbindung " + name + " mit "
                         + connInetAdr.getHostName() + " wird beendet.");
      if (address.length() > 0) connList.remove(address);
      socket.close();
    } catch (IOException e) {
      System.err.println(e.toString());
      System.out.println("Verbindung " + name + " mit " + connInetAdr.getHostName() +
                              " wird mit Exception beendet");
      if (address.length() > 0) connList.remove(address);


    }
  }
}
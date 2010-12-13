package networknew;
import java.net.*;
/**
 * Name2Address.java aus Goto Java 2.
 * Ein etwas dämliches Konstrukt dessen Nutzen bestenfalls darin besteht
 * Programmparameter verarbeiten zu lernen
 * 
 *
 */
public class Name2Address01
{
  public static void main(String[] args)
  {
    if (args.length != 1) {
      System.err.println("Usage: java NameToAddress <host>");
      System.exit(1);
    }
    try {
      //Get requested address
      InetAddress addr = InetAddress.getByName(args[0]);
      System.out.print("\"" + addr.getHostName() + "\"");
      System.out.println(" hat die IP-Adresse: " + addr.getHostAddress());
    } catch (UnknownHostException e) {
      System.err.println(e.toString());
      System.exit(1);
    }
  }
}
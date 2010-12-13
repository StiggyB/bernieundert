package networknew;
import java.net.*;
import java.io.*;
/** GetDayTime.java aus Goto Java 2 
 * */
public class GetDayTime
{
  public static void main(String[] args)
  {
    if (args.length != 1) {
      System.err.println("Usage: java GetDayTime <host>");//time-a.nist.gov  works(most of the time)
      System.exit(1);
    }
    try {
      Socket sock = new Socket(args[0], 13);
      InputStream in = sock.getInputStream();
      int len;
      byte[] b = new byte[100];
      while ((len = in.read(b)) != -1) {
        System.out.write(b, 0, len);
      }
      in.close();
      sock.close();
    } catch (IOException e) {
      System.err.println("IOException: "+ e.toString());
      System.exit(1);
    }
  }
}
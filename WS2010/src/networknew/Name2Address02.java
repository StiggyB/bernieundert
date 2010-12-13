package networknew;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Illustriert die Verwendung einiger Operationen der Klasse
 * {@link java.net.InetAddress InetAddress} etc.
 * 
 * @author Bernd Kahlbrandt
 * 
 */
public class Name2Address02 {
	public static void main(String[] args) {
		System.out.println("Geben Sie bitte einen Domainnamen ein!");
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String domain = sc.next();
			try {
				InetAddress addr = InetAddress.getByName(domain);
				System.out.print("\"" + addr.getHostName() + "\"");
				System.out.println(" hat die IP-Adresse: "
						+ addr.getHostAddress());
			} catch (UnknownHostException e) {
				System.err.println(e.toString());
				System.exit(1);
			}
			System.out.println("Geben Sie bitte einen Domainnamen ein!");

		}
	}
}
package spoofmail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class TestEmailSender {
	public static void main(String[] args) throws AddressException, MessagingException {
		String host = "haw-mailer.haw-hamburg.de";
		// String host = "exchange.hansaholz.de";
		String port = "587";
		// String port = "25";
		String mailFrom = "martin.slowikowski@haw-hamburg.de";
		String mailTo = "donald.daisy@entenhausen.de";
		// String mailTo = "administrator@hansaholz.de";
		String subject = "Hallo da drueben!";
		String bodyMessage = "<html><b>Hallo</b><br/><i>Wie gehts denn dem Geldspeicher?</i></html>";

		EmailSender sender = new EmailSender();
		sender.sendEmail(host, port, mailFrom, mailTo, subject, bodyMessage);
	}

	/**
	 * 530 SMTP authentication is required.
	 * 
	 * You have enabled SMTP authentication for the IP range that the user is
	 * connecting from, but the user has not configured his client to use SMTP
	 * authentication. There's two ways to solve this problem. Either configure
	 * your email client to use SMTP authentication. This setting is normally
	 * found in the account settings in your email client. Or, disable SMTP
	 * authentication for the IP range. The first solution is recommended since
	 * it reduces the risk that anyone will send spam through your server.
	 */
}
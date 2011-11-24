package namensdienst;

public class NameServiceStarter {

	public static void main(String[] args) {
		NameServiceServer nsServer = new NameServiceServer(Integer.parseInt(args[0]));
		nsServer.start();
	}
}

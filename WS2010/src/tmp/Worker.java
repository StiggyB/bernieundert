package tmp;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.regex.Pattern;
import java.io.InputStreamReader;

public class Worker extends Thread implements Runnable {
	private Socket connectionSocket;
	private String client;
	boolean nameOk=false;

	public Worker(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}

	public void run() {
		System.out.println("Worker " + client + ": Herzlich Willkommen!!!");		
		String temp = "";		
			try{				
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connectionSocket.getInputStream())); // Socket lesen
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						connectionSocket.getOutputStream())); // Socket schreiben
				System.out.println("Worker  " + client + ": mit Client connectet");						
				String client ="",clientIp =""; 				
				do{
					client = in.readLine();
	                if(client!=null){
	                    if(clientNameIsOK(client)){
	                    	client=convertClientName(client);
	                        System.out.println("Worker: " + client + ": Name OK");
	                        clientIp = connectionSocket.getRemoteSocketAddress().toString();
	                        Server.getClientlist().put(clientIp, client) ;
	                        out.write("OK"+ "\n");
	                        out.flush();  
	                    }else{
	                        System.out.println("Worker " + client + " send ERROR to Client");
	                        out.write("ERROR"+ "\n");
	                        out.flush();
	                        System.out.println("Server : Wait for new connection");
	                    }
	                }
				}while( !nameOk || client==null)	;
        		System.out.println("Worker  " + client + ": bereit zum arbeiten......");
				temp = in.readLine();
	        	while (temp!=null && !(temp.contains("BYE") )) {
						if(temp.contains("BYE")){
							Server.getClientlist().remove(client);
							out.write("Abbruch erfolgt" + "\n");
							out.flush();
						}else{
 							String list ="LIST <" + Server.getClientlist().size() + ">";
							int i=1;
							for (Map.Entry<String,String> elem : Server.getClientlist().entrySet()){
								list=list+ " " + elem.getKey()+"-"+i +" " +elem.getValue()+"-"+i;
								i++;
							}
							list=list+"\n";
							out.write(list);
							out.flush();
							System.out.println("Worker  " + client + ": hat lite gesendet!!!");
						}
			   		System.out.println("Worker  " + client + ": bereit zum arbeiten......");
					temp = in.readLine();
				}
	        	Server.getClientlist().remove(clientIp);
				try {					
					connectionSocket.close(); 
					System.out.println("Worker  " + client + " close");
				} catch (IOException e) {
						System.out.println("Worker  " + client + ": couldn't close");
				}
			}catch (IOException e){
				System.out.println(" Worker " + client + ": couldn't close");
				Server.getClientlist().remove(client);
			} 
	
	}

	private boolean clientNameIsOK(String Name) {
		System.out.println(Name);
		return this.nameOk = Pattern.matches("\\w+", convertClientName(Name))
				&& convertClientName(Name).length() <= 20
				&& (Name.substring(0, 5).contains("NEW <"))
				&& Name.trim().substring(Name.length() - 1, Name.length()).contains(">");
	}

	private String convertClientName(String Name) {
		if(Name.length()<5)return Name;
		String MyName = Name.substring(5).trim();
		return MyName.substring(0, MyName.length() - 1);
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClient() {
		return client;
	}
}
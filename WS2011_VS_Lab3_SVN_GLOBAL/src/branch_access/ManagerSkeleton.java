package branch_access;

import java.io.IOException;
import java.net.UnknownHostException;

import messages.InvokeMessage;
import tcp_advanced.Connection;
import tcp_advanced.Server;

public class ManagerSkeleton implements Runnable{
    
    private volatile boolean running;
    private Thread thread;
    
    private Server server;
    private int port;
    
    public ManagerSkeleton(int managerPort) throws IOException{
        this.running = true;
        this.thread = new Thread(this);
        this.port = managerPort;
    }

    @Override
    public void run() {
        try {
            this.server = new Server(port);
         while(running){
                this.server.accept();
                Connection connection = server.getConnection();
                try {
                    Object rcvdObj = connection.receive();
                    if(!(rcvdObj instanceof InvokeMessage)){
//                        connection.send(new IllegalMessageException(rcvdObj.getClass().toString()));
                    }
                    ManagerWorker mw = new ManagerWorker(connection);
                    mw.start();
                } catch (ClassNotFoundException e) {
                    connection.send(new ClassNotFoundException(e.getMessage()));
                }
            }
        } catch (UnknownHostException e) {
         e.printStackTrace();
        } catch (IOException e) {
         e.printStackTrace();
        }
    }
    
    public void stop(){
        this.running = false;
    }
    
    public void start(){
        this.thread.start();
    }

}

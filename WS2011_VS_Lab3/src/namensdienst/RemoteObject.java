package namensdienst;

import java.io.Serializable;
import java.math.BigInteger;

public class RemoteObject implements Serializable{

	private String host;
	private int port;
	private BigInteger objID;
	private Object type;
	
	public RemoteObject(String host, int port, BigInteger objID, Object type) {
		super();
		this.host = host;
		this.port = port;
		this.objID = objID;
		this.type = type;
	}
	
}

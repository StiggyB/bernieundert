package namensdienst;

import java.io.Serializable;
import java.math.BigInteger;

public class RemoteObject implements Serializable{

	private String host;
	private int port;
	private BigInteger objID;
	private Object objRef;
	
	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public BigInteger getObjID() {
		return objID;
	}

	public Object getObjRef() {
		return objRef;
	}

	public RemoteObject(String host, int port, BigInteger objID, Object type) {
		this.host = host;
		this.port = port;
		this.objID = objID;
		this.objRef = type;
	}
	
}

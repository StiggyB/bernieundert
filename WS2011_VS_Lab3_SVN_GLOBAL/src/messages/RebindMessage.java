package messages;

import java.io.Serializable;

public class RebindMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5025999473771344271L;
	private String remoteName;
	private Class<?> type;

	public Class<?> getType() {
		return type;
	}

	public String getRemoteName() {
		return remoteName;
	}

	public RebindMessage(Class<?> type, String remoteName) {
		this.remoteName = remoteName;
		this.type = type;
	}

	@Override
	public String toString() {
		return "RemoteObject [remoteName=" + remoteName + ", type=" + type + "]";
	}
}

package messages;

import java.io.Serializable;

public class RebindMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5025999473771344271L;
	private String remoteName;
	private Object remoteInfo;

	public Object getRemoteInfo() {
		return this.remoteInfo;
	}

	public String getRemoteName() {
		return this.remoteName;
	}

	public RebindMessage(Object remoteInfo, String remoteName) {
		this.remoteName = remoteName;
		this.remoteInfo = remoteInfo;
	}

	@Override
	public String toString() {
		return "RebindMessage [remoteName=" + remoteName + ", type=" + remoteInfo + "]";
	}
}

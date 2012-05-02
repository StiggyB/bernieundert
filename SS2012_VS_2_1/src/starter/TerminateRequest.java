package starter;


public class TerminateRequest {
	
	private boolean terminate;
	private String processName;
	private long timestamp;
	private int terminateId;
	
	public TerminateRequest(String procesName, long timestamp, boolean terminate, int terminateId){
		this.terminate = terminate;
		this.processName = procesName;
		this.timestamp = timestamp;
		this.terminateId = terminateId;
	}
	
	public String getProcessName() {
		return processName;
	}
	
	public boolean getTerminate(){
		return terminate;
	}
	
	public long getTimestamp(){
		return timestamp;
	}
	
	public int getTerminateId() {
		return terminateId;
	}
}

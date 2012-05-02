package starter;


public class TerminateRequest {
	
	private boolean terminate;
	private String processName;
	private long timestamp;
	
	public TerminateRequest(String procesName, long timestamp, boolean terminate){
		this.terminate = terminate;
		this.processName = procesName;
		this.timestamp = timestamp;
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
}

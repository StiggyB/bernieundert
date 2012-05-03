package starter;

/**
 * Represents a termination request for a running ggt-Calculation.
 * 
 * @author Martin
 * 
 */
public class TerminateRequest {

	private boolean terminate;
	private String processName;
	private long timestamp;

	/**
	 * 
	 * @param processName
	 *            Name of the original process, that started the termination
	 *            request.
	 * @param timestamp
	 *            time when termination request has been issued.
	 * @param terminate
	 *            forwarded termination status of the previous process.
	 */
	public TerminateRequest(String processName, long timestamp, boolean terminate) {
		this.terminate = terminate;
		this.processName = processName;
		this.timestamp = timestamp;
	}

	/**
	 * This methods returns the name of the original process, which started the
	 * termination request.
	 * 
	 * @return original process name
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * This method indicates, if the anterior process' termination request has
	 * been answered as terminate or not terminate.
	 * 
	 * @return last process' termination request status
	 */
	public boolean isTerminationOk() {
		return terminate;
	}

	/**
	 * This method can be used to determine at which time this termination
	 * request has been issued
	 * 
	 * @return point of time, when request has been placed
	 */
	public long getTimestamp() {
		return timestamp;
	}
}

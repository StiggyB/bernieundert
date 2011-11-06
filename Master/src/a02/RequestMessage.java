package a02;

import java.util.ArrayList;
import java.util.List;

public class RequestMessage {

	private List<WorkerInfo> wInfoList = new ArrayList<WorkerInfo>();

	public List<WorkerInfo> getwInfoList() {
		return wInfoList;
	}

	public RequestMessage(List<WorkerInfo> wInfoList) {
		super();
		this.wInfoList = wInfoList;
	}
}

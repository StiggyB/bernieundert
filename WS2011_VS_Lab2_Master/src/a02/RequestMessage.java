package a02;

/**
 * Praktikum: VSP<br>
 * Semester: WS11<br>
 * Aufgaben-Nr.: 02<br>
 * 
 * Version: V0.1<br>
 * Aenderungen:
 * 
 * Quellen: API, Swing, VS Folien
 * 
 * @author Mueller-Pettenpohl, Tell #1989982, Benjamin, Burchart #1863248<br>
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequestMessage implements Serializable {

	private static final long serialVersionUID = -9199843231239568454L;
	private List<WorkerInfo> wInfoList = new ArrayList<WorkerInfo>();

	public RequestMessage(List<WorkerInfo> wInfoList) {
		super();
		this.wInfoList = wInfoList;
	}
	
	public List<WorkerInfo> getwInfoList() {
		return wInfoList;
	}

}

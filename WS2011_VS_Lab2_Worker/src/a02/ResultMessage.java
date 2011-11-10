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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ResultMessage implements Serializable {

	private static final long serialVersionUID = -6065578273626197783L;
	private List<BigInteger> results = new ArrayList<BigInteger>();
	private List<Long> cpuTimes = new ArrayList<Long>();

	public ResultMessage(List<BigInteger> result, List<Long> cpuTimes) {
		this.results = result;
		this.cpuTimes = cpuTimes;
	}

	public List<Long> getCpuTimes() {
		return cpuTimes;
	}

	public List<BigInteger> getResults() {
		return this.results;
	}

	@Override
	public String toString() {
		return "ResultMessage [result=" + results + "]";
	}

}

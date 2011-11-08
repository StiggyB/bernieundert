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

public class CalculateMessage implements Serializable {

	private static final long serialVersionUID = 840244832287440949L;
	private BigInteger n;
	private int maxIterations;
	private long cpuTime;

	public CalculateMessage(BigInteger n, int maxIterations, long cpuTime) {
		this.n = n;
		this.maxIterations = maxIterations;
		this.cpuTime = cpuTime;
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public BigInteger getN() {
		return this.n;
	}

	public long getCpuTime() {
		return this.cpuTime;
	}

	@Override
	public String toString() {
		return "CalculateMessage [n = " + n + "]";
	}
}

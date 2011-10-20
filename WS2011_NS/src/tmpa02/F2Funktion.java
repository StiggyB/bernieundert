package tmpa02;

public class F2Funktion implements Funktion {

    @Override
    public double f(double x) {
        return Math.sin(x)*Math.cos(x);
    }
    
    @Override
    public String toString() {
        return "sin(x)*cos(x)";
    }

}

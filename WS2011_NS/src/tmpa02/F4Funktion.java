package tmpa02;

public class F4Funktion implements Funktion {

    @Override
    public String toString() {
        return "-0,2x²+1";
    }

    @Override
    public double f(double x) {
        return -0.2*Math.pow(x, 2) + 1;
    }

}

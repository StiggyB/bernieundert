package tmpa02;

public class F3Funktion implements Funktion {

    @Override
    public double f(double x) {
        return (1.0/3.0)*Math.pow(x, 2) - 2*x +2;
    }

    @Override
    public String toString() {
        return "1/3x²-2x+2";
    }

}

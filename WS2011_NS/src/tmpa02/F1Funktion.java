package tmpa02;

public class F1Funktion implements Funktion {

    @Override
    public double f(double x) {
        return Math.pow(x, 5) + 3*Math.pow(x, 4) - 5*Math.pow(x, 3) - 15*Math.pow(x, 2) + 4*x + 12;
    }

    @Override
    public String toString() {
        return "xâ�µ+3xâ�´-5xÂ³-15xÂ²+4x+12";
    }
    
}

package tmpa02;

public class F0Funktion implements Funktion {

    @Override
    public double f(double x) {
        return Math.pow(x, 2)-4;
    }

    @Override
    public String toString() {
        return "x²-4";
    }
    
    
    
}

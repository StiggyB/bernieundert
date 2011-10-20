package tmpa02;

public class SekantenVerfahren extends LoesungsVerfahren {

    @Override
    public double solve(Funktion f, double aIntervall, double bIntervall, int iterationen) {
        
        double[] x = new double[2];
        double next;
        double eps;
        int i = 0;
            
        x[0] = aIntervall;
        x[1] = bIntervall;
        eps = getMachineEpsilon();
        
        while(Math.abs(x[1]-x[0]) > eps*2 && i < iterationen) {
            next = x[1]-((x[1]-x[0])/(f.f(x[1])-f.f(x[0]))*f.f(x[1]));
            
            x[0] = x[1];
            x[1] = next;
            i++;
        }
        
        this.iterationen = i;
        
        return x[1];
    }

}

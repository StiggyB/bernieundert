package tmpa02;

public class BisektionsVerfahren extends LoesungsVerfahren {

    @Override
    public double solve(Funktion f, double aIntervall, double bIntervall, int iterationen) {
        
        double  a   = aIntervall;
        double  b   = bIntervall;
        
        double  c   = Double.NaN;
        double  eps = getMachineEpsilon();
        int     i   = 0;
        
        while(Math.abs(b-a) > eps*2 && i < iterationen) {
            i++;
            
            c = a/2+b/2;
            if (f.f(a)*f.f(c) <= 0) {
                b = c;
            } else {
                a = c;
            }
        }
        
        this.iterationen = i;
        
        return c;
    }

}

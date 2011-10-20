package tmpa02;

public class RegulaFalsiVerfahren extends LoesungsVerfahren {

    @Override
    public double solve(Funktion f, double aIntervall, double bIntervall,
            int iterationen) {
        
        double a = aIntervall;
        double b = bIntervall;
        double c = Double.NaN;
        double eps = getMachineEpsilon();
        int i = 0;
        
        
        while(Math.abs(b-a) > 2*eps && i < iterationen) {
            c = (a*f.f(b)-b*f.f(a))/(f.f(b)-f.f(a));
            // alt = a - ((b-a) / (f.f(b)-f.f(a)))*f.f(a)
            if (f.f(a)*f.f(c) <= 0) {
                b = c;
            } else {
                a = c;
            }
            
            i++;
        }
        
        this.iterationen = i;
        
        return c;
    }

}

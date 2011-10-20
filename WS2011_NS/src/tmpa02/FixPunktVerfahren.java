package tmpa02;

public class FixPunktVerfahren extends LoesungsVerfahren {
    
    
    /**
     * Da man bei der Fixpunktiteration nur einen Startpunkt und kein 
     * Startintervall hat ist der parameter bIntervall irrelevant.
     * */
    @Override
    public double solve(Funktion f, double aIntervall, double bIntervall,
            int iterationen) {
        
        double x = aIntervall;
        double eps = getMachineEpsilon();
        int i = 0;
        
        while(Math.abs(f.f(x)) > 2*eps && i < iterationen) {
            x = f.f(x)+x;
            i++;
        }
        
        this.iterationen = i;
        
        return x;
    }

}

package tmpa02;
/**
 * Interface fuer die unterschiedlichen Loesungsverfahren die zu implementieren sind.
 * 
 * @author Dennis Blauhut
 * */

abstract public class LoesungsVerfahren {
    
    /**
     * 
     * @param f Funktion deren Nullstelle gesucht werden soll
     * @param aIntervall linke Intervallgrenze des zu ueberpruefenden Intervalls
     * @param bIntervall rechte Intervallgrenze des zu ueberpruefenden Intervalls
     * @param iterationen Iterationen bis Ergebnis ausgegeben werden soll
     * 
     * @return ggf. vorhandene Nullstelle
     * */
    abstract public double solve(Funktion f, double aIntervall, double bIntervall, int iterationen);
    
    
    
    /**
     * Ermittelt die Maschinengenauigkeit des Computers
     * 
     * @return Maschinengenauigkeit
     * */
    public double getMachineEpsilon() {
        
        double value = 1;
        double eps   = 1;
        
        
        while(1+value != 1) {
            eps = value;
            value = value / 2;
        }
        
        return eps;
    }
    
    
    protected int iterationen = 0;
    
    /**
     * Gibt die Anzahl an Iterationen des letzten Lösungsversuchs an.
     * */
    public int getIterationen() {
        return iterationen;
    }
}

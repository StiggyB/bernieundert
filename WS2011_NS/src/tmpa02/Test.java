package tmpa02;


public class Test {
    
    public static void main(String[] args) {
        
        Funktion f;
        LoesungsVerfahren verfahren;
        
        double aIntervall;
        double bIntervall;
        int iterationen;
        
        
        f = new F0Funktion();
        System.out.printf("Funktion %s\n", f);
        verfahren = new BisektionsVerfahren();
        aIntervall = -10;
        bIntervall = 0;
        iterationen = 100;
        
        System.out.printf("Bisektion\n");
        System.out.printf("Funktion Intervall \t\t\t Nullstelle \t Iterationen\n");
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall = 0;
        bIntervall = 10;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        
        verfahren = new SekantenVerfahren();
        aIntervall = -10;
        bIntervall = 0;
        iterationen = 100;
        
        System.out.printf("SekantenVerfahren\n");
        System.out.printf("Funktion Intervall \t\t\t Nullstelle \t Iterationen\n");
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall = 0;
        bIntervall = 10;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        
        verfahren = new RegulaFalsiVerfahren();
        aIntervall = -10;
        bIntervall = 0;
        iterationen = 100;
        
        System.out.printf("RegulaFalsiVerfahren\n");
        System.out.printf("Funktion Intervall \t\t\t Nullstelle \t Iterationen\n");
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall = 0;
        bIntervall = 10;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        
        verfahren = new FixPunktVerfahren();
        aIntervall = -2;
        bIntervall = -1;
        System.out.printf("Fixpunkt Verfahren\n");
        System.out.printf("Funktion Intervall \t\t\t Nullstelle \t Iterationen\n");
        System.out.printf("%s \t [% f, % f] \t % f \t %d \t (% f \t %d)\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen(), verfahren.solve(f, bIntervall, bIntervall, iterationen), verfahren.getIterationen());        
        
        f = new F2Funktion();
        System.out.printf("Funktion %s\n", f);
        verfahren = new BisektionsVerfahren();
        iterationen = 100;
        aIntervall = 2*Math.PI+1.0/4.0*Math.PI;
        bIntervall = 2*Math.PI-1.0/4.0*Math.PI;
        System.out.printf("Bisektion\n");
        System.out.printf("Funktion \t Intervall \t\t\t Nullstelle \t Iterationen\n");
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        
        
        verfahren = new SekantenVerfahren();
        aIntervall = 2*Math.PI+1.0/4.0*Math.PI;
        bIntervall = 2*Math.PI-1.0/4.0*Math.PI;        
        System.out.printf("Sekantenverfahren\n");
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        
        verfahren = new RegulaFalsiVerfahren();
        aIntervall = 2*Math.PI+1.0/4.0*Math.PI;
        bIntervall = 2*Math.PI-1.0/4.0*Math.PI;        
        System.out.printf("Regula Falsi Verfahren\n");
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen());
        
        
        verfahren = new FixPunktVerfahren();
        aIntervall = 2*Math.PI+1.0/4.0*Math.PI;
        bIntervall = 2*Math.PI-1.0/4.0*Math.PI;
        System.out.printf("Fixpunkt Verfahren\n");
        
        System.out.printf("%s \t [% f, % f] \t % f \t %d \t (% f \t %d)\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen(), verfahren.solve(f, bIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d \t (% f \t %d)\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen(), verfahren.solve(f, bIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d \t (% f \t %d)\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen(), verfahren.solve(f, bIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d \t (% f \t %d)\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen(), verfahren.solve(f, bIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d \t (% f \t %d)\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen(), verfahren.solve(f, bIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d \t (% f \t %d)\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen(), verfahren.solve(f, bIntervall, bIntervall, iterationen), verfahren.getIterationen());
        aIntervall -= 1.0/2.0*Math.PI;
        bIntervall -= 1.0/2.0*Math.PI;
        System.out.printf("%s \t [% f, % f] \t % f \t %d \t (% f \t %d)\n", f, aIntervall, bIntervall, verfahren.solve(f, aIntervall, bIntervall, iterationen), verfahren.getIterationen(), verfahren.solve(f, bIntervall, bIntervall, iterationen), verfahren.getIterationen());
    }
}

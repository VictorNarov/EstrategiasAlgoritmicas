package AMC1;

/**
 *
 * @author VSPC-Infernov4
 */
public class ParPuntos {

    private Punto p1, p2;

    public ParPuntos(Punto p1, Punto p2) {

        this.p1 = p1;
        this.p2 = p2;
    }

    public Punto getP1() {
        return p1;
    }

    public Punto getP2() {
        return p2;
    }
    
    public float distancia_puntos() {
        return (p2.getX() - p1.getX());
    }
}

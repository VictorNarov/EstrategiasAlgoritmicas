/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1.Comun;

import AMC1.Comun.Vertice;


/**
 *
 * @author victo
 */
public class Trio {
    public Trio()
    {
        
    }

    public Trio(Vertice p1, Vertice p2, Vertice p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

        this.distancia = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2))
                + Math.sqrt(Math.pow(p1.getX() - p3.getX(), 2) + Math.pow(p1.getY() - p3.getY(), 2))
                + Math.sqrt(Math.pow(p2.getX() - p3.getX(), 2) + Math.pow(p2.getY() - p3.getY(), 2));
    }

    private Vertice p1;
    private Vertice p2;
    private Vertice p3;
    private double distancia;

    public Vertice getP1() {
        return p1;
    }

    public Vertice getP2() {
        return p2;
    }

    public Vertice getP3() {
        return p3;
    }

    public double getDistancia() {
        return this.distancia;
    }

    public void setP1(Vertice pP1) {
        this.p1 = pP1;
    }

    public void setP2(Vertice pP2) {
        this.p2 = pP2;
    }

    public void setP3(Vertice pP3) {
        this.p3 = pP3;
    }

    public Vertice getMenorX() {
        double min = (Math.min(Math.min(p1.getX(), p2.getX()), p3.getX()));
        if (p1.getX() == min) {
            return p1;
        } else if (p2.getX() == min) {
            return p2;
        } else {
            return p3;
        }
    }

    public Vertice getMayorX() {
        double max = (Math.max(Math.min(p1.getX(), p2.getX()), p3.getX()));

        if (p1.getX() == max) {
            return this.p1;
        } else if (p2.getX() == max) {
            return p2;
        } else {
            return p3;
        }
    }

    @Override
    public String toString() {
        return (p1.toString() + p2.toString() + p3.toString());
    }
}

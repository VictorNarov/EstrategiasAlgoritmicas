/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteP1;

/**
 *
 * @author victo
 */
public class Punto {
    public Punto(float px, float py) {this.x=px; this.y=py;}
    private float x;
    private float y;
    
    public float getX() {return this.x;}
    public float getY() {return this.y;}
    public void setX(float px) {this.x=px;}
    public void setY(float py) {this.y=py;}
    
    public void ver()
    {
        System.out.println(x + " - " + y);
    }
    
}

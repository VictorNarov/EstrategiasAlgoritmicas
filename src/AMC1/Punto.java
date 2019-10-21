/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMC1;

/**
 *
 * @author victo
 */
public class Punto {
    public Punto(int id,float px, float py) {this.id=id; this.x=px; this.y=py;}
    private float x;
    private float y;
    private int id;
    
    public float getX() {return this.x;}
    public int getID() {return id;}
    public float getY() {return this.y;}
    public void setX(float px) {this.x=px;}
    public void setY(float py) {this.y=py;}
    
    public void ver()
    {
        System.out.println(id+") "+ x + " - " + y);
    }
    
}

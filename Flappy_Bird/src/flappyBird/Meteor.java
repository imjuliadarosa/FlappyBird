/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappyBird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.math.*;

/**
 *
 * @author Julia
 */
public class Meteor {
    public ArrayList<Rectangle> meteors;
    public Settings set;
    public Math Math;
    

    public Meteor() {
        meteors = new ArrayList<Rectangle>();
        set = new Settings();
    }
    
    public boolean running(int speed){
        for( Rectangle meteor: meteors){
                meteor.x -= speed;
                meteor.y += speed;
                if(meteor.y == 600){
                    meteors.remove(meteor);
                    return true;
                }
        }
        return false;
    }
    
    public boolean intersects(Rectangle bird){
        for(Rectangle meteor : meteors){
            if(meteor.intersects(bird)){
                return true;
            }
        }
        return false;
    }
    
    public void addMeteor(){
            meteors.add(new Rectangle((int) Math.floor(Math.random() * (set.WIDTH - (set.WIDTH/2) + 1) + set.WIDTH/2),-30,30,30));
    }
    
    public void paintMeteor(Graphics g){
        for (Rectangle meteor : meteors) {
            g.setColor(Color.ORANGE);
            g.fillRect(meteor.x, meteor.y, meteor.width, meteor.height);
        }
        
    }

    void clear() {
        meteors.clear();
    }
    
}

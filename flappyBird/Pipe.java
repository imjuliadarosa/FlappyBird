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
import java.util.Random;

/**
 *
 * @author Julia
 */
public class Pipe {
    public ArrayList<Rectangle> pipes;
    private Random rand;
    Settings set;  

    public Pipe() {
        pipes = new ArrayList<Rectangle>();
        set = new Settings();
        rand = new Random();
    }
    
    boolean running(int speed){
        for( Rectangle pipe: pipes){
            pipe.x-= speed;
            if(pipe.x + pipe.width<0){
                if(pipe.y == 0){
                    pipes.remove(pipe);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean intersects(Rectangle bird) {
        for (Rectangle pipe : pipes) {
            if (pipe.intersects(bird)) {
                return true;
            }
        }
        return false;
    }
    //verifica se o passaro passou entre os canos
    public boolean passThrough(Rectangle bird) {
        for (Rectangle pipe : pipes) {
            if(pipe.y == 0 && bird.x + bird.width / 2 > pipe.x + pipe.width / 2 -10 && bird.x + bird.width/ 2 < pipe.x +pipe.width/2 + 10){
                return true;
            }
        }
        return false;
    }
    
    public void paintPipe(Graphics g){
        for(Rectangle pipe:pipes){
        g.setColor(Color.green.darker());
        g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
        }
    }
    
    public void addPipe(boolean start){
        int space = 300; 
        int width = 100;
        int height = 50 + rand.nextInt(300);
        if(start){
            pipes.add(new Rectangle( set.WIDTH + width + pipes.size()*300, set.HEIGHT - height - 120, width, height));
            pipes.add(new Rectangle( set.WIDTH + width + (pipes.size()-1)*300, 0, width, set.HEIGHT - height - space));
        }else{
            pipes.add(new Rectangle(pipes.get(pipes.size() -1).x + 600, set.HEIGHT - height - 120, width, height));
            pipes.add(new Rectangle(pipes.get(pipes.size()-1).x, 0, width, set.HEIGHT - height - space));
        }
        
    }

    void clear() {
        pipes.clear();
    }
    
    
}

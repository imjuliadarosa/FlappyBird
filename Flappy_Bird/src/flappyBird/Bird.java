package flappyBird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Julia
 */
public class Bird {
    public Rectangle bird;
    public Settings set;

    public Bird() {
        set = new Settings();
        bird = new Rectangle( set.WIDTH / 2 -10, set.HEIGHT / 2 -10, 20, 20);
    }
    
    int getY(){
        return bird.y;
    }
    
    int getX(){
        return bird.x;
    }
    
    void setY(int y,int i){
        bird.y= y+i;
    }
    void setX(int x,int i){
        bird.x= x+i;
    }

    Rectangle getBird() {
        return bird;
    }

    int getHeight() {
        return bird.height;
    }

    int getWidth() {
        return bird.width;
    }
    
    public void paintBird(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);
    }

}

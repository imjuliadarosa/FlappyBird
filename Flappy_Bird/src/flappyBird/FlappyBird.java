/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.Timer;
/**
 *
 * @author Julia
 */
public class FlappyBird implements ActionListener, MouseListener, KeyListener{
    
    public static FlappyBird flappyBird;
    public Renderer renderer;
    private Bird bird;
    public int ticks, yMotion, score;
    private final Meteor meteors;
    private final Pipe pipes;
    public boolean gameOver, started, paused = false;
    public Settings set;  
    
    
    // construtor do objeto
    public FlappyBird(){
        
        JFrame jframe = new JFrame();
        Timer timer = new Timer(30, this);
        renderer = new Renderer();
        set = new Settings();
        jframe.add(renderer);
        jframe.setTitle("Flappy Bird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(set.WIDTH, set.HEIGHT);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        
        bird = new Bird();
        pipes = new Pipe();
        meteors = new Meteor();
        pipes.addPipe(true);
        pipes.addPipe(true);
        meteors.addMeteor();
        
        
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        int speed=10;
        ticks++;
        if(started&&!paused&&!gameOver){
            if(pipes.running(speed)){
                pipes.addPipe(false);
            }


            if(meteors.running(speed)){
                meteors.addMeteor();
            }
        
            if(ticks % 2==0 && yMotion <15){
                yMotion += 2;
            }
        
        
        
        
        bird.setY(bird.getY(),yMotion);
        
        
            //verifica se o passaro passou entre os canos
            if(pipes.passThrough(bird.getBird())){
                score++;
            }
            
            //verifica se o passaro bateu nos canos ou no meteoro
            if(pipes.intersects(bird.getBird())||meteors.intersects(bird.getBird())){
                gameOver=true;
            }
       
        if(bird.getY() > set.HEIGHT - 120 - bird.getHeight() || bird.getY() < 0){
            gameOver = true;
        }
        if(gameOver){
            bird.setY(set.HEIGHT - 120 - bird.getHeight(),0);
        }
        }
        renderer.repaint();
        
    }
    
    public void repaint(Graphics g) {
        //System.out.println("The game is on!");
        // criando a cor de fundo do jframe, ou o ceu
        g.setColor(Color.cyan);
        g.fillRect(0, 0,  set.WIDTH, set.HEIGHT);
        
        //criando o chão
        g.setColor(Color.ORANGE);
        g.fillRect(0, set.HEIGHT - 120,  set.WIDTH, 150);
        //grama
        g.setColor(Color.green);
        g.fillRect(0, set.HEIGHT - 120,  set.WIDTH, 20);
        
        //criando passaro, canos e meteoros
            bird.paintBird(g);
            pipes.paintPipe(g);
            meteors.paintMeteor(g);
        
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 50));
        
        if(gameOver){
            g.drawString("game over", 275 , set.HEIGHT/2-50);
            g.drawString("click to restart", 275 , set.HEIGHT/2-10);
        }
        if(!started){
            g.drawString("click to start", 275 , set.HEIGHT/2-50);
        }
        
        if(paused){
            g.drawString("paused", 275 , set.HEIGHT/2-50);
            g.drawString("press ESC to resume", 275 , set.HEIGHT/2-10);
        }
        
        if(!gameOver && started){
            g.drawString(String.valueOf(score),  set.WIDTH/2 - 25, 100);
        }
        
    }
    private void jump() {
        if(gameOver){
            
            bird = new Bird();
            pipes.clear();
            meteors.clear();
            yMotion=0;
            score = 0;
            pipes.addPipe(true);
            pipes.addPipe(true);
            meteors.addMeteor();
            gameOver = false;
        }
        if(!started){
            started=true;
        }else if (!gameOver){
            if(yMotion > 0){
                yMotion = 0;
            }
            yMotion -= 10;
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        jump();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            jump();
        }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            //esc para pausar
                pause();
        }
        
    }

    private void pause() {
        if(paused){
            paused = false;
            
        }else{
            paused = true;
            
        }
    }
}

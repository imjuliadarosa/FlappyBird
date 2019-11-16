/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;
/**
 *
 * @author Julia
 */
public class FlappyBird implements ActionListener, MouseListener, KeyListener{
    //criando o objeto
    public static FlappyBird flappyBird;// redundancia?
    // criando e definindo valores imutaveis para o Jframe
    //criando renderer em fb
    public Renderer renderer;
    //criando the bird
    public Rectangle bird;
    // movimentação
    public int ticks, yMotion, score;
    // criando o vetor de canos
    public ArrayList<Rectangle> columns;
    //validadores
    public boolean gameOver, started;
    // randomização
    public Random rand;
    public Settings set;
    
    
    
    // construtor do objeto
    public FlappyBird(){
        
        JFrame jframe = new JFrame();
        Timer timer = new Timer(30, this);
        set = new Settings();
        renderer = new Renderer();
        rand = new Random();
        jframe.add(renderer);
        jframe.setTitle("Flappy Bird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(set.WIDTH, set.HEIGHT);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);
        
        bird = new Rectangle( set.WIDTH / 2 -10, set.HEIGHT / 2 -10, 20, 20);
        columns = new ArrayList<Rectangle>();
        addColumn(true);
        addColumn(true);
        
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int speed=10;
        
        ticks++;
        if(started){
        for( int i = 0; i<columns.size(); i++){
            Rectangle column = columns.get(i);
            column.x-= speed;
        }
        
        if(ticks % 2==0 && yMotion <15){
            yMotion += 2;
        }
        
        for( int i = 0; i<columns.size(); i++){
            Rectangle column = columns.get(i);
            if(column.x + column.width<0){
                columns.remove(column);
                if(column.y == 0){
                    addColumn(false);
                }
            }
        }
        
        bird.y+= yMotion;
        
        for(Rectangle column : columns){
            
            if(column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 -10 && bird.x + bird.width/ 2 < column.x +column.width/2 + 10){
                score++;
            }
            
            if(column.intersects(bird)){
                gameOver = true;
                bird.x = column.x - bird.width;
            }
        }
        if(bird.y > set.HEIGHT - 120 - bird.height || bird.y < 0){
            gameOver = true;
        }
        if(gameOver){
            bird.y = set.HEIGHT - 120- bird.height;
        }
        }
        renderer.repaint();
        
    }
    
    public void addColumn(boolean start){
        int space = 300; 
        int width = 100;
        int height = 50 + rand.nextInt(300);
        if(start){
            columns.add(new Rectangle( set.WIDTH + width + columns.size()*300, set.HEIGHT - height - 120, width, height));
        columns.add(new Rectangle( set.WIDTH + width + (columns.size()-1)*300, 0, width, set.HEIGHT - height - space));
        }else{
            columns.add(new Rectangle(columns.get(columns.size() -1).x + 600, set.HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size()-1).x, 0, width, set.HEIGHT - height - space));
        }
        
    }
    
    public void paintColumn(Graphics g, Rectangle column){
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
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
        
        //criando the bird in graphics
        g.setColor(Color.yellow);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);
        
        //gerando colunas
        for(Rectangle column : columns){
            paintColumn(g,column);
        }
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 50));
        
        if(gameOver){
            g.drawString("game over", 275 , set.HEIGHT/2-50);
        }
        if(!started){
            g.drawString("click to start", 275 , set.HEIGHT/2-50);
        }
        
        if(!gameOver && started){
            g.drawString(String.valueOf(score),  set.WIDTH/2 - 25, 100);
        }
        
    }
    private void jump() {
        if(gameOver){
            bird = new Rectangle( set.WIDTH / 2 -10, set.HEIGHT / 2 -10, 20, 20);
            columns.clear();
            yMotion=0;
            score = 0;
            addColumn(true);
            addColumn(true);
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
        }
    }
}

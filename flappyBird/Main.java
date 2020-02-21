/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappyBird;

import static flappyBird.FlappyBird.flappyBird;
import flappyBird.JSON;
import flappyBird.Score;
import flappyBird.Persistencia;
/**
 *
 * @author Julia
 */
public class Main {
     public static void main(String[] args) {
        //instanciando o objeto
        flappyBird = new FlappyBird();
        JSON json = new JSON();
        Persistencia persistencia = json;
        Score score = new Score();
        persistencia.gravar(score);
        score=persistencia.lerum();
        System.out.print(score.getUser()+" "+score.getPoints());
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappyBird;

import java.util.ArrayList;

/**
 *
 * @author Julia
 */
public interface Persistencia {

        public boolean gravar(ArrayList<Score> scores);

        public ArrayList<Score> ler();

}

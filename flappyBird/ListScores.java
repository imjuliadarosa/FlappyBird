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
public class ListScores {

    public ListScores() {
    }
    
    public Score Record( ArrayList <Score> scores){
        Score record = new Score();
        for(Score score: scores){
            if(score.getPoints()>record.getPoints()){
                record = score;
            }
        }
        return record;
    }
    
    public ArrayList<Score> scoresUser(ArrayList <Score> scores, String user){
        ArrayList<Score> scoreUser = new ArrayList();
        for(Score score: scores){
            if(score.getUser().equals(user)){
                scoreUser.add(score);
            }
        }
        return scoreUser;
    
    }
    public void deleteScoreUser(ArrayList <Score> scores, String user){
        for(Score score: scores){
            if(score.getUser().equals(user)){
                scores.remove(score);
            }
        }
    }
}

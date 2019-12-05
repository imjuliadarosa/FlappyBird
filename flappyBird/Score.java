/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappyBird;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Julia
 */
public class Score {
    private int points;
    private Date date;
    private String user;

    public Score() {
        this.points = 0;
        this.user = "Guest";
        Calendar cal = Calendar.getInstance();
        this.date = new Date(cal.getTimeInMillis());
    }

    public Score(String user) {
        this.points = 0;
        this.user = user;
        Calendar cal = Calendar.getInstance();
        this.date = new Date(cal.getTimeInMillis());
    }
    
    public Score(String user,int points) {
        this.points = points;
        this.user = user;
        Calendar cal = Calendar.getInstance();
        this.date = new Date(cal.getTimeInMillis());
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setDate(String data, String hora) {
        String[] vetor = data.split("/");
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.DAY_OF_MONTH, Integer.parseInt(vetor[0]));
        calendario.set(Calendar.MONTH, Integer.parseInt(vetor[1]) - 1);
        calendario.set(Calendar.YEAR, Integer.parseInt(vetor[2]));
        vetor = hora.split(":");
        calendario.set(Calendar.HOUR_OF_DAY, Integer.parseInt(vetor[0]));
        calendario.set(Calendar.MINUTE, Integer.parseInt(vetor[1]));
        calendario.set(Calendar.SECOND, Integer.parseInt(vetor[2]));
        Date dataAux = calendario.getTime();
        this.date = dataAux;
    }
    
    
    
    
}

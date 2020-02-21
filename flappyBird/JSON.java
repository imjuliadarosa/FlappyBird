package flappyBird;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Julia
 */
public class JSON implements Persistencia {

    @Override
    public boolean gravar(ArrayList<Score> scores) {
            System.out.print("Run");
        try {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                FileWriter gravarArq = new FileWriter("scores.json");
                String data;
                String hora;
                for (Score score : scores) {
                    data = "" + score.getDate().getDay() + "/" + score.getDate().getMonth() + "/" + score.getDate().getYear();
                    hora = ""+ score.getDate().getHours()+":"+score.getDate().getMinutes()+":"+score.getDate().getSeconds();
                    gravarArq.write("" + score.getUser() + ";" +score.getPoints()+ ";" +data+";"+hora);
                }
                gravarArq.close ();
                return true;
            } catch (IOException e) {
                System.out.print("Hide");
                return false;
            }
    }

    public boolean gravar(Score score) {
        System.out.print("Run");
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            FileWriter gravarArq = new FileWriter("scores.json");
            String data;
            String hora;
                data = "" + score.getDate().getDay() + "/" + score.getDate().getMonth() + "/" + score.getDate().getYear();
                hora = "" + score.getDate().getHours() + ":" + score.getDate().getMinutes() + ":" + score.getDate().getSeconds();
                gravarArq.write("" + score.getUser() + ";" + score.getPoints() + ";" + data + ";" + hora);
            gravarArq.close();
            return true;
        } catch (IOException e) {
            System.out.print("Hide");
            return false;
        }
    }
    
    @Override
    public ArrayList<Score> ler() {
        ArrayList<Score> scores;
        Score score;
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("scores.json"));
            String linha = bufferedReader.readLine();
             scores= new ArrayList <Score>();
			while (linha != null) {
				String [] leitura = linha.split(";");
				score = new Score();
                                score.setUser(leitura[0]);
				score.setPoints(Integer.parseInt(leitura[1]));
                                score.setDate(leitura[2], leitura[3]);
				scores.add(score);
				linha = bufferedReader.readLine();
			}
            bufferedReader.close();
            return scores;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo : %s.\n", e.getMessage());
            return null;
        }
    }
        
    @Override
        public Score lerum() {
        Score score = new Score();
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("scores.json"));
            String linha = bufferedReader.readLine();
			while (linha != null) {
				String [] leitura = linha.split(";");
                                score.setUser(leitura[0]);
				score.setPoints(Integer.parseInt(leitura[1]));
                                score.setDate(leitura[2], leitura[3]);
				linha = bufferedReader.readLine();
			}
            bufferedReader.close();
            return score;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo : %s.\n", e.getMessage());
            return null;
        }
        
    }
}

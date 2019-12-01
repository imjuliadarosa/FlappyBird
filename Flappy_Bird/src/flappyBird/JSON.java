package flappyBird;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Julia
 */
public class JSON implements Persistencia {

    @Override
    public boolean gravar(int score) {
        try {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                FileWriter gravarArq = new FileWriter("agenda.json");
                gravarArq.write(score);
                gravarArq.close ();
                return true;
            } catch (IOException e) {
                return false;
            }
    }

    @Override
    public int ler() {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("agenda.json"));
            int score = Integer.parseInt(bufferedReader.readLine());
            bufferedReader.close();
            return score;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo : %s.\n", e.getMessage());
            return 0;
        }
    }
}

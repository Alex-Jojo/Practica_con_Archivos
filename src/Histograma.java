import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Histograma{
    public static void main(String[] args){
        String name = " divina_comedia_sp.txt";
        Map<Integer, Integer> words = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(name))){
            String line;
            while((line = br.readLine())!=null){
                String[] wordsss = line.split("\\s+");
                for (String word : wordsss) {
                    word = word.replaceAll("[^a-zA-Z]", "");
                    int num = word.length();
                    if(num>=2 && num<=10){
                        words.put(num, words.getOrDefault(num, 0)+1);
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        for(Map.Entry<Integer, Integer> word : words.entrySet()){
            int num = word.getKey();
            int reps = word.getValue();
            int rep = word.getValue()/1000;

            System.out.println("Tamaño "+num+":\trepeticiones ("+reps+"):\t"+"*".repeat(rep));
        }
    }

}
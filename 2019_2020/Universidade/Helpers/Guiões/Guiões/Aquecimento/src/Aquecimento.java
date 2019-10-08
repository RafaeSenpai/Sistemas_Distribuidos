import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Aquecimento {
    public static void main(String[] args){
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));
        String line;

        try{
            while( (line = input.readLine()) != null )
                System.out.println(line);
        }
        catch(IOException e){
            System.err.println("Error reading line from stdin");
            e.printStackTrace();
        }
    }
}

package sample;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Builder{

    private HashMap<Integer, String> racers;
    Builder(HashMap<Integer, String> racers){
        this.racers = racers;
        try{
            build();
        }catch(URISyntaxException e){System.out.println(e);}
    }

    //Loops through data
    //Stores the unique jersey number as the key,
    // and the previous race finishes as one long string value
    private void build() throws URISyntaxException {
        String line = "";
        String csvSplit = ",";
        String[] races;
        URI url = getClass().getResource("/resources/data/Data.txt").toURI();
        File statsFile = new File(url);
        try (
            BufferedReader br = new BufferedReader(new FileReader(statsFile)))
            {
            while ((line = br.readLine()) != null) {
                races = line.split(csvSplit);
                racers.put(Integer.valueOf(races[0]),races[1]);
            }
        }
        catch (IOException io) {
            System.out.println("Reader Error: " + io);
        }
    }
}

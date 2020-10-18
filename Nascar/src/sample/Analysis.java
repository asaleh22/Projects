package sample;

import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.ArrayList;

public class Analysis{
    private  HashMap<Integer,String> racers;
    private Integer WrongNumber;
    private ArrayList<Integer> group;
    private Text groupDisplay;

    void setForAnalysis(ArrayList<Integer> group, HashMap<Integer, String> racers, Text groupDisplay){
        this.group = group;
        this.racers = racers;
        this.groupDisplay = groupDisplay;
    }

    //loops through given subgroup and prints the racer race finishes
    public boolean ShowStats(){
        if(!determineValid()){
            Main.errorNumber = WrongNumber;
            return false;
        }
        groupDisplay.setText("");
        for(Integer val : group){
            groupDisplay.setText(groupDisplay.getText() + "\n\n" + racers.get(val));
        }
        return true; //success
    }
    //insures the user input is from a valid racer
    private boolean determineValid(){
        for(Integer val : group){
            if(!racers.containsKey(val)){
                WrongNumber = val;
                return false;
            }
        }
        return true;
    }
}

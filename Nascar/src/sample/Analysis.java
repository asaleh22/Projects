package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.ArrayList;

public class Analysis{
    private  HashMap<Integer,String[]> racers;
    private Integer WrongNumber;
    private ArrayList<Integer> group;
    private Text groupDisplay;
    private ArrayList<VBox> labelContainer;
    private int containerIndex;
    void setForAnalysis(ArrayList<Integer> group, HashMap<Integer, String[]> racers, Text groupDisplay,
                        ArrayList<VBox> labelContainer, int containerIndex){
        this.group = group;
        this.racers = racers;
        this.groupDisplay = groupDisplay;
        this.labelContainer = labelContainer;
        this.containerIndex = containerIndex;
    }

    //loops through given subgroup and prints the racer race finishes
    public boolean ShowStats(){
        if(!determineValid()){
            Main.errorNumber = WrongNumber;
            return false;
        }
        labelNames();
        groupDisplay.setText("");
        groupDisplay.setText(groupDisplay.getText() + displayArray(racers.get(group.get(0))));

        for(int i = 1; i < group.size(); ++i){
            groupDisplay.setText(groupDisplay.getText() + "\n\n" + displayArray(racers.get(group.get(i))));

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

    private String displayArray(String[] line){
        String tmp = "";
        for(int i = 1; i < line.length; ++i){
            tmp += line[i] + " ";
        }
        return tmp;
    }
    private void labelNames(){ //tmp contains keys for h_racers
        VBox holder = labelContainer.get(containerIndex);
        for(int i = 0; i < group.size(); ++i){
            Label s = (Label)holder.getChildren().get(i);
            s.setText(racers.get(group.get(i))[0] + ":");
        }
    }

}

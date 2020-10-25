package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    TextField[] racers;
    GridPane grid;
    HashMap<Integer, String[]> H_Racers;
    Builder builder;
    Text handler;
    Text texts[];
    ArrayList<VBox> names;
    TextField err = new TextField();
    Analysis analysis = new Analysis();
    static int errorNumber;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Enter racers by group");
        grid = new GridPane();
        grid.setPadding(new Insets(50, 50, 50, 50));
        grid.setVgap(15);
        grid.setHgap(20);

        racers = new TextField[30];
        texts = new Text[6];
        names = new ArrayList<>();
        setRacers(0,0,false);    //define racer textfields.

        Button submit = new Button("Submit");
        submit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e){
                HandleSubmit();
            }
        });

        handler = new Text("Enter racer number in groups of five. Legend below.");
        handler.getStyleClass().add("custom");
        VBox vb = new VBox();
        vb.setPrefSize(180,100);
        vb.setMaxSize(100,100);
        vb.setMinSize(180,100);
        handler.setTextAlignment(TextAlignment.CENTER);
        handler.setWrappingWidth(180);
        vb.getChildren().add(handler);
        vb.setStyle("-fx-background-color:black");
        vb.setAlignment(Pos.CENTER);

        GridPane.setConstraints(vb,3,6);
        grid.getChildren().add(vb);

        H_Racers = new HashMap<>();
        builder = new Builder(H_Racers);

        setDataContainer(0);
        setLegend();

        myThread m = new myThread(); //Gate keeping mechanism, forcing user to input fiv
        m.start();
        String url = getClass().getResource("/resources/images/flag.jpg").toString();
        grid.setStyle("-fx-background-image: url(" + url + "); -fx-background-repeat: stretch; -fx-background-size: cover;");

        Button exit = new Button();
        GridPane.setConstraints(exit,6,5);
        HBox tmp = new HBox();
        tmp.getChildren().addAll(submit,exit);
        GridPane.setConstraints(tmp, 5, 6);
        tmp.setSpacing(50);
        grid.getChildren().add(tmp);
        url = getClass().getResource("/resources/images/exit.png").toString();
        exit.setGraphic(new ImageView(new Image(url,25 ,25, true, true)));

        exit.prefHeight(20);
        exit.prefWidth(20);
        exit.setMaxHeight(20);
        exit.setMaxWidth(20);
        exit.setMinHeight(20);
        exit.setMinWidth(20);
        exit.setOnMouseEntered(e-> {exit.setStyle(
                          "-fx-background-color:red;" +
                          "-fx-scale-x: 1.5;" +
                          "-fx-scale-y: 1.5;" +
                          "-fx-scale-z: 1.5;");
        });
        exit.setOnMouseExited(e ->  exit.setStyle(null));
        exit.setOnMouseClicked(e->  System.exit(0));

        Scene scene = new Scene(grid, 300, 275);
        scene.getStylesheets().add(getClass().getResource("/resources/stylesheets/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /*After user hits submit, process the input.*/
    public void HandleSubmit(){
        //if textfield had an error in prev click, reset it for this next click
        handler.setText("Enter racer number in groups of five. Legend below.");
        err.setStyle(null);

        ArrayList<Integer> arr = new ArrayList<>();
        int i = 0; //in case of an error, which textfield is throwing the error
        try {
            /*ensures user is entering integer values*/
            for (; i < racers.length; ++i) {
                if(!racers[i].getText().equals(""))
                    arr.add(Integer.valueOf(racers[i].getText()));
            }
        }
        catch(NumberFormatException e){ //user entered a letter
            err = racers[i];
            err.setStyle("-fx-background-color: red");
            handler.setText("Racer's number required.");
        }
            if(arr.size() % 5 != 0){
                handler.setText("Must enter groups of five.");
            }
            else{ //success
                //access the right text and send it and the group of people(5)
                int size = arr.size();
                while(size >= 5){
                    ArrayList<Integer> tmp = new ArrayList<>(arr.subList(size - 5, size));
                    int currIndex = (size/5) - 1;
                    size -= 5;
                    analysis.setForAnalysis(tmp, H_Racers, texts[currIndex], names, currIndex);

                    // if user enters a non existing number the static int is changed above
                    boolean found = analysis.ShowStats();
                    if(!found){
                        handler.setText("Error: " + this.errorNumber + " does not match a player");
                        //find the textfield that contains the wrong number
                        for(int b = 0; b < racers.length; ++b){
                            if(racers[b].getText().equals(String.valueOf(this.errorNumber))){
                                err = racers[b];
                                err.setStyle("-fx-background-color: red");
                            }
                        }
                    }
                }
            }
    }
    /*Recursively creates 6 rows of 5 Textfields to enter jersey numbers
    The first two is enabled the rest disabled*/
    public void setRacers(int col, int index, boolean isDisabled){
        if(col == 6)
            return;

        TextField Racer1 = new TextField();
        Racer1.setPromptText("Racer One");
        Racer1.setPrefColumnCount(10);
        GridPane.setConstraints(Racer1, col, 0);
        grid.getChildren().add(Racer1);
        Racer1.setDisable(isDisabled);


        TextField Racer2 = new TextField();
        Racer2.setPromptText("Racer Two.");
        Racer2.setPrefColumnCount(10);
        GridPane.setConstraints(Racer2, col, 1);
        grid.getChildren().add(Racer2);
        Racer2.setDisable(isDisabled);

        TextField Racer3 = new TextField();
        Racer3.setPromptText("Racer Three.");
        Racer3.setPrefColumnCount(10);
        GridPane.setConstraints(Racer3, col, 2);
        grid.getChildren().add(Racer3);
        Racer3.setDisable(isDisabled);

        TextField Racer4 = new TextField();
        Racer4.setPromptText("Racer Four.");
        Racer4.setPrefColumnCount(10);
        GridPane.setConstraints(Racer4, col, 3);
        grid.getChildren().add(Racer4);
        Racer4.setDisable(isDisabled);

        TextField Racer5 = new TextField();
        Racer5.setPromptText("Racer Five.");
        Racer5.setPrefColumnCount(10);
        GridPane.setConstraints(Racer5, col, 4);
        grid.getChildren().add(Racer5);
        Racer5.setDisable(isDisabled);

        racers[index++] = Racer1;
        racers[index++] = Racer2;
        racers[index++] = Racer3;
        racers[index++] = Racer4;
        racers[index++] = Racer5;

        setRacers(++col, index, true);
    }

    /*Recursively creates 6 HBOX Nodes to store 5 labels and 1 text object per HBOX node*/
    public void setDataContainer(int i){

        if(i == 6) return;

        HBox hb = new HBox();
        Label labels[] = new Label[5];
        for(int j = 0; j < labels.length; ++j){
            labels[j] = new Label();
            labels[j].setStyle("-fx-text-fill: white;");
        }
        labels[0].setText("Group " + (i+1) + ": ");
        VBox labelContainer = new VBox();//contains all 6 labels

        labelContainer.setStyle("-fx-border: 12px solid; -fx-border-color:red;");
        labelContainer.setPrefHeight(160);
        labelContainer.setMinHeight(160);
        labelContainer.setMaxHeight(160);

        labelContainer.setPrefWidth(60);
        labelContainer.setMinWidth(60);
        labelContainer.setMaxWidth(60);

        labelContainer.setSpacing(14);
        labelContainer.getChildren().addAll(labels);

        names.add(labelContainer);

        Text t = new Text();

        texts[i] = t;
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(t);

        hb.setPrefWidth(180);
        hb.setPrefHeight(160);
        hb.setMaxWidth(180);
        hb.setMaxHeight(160);
        hb.setMinWidth(180);
        hb.setMinHeight(160);
        hb.setStyle("-fx-background-color: black;");

        scrollPane.setPrefWidth(130);
        scrollPane.setMinWidth(130);
        scrollPane.setMaxWidth(130);

        hb.getChildren().addAll(labelContainer,scrollPane);
        grid.getChildren().add(hb);
        GridPane.setConstraints(hb, i, 9);
        setDataContainer(++i);
    }

    //A legend linking jersey numbers with the correct racers
    public void setLegend(){
        String textString = "";
        int col = 0;
        int i = 0;

        for (Map.Entry<Integer, String[]> pair: H_Racers.entrySet()) {
            int num = pair.getKey();
            String pos[] = pair.getValue();
            textString += pos[0] + ":" + num + "\n";
            ++i;
            if(i == 6){
                Text text = new Text(textString);
                text.setStyle("-fx-fill: red;");
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setContent(text);
                scrollPane.setFitToWidth(false);
                scrollPane.setPrefWidth(180);
                scrollPane.setPrefHeight(180);
                scrollPane.setMaxWidth(180);
                scrollPane.setMaxHeight(180);
                scrollPane.setMinWidth(180);
                scrollPane.setMinHeight(180);

                GridPane.setConstraints(scrollPane,col,10);
                grid.getChildren().add(scrollPane);

                ++col;
                i = 0;
                textString = "";
            }
            if(col == 6)
                return;
        }

    }

    //INNER CLASS THREAD
    //Makes sure user can only enter one group of 5 before moving on.
    class myThread extends Thread{
        TextField group1[],group2[],group3[],group4[],group5[],group6[];
        myThread(){
            group1 = new TextField[5];
            group2 = new TextField[5];
            group3 = new TextField[5];
            group4 = new TextField[5];
            group5 = new TextField[5];
            group6 = new TextField[5];
            System.arraycopy(racers, 0, group1, 0, 5);
            System.arraycopy(racers, 5, group2, 0, 5);
            System.arraycopy(racers, 10, group3, 0, 5);
            System.arraycopy(racers, 15, group4, 0, 5);
            System.arraycopy(racers, 20, group5, 0, 5);
            System.arraycopy(racers, 25, group6, 0, 5);
        }

       private boolean groupFull(TextField group[]){

            for(TextField v : group){
                if(v.getText().equals("")) {
                    return false;
                }
            }
            return true;
        }

       private void setActive(TextField group[], boolean setActive){
            boolean setDisable = setActive == true ? false : true;
            for(TextField v : group)
                v.setDisable(setDisable);
        }

        @Override
        public void run() {
            while(true){
                setActive(group2, groupFull(group1));
                setActive(group3,groupFull(group2) && !group2[0].isDisable());
                setActive(group4,groupFull(group3) && !group3[0].isDisable());
                setActive(group5,groupFull(group4) && !group4[0].isDisable());
                setActive(group6,groupFull(group5) && !group5[0].isDisable());
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

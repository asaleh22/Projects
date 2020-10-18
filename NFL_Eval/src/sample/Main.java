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
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;


public class Main extends Application {
    private Stage primaryStage;
    private Scene chooseScreen, showAnalysis;
    private ImageView lHalf;
    private ImageView rHalf;
    private int LIndex = 0;
    private int RIndex = 0;
    private Image imgs[];
    private TextArea LeftStats, RightStats;
    private NFL_Eval Evaluator;
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        VBox root = new VBox();
        String url = getClass().getResource("/resources/icons/rootBackground.png").toString();
        root.setStyle("-fx-background-image: url(" + url + "); -fx-background-repeat: stretch;" +
                      "-fx-background-size: cover");

        HBox TopRow = new HBox(); //top row
        HBox lCol = new HBox();
        HBox rCol = new HBox();

        HBox MatchUp = new HBox(); //middle row where images are placed

        HBox BottomRow = new HBox(); //bottom row
        HBox lCol_bot = new HBox();
        HBox rCol_bot = new HBox();

        root.setSpacing(20);
        root.getChildren().addAll(TopRow, MatchUp, BottomRow);
        root.setAlignment(Pos.CENTER);

        lHalf = new ImageView();
        rHalf = new ImageView();
        lHalf.setFitWidth(600);
        lHalf.setFitHeight(445);
        rHalf.setFitWidth(600);
        rHalf.setFitHeight(445);

        imgs = new Image[32];
        try{fillImage();}
        catch(URISyntaxException e){System.out.println(e);} //fill the imgs array
        lHalf.setImage(imgs[0]);
        rHalf.setImage(imgs[0]);
        Button upLeft = new Button();
        Button upRight = new Button();
        Button downLeft = new Button();
        Button downRight = new Button();
        Button next = new Button();

        downLeft.setPrefSize(350,35);
        downRight.setPrefSize(350,35);
        upLeft.setPrefSize(350,35);
        upRight.setPrefSize(350,35);

        downLeft.setMinSize(350,35);
        downRight.setMinSize(350,35);
        upLeft.setMinSize(350,35);
        upRight.setMinSize(350,35);

        downLeft.setMaxSize(350,35);
        downRight.setMaxSize(350,35);
        upLeft.setMaxSize(350,35);
        upRight.setMaxSize(350,35);

        lCol.getChildren().add(upLeft);
        rCol.getChildren().add(upRight);

        Button exitButton = new Button();

        TopRow.setAlignment(Pos.TOP_CENTER);
        TopRow.setSpacing(40);
        TopRow.getChildren().addAll(lCol,rCol, exitButton);

        MatchUp.getChildren().addAll(lHalf,rHalf);
        MatchUp.setAlignment(Pos.CENTER);

        lCol_bot.getChildren().add(downLeft);
        rCol_bot.getChildren().add(downRight);
        rCol_bot.getChildren().add(next);
        next.setPrefSize(50,30);
        HBox.setMargin(next,new Insets(60,60,0,0));
        BottomRow.getChildren().addAll(lCol_bot,rCol_bot);
        BottomRow.setSpacing(30);
        BottomRow.setAlignment(Pos.BOTTOM_CENTER);


        HBox.setMargin(upLeft,new Insets(5,5,5,200));
        HBox.setMargin(upRight,new Insets(5,100,5,5));
        HBox.setMargin(downLeft,new Insets(5,5,5,200));
        HBox.setMargin(downRight,new Insets(5,100,5,5));

        upRight.setOnMouseClicked(e   ->{++RIndex; changeRightImage();});
        downRight.setOnMouseClicked(e ->{--RIndex; changeRightImage();});
        upLeft.setOnMouseClicked(e    ->{++LIndex; changeLeftImage();});
        downLeft.setOnMouseClicked(e  ->{--LIndex; changeLeftImage();});

        //Sets the Teams hashmap in the object for repeated use
        Evaluator = new NFL_Eval();
        try{
            Evaluator.setData();
        }catch(URISyntaxException e){ System.out.println(e);}


        next.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<>() {
            @Override public void handle(MouseEvent e){
                showAnalysis = new Scene(nextScene(lHalf.getImage().getUrl().toString(),rHalf.getImage().getUrl().toString()),600,600);
                showAnalysis.getStylesheets().add(getClass().getResource("/sample/stylesheets/myStyle.css").toExternalForm());
                Evaluator.start(LIndex, RIndex, LeftStats, RightStats);
                LeftStats.positionCaret(0);
                RightStats.positionCaret(0);
                primaryStage.setScene(showAnalysis);
                primaryStage.setFullScreen(true);
            }
        });
        setButtonStyles(downLeft,downRight,upLeft,upRight, next, exitButton);

        chooseScreen = new Scene(root,600,600); //creates the next scene

        primaryStage.setScene(chooseScreen);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    //Match Up screen
    HBox nextScene(String teamA, String teamB){
        HBox root = new HBox();
        VBox lHalf = new VBox();
        VBox rHalf = new VBox();

        lHalf.setFillWidth(true);
        lHalf.setMaxWidth(650);
        lHalf.setMinWidth(650);
        lHalf.setPrefWidth(650);

        rHalf.setFillWidth(true);
        rHalf.setPrefWidth(650);
        rHalf.setMaxWidth(650);
        rHalf.setMinWidth(650);

        LeftStats = new TextArea();
        RightStats = new TextArea();
        LeftStats.setEditable(false);
        RightStats.setEditable(false);

        LeftStats.setTextFormatter(createTextFormatter());
        RightStats.setTextFormatter(createTextFormatter());

        lHalf.getChildren().add(LeftStats);
        rHalf.getChildren().add(RightStats);

        lHalf.setStyle("-fx-background-image: url(" + teamA + "); -fx-background-repeat: stretch;" +
                       "-fx-background-size: cover");
        rHalf.setStyle("-fx-background-image: url(" + teamB + "); -fx-background-repeat: stretch;" +
                       "-fx-background-size: cover");

        LeftStats.setPrefSize(300,500);
        RightStats.setPrefSize(300,500);

        VBox.setMargin(LeftStats, new Insets(50));
        VBox.setMargin(RightStats,new Insets(50));

        Button returnButton = new Button();
        String url = getClass().getResource("/resources/icons/back.png").toString();
        returnButton.setGraphic(new ImageView(new Image(url,20,20,true,true)));

        returnButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e){
                primaryStage.setScene(chooseScreen);
                primaryStage.setFullScreen(true);
            }
        });
        returnButton.setAlignment(Pos.BOTTOM_LEFT);
        returnButton.setOnMouseEntered(e-> returnButton.setStyle("-fx-background-color: green;" +
                                                                 "-fx-border-color: blue;"));
        returnButton.setOnMouseExited(e-> returnButton.setStyle(null));
        VBox.setMargin(returnButton,new Insets(50));
        lHalf.getChildren().add(returnButton);

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(lHalf,rHalf);
        return root;
    }

    //reads the teams folder and stores all images in an images array
    private void fillImage() throws URISyntaxException{
        int i = 0;
        Path dir = Paths.get(getClass().getResource("/resources/teams").toURI());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
            for (Path entry: stream) {
                imgs[i] = new Image(getClass().getResource("/resources/teams/" + entry.getFileName().toString()).toString());
                ++i;
            }
        }catch(NotDirectoryException e) {
            System.out.println("NotDirectory: " + e.getCause());
        }
        catch(IOException e) {
            System.out.println("IOException: " + e.getCause());
        }
        catch(SecurityException e) {
            System.out.println("SecurityException: " + e.getCause());
        }
        catch(DirectoryIteratorException e) {
            System.out.println("IteratorException: " + e.getCause());
        }

    }

    //Changes image in imageview and loops the array.
    private void changeLeftImage(){
        if(LIndex > 31)
            LIndex = 0;
        else if(LIndex < 0)
            LIndex = 31;
        lHalf.setImage(imgs[LIndex]);
    }

    //Changes image in imageview and loops the array.
    private void changeRightImage(){
        if(RIndex> 31)
            RIndex = 0;
        else if(RIndex < 0)
            RIndex = 31;
        rHalf.setImage(imgs[RIndex]);
    }

    private void setButtonStyles(Button bottomLeft,Button bottomRight,Button topLeft, Button topRight, Button next, Button exit){
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: yellow; -fx-border-color: blue;";

        String url = getClass().getResource("/resources/icons/down.png").toString();
        Image down = new Image(url,40,40,true,true);

        bottomLeft.setGraphic(new ImageView(down));

        bottomLeft.setOnMouseEntered(e-> bottomLeft.setStyle(HOVERED_BUTTON_STYLE));
        bottomLeft.setOnMouseExited(e-> bottomLeft.setStyle(null));

        //button two
        bottomRight.setGraphic(new ImageView(down));

        bottomRight.setOnMouseEntered(e-> bottomRight.setStyle(HOVERED_BUTTON_STYLE));
        bottomRight.setOnMouseExited(e-> bottomRight.setStyle(null));
        //button three

        ImageView up = new ImageView();
        up.setImage(down);
        up.setRotate(up.getRotate() + 180);
        topLeft.setGraphic(up);

        topLeft.setOnMouseEntered(e-> topLeft.setStyle(HOVERED_BUTTON_STYLE));
        topLeft.setOnMouseExited(e-> topLeft.setStyle(null));
        //button four

        up = new ImageView();
        up.setImage(down);
        up.setRotate(up.getRotate() + 180);
        topRight.setGraphic(up);

        topRight.setOnMouseEntered(e-> topRight.setStyle(HOVERED_BUTTON_STYLE));
        topRight.setOnMouseExited(e-> topRight.setStyle(null));

        //button next
        url = getClass().getResource("/resources/icons/next.png").toString();
        next.setGraphic(new ImageView(new Image(url,20 ,20, true, true)));
        next.setOnMouseEntered(e-> next.setStyle("-fx-background-color: green;" +
                                                 "-fx-border-color: blue;"));
        next.setOnMouseExited(e-> next.setStyle(null));

        //button exit
        url = getClass().getResource("/resources/icons/exit.png").toString();
        exit.setGraphic(new ImageView(new Image(url,20 ,20, true, true)));
        exit.setOnMouseEntered(e-> exit.setStyle("-fx-background-color: red;" +
                                                 "-fx-border-color: blue;"));
        exit.setOnMouseExited(e-> exit.setStyle(null));
        exit.setOnMouseClicked(e-> System.exit(0));
    }

    private static <T> TextFormatter<T> createTextFormatter() {

        //if a new line contains too many characters, cut it down to a readable end
        return new TextFormatter<>(change -> {
            if (change.isAdded()) {
                if (change.getText().indexOf('\n') > -1) { //if /n is found
                    if(change.getText().length() > 40)
                        change.setText(change.getText().substring(0,36) + change.getText().substring(change.getText().length()-4, change.getText().length()));
                }
            }
            return change;
        });
    }

    public static void main(String[] args) {
        launch(args);
    }


}

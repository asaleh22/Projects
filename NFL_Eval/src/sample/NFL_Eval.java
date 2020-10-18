package sample;

import javafx.scene.control.TextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public class NFL_Eval {

    private HashMap<String,Team> Teams;
    private Run_Analysis gameStats;
    //Index in array correspond to id of team
    //given the teams, sets the data in objects then starts the analysis
    NFL_Eval(){
       Teams = buildTeams();
       gameStats = new Run_Analysis();
    }

    void start(int teamA, int teamB, TextArea lhs, TextArea rhs){
        matchUpAnalysis(Franchises.getTeam(teamA), Franchises.getTeam(teamB), lhs,rhs);
    }
    //Builds the teams objects
    //Data is taken from(and must be updated) https://www.pro-football-reference.com/ throughout the season
    //method is called once
    void setData() throws URISyntaxException{

        String csv = "Rushing_Defense.txt";
        setRushingDefense(csv);

        csv = "Passing_Defense.txt";
        setPassingDefense(csv);

        csv = "Total_Defense.txt";
        setTotalDefense(csv);

        csv = "Passing_Offense.txt";
        setPassingOffense(csv);

        csv = "Rushing_Offense.txt";
        setRushingOffense(csv);

        csv = "Total_Offense.txt";
        setTotalOffense(csv);
    }


    //Gets the names and runs them through for an analysis
    //At this point, data is properly assigned, now it will run it.
    private void matchUpAnalysis(String a, String b, TextArea lhs, TextArea rhs){
        gameStats.start(Teams.get(a), Teams.get(b),lhs,rhs);
    }

    //Enters the relevant information for this category.
    private void setTotalOffense(String csv) throws URISyntaxException{
        String line = "";
        String csvSplit = ",";
        String[] football;
        File stats = new File(getClass().getResource("/resources/stats/" + csv).toURI());

        try (
            BufferedReader br =
                    new BufferedReader(new FileReader(stats)))
        {
            String headerLine = br.readLine();

            while ((line = br.readLine()) != null) {
                football = line.split(csvSplit);

                if(Teams.containsKey(football[1])){  //football[1] is the team name
                    Team currTeam = Teams.get(football[1]);

                    currTeam.getOff().setRushAndPass(football[6], football[7], football[8], //relevant info
                            football[9], football[11], football[12],
                            football[13], football[14], football[16],
                            football[17], football[18], football[19], football[21], football[22], football[24], football[25], football[26]);
                }

            }
        }
        catch (IOException io) {
            System.out.println(io);
        }

    }

    //Enters the relevant information for this category.
    private void setRushingOffense(String csv) throws URISyntaxException{
        String line = "";
        String csvSplit = ",";
        String[] football = new String[0];
        File stats = new File(getClass().getResource("/resources/stats/" + csv).toURI());
        try(
            BufferedReader br =
                new BufferedReader(new FileReader(stats)))
        {
            String headerLine = br.readLine();

            while ((line = br.readLine()) != null) {
                football = line.split(csvSplit);

                if(Teams.containsKey(football[1])){ //team name
                    Team currTeam = Teams.get(football[1]);

                    currTeam.getOff().getRush().setRushOff(football[4], football[5], football[7], football[8], football[9]); //relevant info
                }

            }

        }
        catch (IOException io) {
            System.out.println(io);
        }

    }

    //Enters the relevant information for this category.
    private void setRushingDefense(String csv) throws URISyntaxException{
        String line = "";
        String csvSplit = ",";
        String[] football = new String[0];
        File stats = new File(getClass().getResource("/resources/stats/" + csv).toURI());

        try(
            BufferedReader br =
                        new BufferedReader(new FileReader(stats)))
            {
            String headerLine = br.readLine(); //remove header

            while ((line = br.readLine()) != null) {
                football = line.split(csvSplit);

                if(Teams.containsKey(football[1])){
                    Team currTeam = Teams.get(football[1]); //team name

                    currTeam.getDef().getRush().setRushDef(football[5], football[6], football[7]); //relevant info
                }

            }

        }
        catch (IOException io) {
            System.out.println(io);
        }

    }

    //Enters the relevant information for this category.
    private void setPassingDefense(String csv) throws URISyntaxException {

        String line = "";
        String csvSplit = ",";
        String[] football = new String[0];
        File stats = new File(getClass().getResource("/resources/stats/" + csv).toURI());

        try (
            BufferedReader br =
                    new BufferedReader(new FileReader(stats)))
            {
            String headerLine = br.readLine();

            while ((line = br.readLine()) != null) {
                football = line.split(csvSplit);

                if(Teams.containsKey(football[1])){
                    Team currTeam = Teams.get(football[1]); //team name

                    currTeam.getDef().getPass().setPassDef(football[4], football[5], football[6], //relevant info
                            football[7], football[8], football[9],
                            football[11], football[12], football[14],
                            football[15], football[16], football[17],
                            football[19], football[20], football[21], football[23]);
                }

            }
        }
        catch (IOException io) {
            System.out.println(io);
        }

    }

    //Enters the relevant information for this category.
    private void setTotalDefense(String csv) throws URISyntaxException{
        String line = "";
        String csvSplit = ",";
        String[] football = new String[0];
        File stats = new File(getClass().getResource("/resources/stats/" + csv).toURI());

        try(BufferedReader br =
                    new BufferedReader(new FileReader(stats)))
            {
             String headerLine = br.readLine();

            while ((line = br.readLine()) != null) {
                football = line.split(csvSplit);
                if(Teams.containsKey(football[1])){
                    Team currTeam = Teams.get(football[1]); //team name

                    currTeam.getDef().setRushAndPass(football[6], football[7], football[8], //relevant info
                            football[9], football[16], football[21],
                            football[22], football[24], football[25],
                            football[26]);
                }

            }
        }
        catch (IOException io) {
            System.out.println(io);
        }


    }

    //Enters the relevant information for this category.
    private void setPassingOffense(String csv) throws URISyntaxException{
        String line = "";
        String csvSplit = ",";
        String[] football = new String[0];
        File stats = new File(getClass().getResource("/resources/stats/" + csv).toURI());

        try (BufferedReader br =
                     new BufferedReader(new FileReader(stats)))
            {
            String headerLine = br.readLine();

            while ((line = br.readLine()) != null) {
                football = line.split(csvSplit);
                String team = this.converter(football[2]); //converter needed for team abbreviations.Football[2] holds team abbreviations

                if(Teams.containsKey(team)){
                    Team currTeam = Teams.get(team);

                    currTeam.getOff().initPasser();
                    currTeam.getOff().getPass().setPasser(football[1], football[10], football[11], //relevant info
                            football[12], football[13], football[14],
                            football[15], football[16], football[17],
                            football[19], football[20], football[21],
                            football[23], football[27], football[28]);
                }
                else{
                    System.out.println("COULD NOT FIND TEAM: " + team);
                }

            }

        }
        catch (IOException io) {
            System.out.println(io);
        }

    }

    //Initial build for the teams hashmap.
    //Stores the proper string as the key for each team(identical to the Franchises class)
    private HashMap<String, Team> buildTeams(){ //creates blank Team Objects.
        HashMap<String, Team> temp = new HashMap<>();

        temp.put("Chicago Bears",            new Team("Bears"));
        temp.put("Cincinnati Bengals",       new Team("Bengals"));
        temp.put("Tampa Bay Buccaneers",     new Team("Buccaneers"));
        temp.put("New York Jets",            new Team("Jets"));
        temp.put("Houston Texans",           new Team("Texans"));
        temp.put("Philadelphia Eagles",      new Team("Eagles"));
        temp.put("New Orleans Saints",       new Team("Saints"));
        temp.put("Los Angeles Rams",         new Team("Rams"));
        temp.put("Baltimore Ravens",         new Team("Ravens"));
        temp.put("Las Vegas Raiders",        new Team("Raiders"));
        temp.put("New England Patriots",     new Team("Patriots"));
        temp.put("Minnesota Vikings",        new Team("Vikings"));
        temp.put("Dallas Cowboys",           new Team("Cowboys"));
        temp.put("Indianapolis Colts",       new Team("Colts"));
        temp.put("Pittsburgh Steelers",      new Team("Steelers"));
        temp.put("Denver Broncos",           new Team("Broncos"));
        temp.put("Washington Football Team", new Team("Washington"));
        temp.put("San Francisco 49ers",      new Team("49ers"));
        temp.put("Atlanta Falcons",          new Team("Falcons"));
        temp.put("Seattle Seahawks",         new Team("Seahawks"));
        temp.put("Tennessee Titans",         new Team("Titans"));
        temp.put("Buffalo Bills",            new Team("Bills"));
        temp.put("Jacksonville Jaguars",     new Team("Jaguars"));
        temp.put("Los Angeles Chargers",     new Team("Chargers"));
        temp.put("Detroit Lions",            new Team("Lions"));
        temp.put("Cleveland Browns",         new Team("Browns"));
        temp.put("New York Giants",          new Team("Giants"));
        temp.put("Carolina Panthers",        new Team("Panthers"));
        temp.put("Arizona Cardinals",        new Team("Cardinals"));
        temp.put("Green Bay Packers",        new Team("Packers"));
        temp.put("Miami Dolphins",           new Team("Dolphins"));
        temp.put("Kansas City Chiefs",       new Team("Chiefs"));

        return temp;
    }

    //some CVs have abbreviations instead of team names.
    private String converter(String t_Name){
        switch(t_Name){

            case "CHI": return "Chicago Bears";
            case "CIN": return "Cincinnati Bengals";
            case "LAC": return "Los Angeles Chargers";
            case "ARI": return "Arizona Cardinals";
            case "NWE": return "New England Patriots";
            case "LAR": return "Los Angeles Rams";
            case "TAM": return "Tampa Bay Buccaneers";
            case "GNB": return "Green Bay Packers";
            case "SEA": return "Seattle Seahawks";
            case "ATL": return "Atlanta Falcons";
            case "DAL": return "Dallas Cowboys";
            case "CLE": return "Cleveland Browns";
            case "JAX": return "Jacksonville Jaguars";
            case "PHI": return "Philadelphia Eagles";
            case "HOU": return "Houston Texans";
            case "NYG": return "New York Giants";
            case "KAN": return "Kansas City Chiefs";
            case "DET": return "Detroit Lions";
            case "MIN": return "Minnesota Vikings";
            case "BUF": return "Buffalo Bills";
            case "LVR": return "Las Vegas Raiders";
            case "SFO": return "San Francisco 49ers";
            case "DEN": return "Denver Broncos";
            case "BAL": return "Baltimore Ravens";
            case "IND": return "Indianapolis Colts";
            case "CAR": return "Carolina Panthers";
            case "MIA": return "Miami Dolphins";
            case "NYJ": return "New York Jets";
            case "PIT": return "Pittsburgh Steelers";
            case "NOR": return "New Orleans Saints";
            case "TEN": return "Tennessee Titans";
            case "WAS": return "Washington Football Team";
            default: return "false";
        }

    }
}


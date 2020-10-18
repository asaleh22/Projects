package sample;

public class Franchises { //change numbers to follow folder to right

    private final static int _49ers     = 0;
    private final static int Bears      = 1;
    private final static int Bengals    = 2;
    private final static int Bills      = 3;
    private final static int Broncos    = 4;
    private final static int Browns     = 5;
    private final static int Buccaneers = 6;
    private final static int Cardinals  = 7;
    private final static int Chargers   = 8;
    private final static int Chiefs     = 9;
    private final static int Colts      = 10;
    private final static int Cowboys    = 11;
    private final static int Dolphins   = 12;
    private final static int Eagles     = 13;
    private final static int Falcons    = 14;
    private final static int Giants     = 15;
    private final static int Jaguars    = 16;
    private final static int Jets       = 17;
    private final static int Lions      = 18;
    private final static int Packers    = 19;
    private final static int Panthers   = 20;
    private final static int Patriots   = 21;
    private final static int Raiders    = 22;
    private final static int Rams       = 23;
    private final static int Ravens     = 24;
    private final static int Saints     = 25;
    private final static int Seahawks   = 26;
    private final static int Steelers   = 27;
    private final static int Texans     = 28;
    private final static int Titans     = 29;
    private final static int Vikings    = 30;
    private final static int Washington = 31;

    public static String getTeam(int team) { //returns the team name, which is the Key to the tables that will be used
        // Must wrap the quarterbacks some how.
        //given a team determine which image to return
        switch (team) {
            case Bears:
                return "Chicago Bears";
            case Packers:
                return "Green Bay Packers";
            case Lions:
                return "Detroit Lions";
            case Vikings:
                return "Minnesota Vikings";
            case _49ers:
                return "San Francisco 49ers";
            case Seahawks:
                return "Seattle Seahawks";
            case Rams:
                return "Los Angeles Rams";
            case Cardinals:
                return "Arizona Cardinals";
            case Saints:
                return "New Orleans Saints";
            case Panthers:
                return "Carolina Panthers";
            case Buccaneers:
                return "Tampa Bay Buccaneers";
            case Falcons:
                return "Atlanta Falcons";
            case Cowboys:
                return "Dallas Cowboys";
            case Eagles:
                return "Philadelphia Eagles";
            case Giants:
                return "New York Giants";
            case Washington:
                return "Washington Football Team";
            case Colts:
                return "Indianapolis Colts";
            case Texans:
                return "Houston Texans";
            case Jaguars:
                return "Jacksonville Jaguars";
            case Titans:
                return "Tennessee Titans";
            case Patriots:
                return "New England Patriots";
            case Bills:
                return "Buffalo Bills";
            case Dolphins:
                return "Miami Dolphins";
            case Jets:
                return "New York Jets";
            case Chiefs:
                return "Kansas City Chiefs";
            case Broncos:
                return "Denver Broncos";
            case Chargers:
                return "Los Angeles Chargers";
            case Raiders:
                return "Las Vegas Raiders";
            case Steelers:
                return "Pittsburgh Steelers";
            case Ravens:
                return "Baltimore Ravens";
            case Browns:
                return "Cleveland Browns";
            case Bengals:
                return "Cincinnati Bengals";
        }
        return "null";
    }
}
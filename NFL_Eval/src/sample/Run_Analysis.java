package sample;


import javafx.scene.control.TextArea;

public class Run_Analysis{

    //Prints everything to the command line side by side for analysis.
    //pairs the right information: Team A offense compared to Team B defense and vice versa.
     void start(Team A, Team B, TextArea lhs, TextArea rhs){
        overviewOFF(A,lhs);
        overviewDEF(B,rhs);

        addLine(lhs,"-------------------------------------------------");
        addLine(rhs,"-------------------------------------------------");

        overviewOFF(B,rhs);
        overviewDEF(A,lhs);

        addLine(lhs,"-------------------------------------------------");
        addLine(rhs,"-------------------------------------------------");

        runGameOFF(A, lhs);
        runGameDEF(B, rhs);

        addLine(lhs,"-------------------------------------------------");
        addLine(rhs,"-------------------------------------------------");

        runGameOFF(B, rhs);
        runGameDEF(A, lhs);

        addLine(lhs,"-------------------------------------------------");
        addLine(rhs,"-------------------------------------------------");

        passQBs(A, lhs);
        passDefense(B,rhs);

        addLine(lhs,"-------------------------------------------------");
        addLine(rhs,"-------------------------------------------------");

        passQBs(B, rhs);
        passDefense(A,lhs);
    }

    //aligns text
    private void addLine(TextArea t, String print) {
        t.appendText("\n" + print);
    }
    //Adds enough padding to invoke the TextFormatter
    private String align(){return "                                                        ";};

    private void overviewOFF(Team A, TextArea t){
        addLine(t, A.getName() + " Offense Overview: ");
        addLine(t,"     AVG yards per play:"            + align() + A.getOff().getYardsPerPlay());
        addLine(t, "     Total turnovers:"              + align() + A.getOff().getTurnOvers());
        addLine(t,"     Total Fumbles Lost:"            + align() + A.getOff().getFumbleslost());
        addLine(t,"     Total First Downs: "            + align() + A.getOff().getFstDtotal());
        addLine(t,"     Penalties Taken:"              + align() + A.getOff().getPenalties());
        addLine(t,"     Firsts by Penalty:"             + align() + A.getOff().getFstbyPenalty());
        addLine(t,"     Avg Drives ending in a score:"  + align() + A.getOff().getPercentofscorebyoffense());
        addLine(t,"     Drives ending in a Turnover: "  + align() + A.getOff().getPercentageofdrivesendingturnover());
    }

    private void overviewDEF(Team B, TextArea t){
        addLine(t, B.getName() + " Defense Overview:     ");
        addLine(t,"     AVG yards given up per play: "  + align() + B.getDef().getYrdsPerPlay());
        addLine(t, "     Total TakeAways:    "          + align() + B.getOff().getYardsPerPlay());
        addLine(t,"     Total Forced Fumbled: "         + align() + B.getDef().getFumbles());
        addLine(t,"     First Downs Given Up: "         + align() + B.getDef().getFst_Total());
        addLine(t,"     Penalties Forced: "            + align() + B.getDef().getPen());
        addLine(t,"     Firsts given up by penalty:  "  + align() + B.getDef().getFstByPen());
        addLine(t,"     AVG drives ending in a TD: "    + align() + B.getDef().getTdDrives_percentage());
        addLine(t,"     Drives ending in a Takeaway:  " + align() + B.getDef().getTurnOverDrives_percentage());
    }

    private void runGameOFF(Team A, TextArea area){
        addLine(area, A.getName() + " Run Offense ");
        addLine(area, "     Rushing TDS Scored: "       + align() + A.getOff().getRush().getTD());
        addLine(area, "     Rush Yrds Per Att: "        + align() + A.getOff().getRush().getYrdsPer_A());
        addLine(area,"     Rush Yrds Per Gme:  "        + align() + A.getOff().getRush().getYrdsPer_G());
        addLine(area,"     Rushing Fst: "               + align() + A.getOff().getFstdwnbyrun());
        addLine(area,"     Rush Yrds Total: "           + align() + A.getOff().getRush().getYrds());
    }

    private void runGameDEF(Team B, TextArea area){
        addLine(area, B.getName() + " Run Defense ");
        addLine(area, "     Rushing TDs given up: "         + align() + B.getDef().getRush().getTd());
        addLine(area, "     Rush Yrds given up per Att: "   + align() + B.getDef().getRush().getYrdsPer_A());
        addLine(area,"     Rush Yrds given up per Gme: "    + align() + B.getDef().getRush().getYrdsPer_G());
        addLine(area,"     Rush Fst given up: "             + align() + B.getDef().getRush().getFst_run());
    }

    private void passQBs(Team A, TextArea t){
        for(Team.Offense.Passing p : A.getOff().getPassers()){
            addLine(t, "Name: "                             + p.getName());
            addLine(t, "     Comp Percentage:"              + align() + p.getComp_Percentage());
            addLine(t, "     Passing Yards:"                + align() + p.getYards());
            addLine(t, "     Passing yrds per Attempt"      + align() + p.getYardsPerAttempt());
            addLine(t, "     Passing yrds per Complete"     + align() + p.getYardsPerCompletion());
            addLine(t,"     Passing TDS:"                   + align() + p.getTd());
            addLine(t, "     Passing td-percentage:"        + align() + p.getTd_Percentage());
            addLine(t,"     Passing INT: "                  + align() + p.getINT());
            addLine(t, "     Int percentage: "              + align() + p.getIntPercentage());
            addLine(t,"     QBR:"                           + align() + p.getQbr());
            addLine(t, "     Sacks:"                        + align() + p.getSacks());
            addLine(t, "     Longest Pass:"                 + align() + p.getLng());
            addLine(t,"     ComeBacks:"                     + align() + p.getComeBacks());
            addLine(t, "     GWD:"                          + align() + p.getGWD());
        }
    }

    private void passDefense(Team A, TextArea t){
        addLine(t, "Pass Defense");
        addLine(t, "     Attempts faced: "          + align() + A.getDef().getPass().getAtt());
        addLine(t,"     CMP_Percentage:       "     + align() + A.getDef().getPass().getCmp_Percentage());
        addLine(t,"     Yards faced:        "       + align() + A.getDef().getPass().getYds());
        addLine(t, "     Pass.TD given up: "        + align() + A.getDef().getPass().getTD());
        addLine(t, "     Pass-TD percentage:   "    + align() + A.getDef().getPass().getTD_percentage());
        addLine(t, "     INTS caught:        "      + align() + A.getDef().getPass().getInt());
        addLine(t,"     INTS percentage:  "         + align() + A.getDef().getPass().getInt_percentage());
        addLine(t,"     Yards Per ATT.: "           + align() + A.getDef().getPass().getYrdsPer_A());
        addLine(t, "     Yards Per CMP.: "          + align() + A.getDef().getPass().getYrdsPer_C());
        addLine(t,"     Yards Per GMS.: "           + align() + A.getDef().getPass().getYrdsPer_G());
        addLine(t,"     QB rating.:     "           + align() + A.getDef().getPass().getQbRating());
        addLine(t,"     QB HITS.:       "           + align() + A.getDef().getPass().getQBHits());
        addLine(t,"     QB Sacks.:      "           + align() + A.getDef().getPass().getSk());
        addLine(t, "     TKL for Loss.:  "          + align() + A.getDef().getPass().getTFL());
        addLine(t,"     Yards Gained Per ATT.: "    + align() + A.getDef().getPass().getYardsGainedPerAttempt());
        addLine(t,"     Sack Percentage:   "        + align() + A.getDef().getPass().getSackPercentage());
        addLine(t, "     Sack Percentage:   "       + align() + A.getDef().getPass().getSackPercentage());
    }
}

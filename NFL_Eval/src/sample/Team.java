package sample;
import java.util.ArrayList;

//Class Team has two nested classes Offense and Defense.
//Classes Offense and Defense each have two nested classes, Passing and Rushing.
//Each enclosing and nested class carry the relevant information to itself.

public class Team{
    private String name;
    private Defense def;
    private Offense off;

    Team(String Name){
        this.name = Name;
        def = new Defense();
        off = new Offense();
    }

    public String getName() {
        return name;
    }

    public Defense getDef() {
        return def;
    }

    public Offense getOff() {
        return off;
    }


    class Defense{

        private Rushing rush;
        private Passing pass;
        private String yrdsPerPlay;
        private String TakeAways;
        private String fumbles;
        private String fst_Total;
        private String pen;
        private String fstByPen;
        private String tdDrives_percentage;
        private String turnOverDrives_percentage;

        Defense(){
            this.rush = new Rushing();
            this.pass = new Passing();
        }

        public void setRushAndPass(String yrdsPerPlay, String TakeAways, String fumbles, String fst_Total,
                                   String fst_run, String fst_pass, String pen, String fstByPen, String tdDrives_percentage, String turnOverDrives_percentage){

            this.yrdsPerPlay = yrdsPerPlay;
            this.TakeAways = TakeAways;
            this.fumbles = fumbles;
            this.fst_Total = fst_Total;
            getRush().fst_run = fst_run;
            getPass().fst_pass = fst_pass;
            this.pen = pen;
            this.fstByPen = fstByPen;
            this.tdDrives_percentage = tdDrives_percentage;
            this.turnOverDrives_percentage = turnOverDrives_percentage;
        }

        public Rushing getRush() {
            return rush;
        }

        public Passing getPass() {
            return pass;
        }

        public String getYrdsPerPlay() {
            return yrdsPerPlay;
        }

        public String getTakeAways() {
            return TakeAways;
        }

        public String getFumbles() {
            return fumbles;
        }

        public String getFst_Total() {
            return fst_Total;
        }

        public String getPen() {
            return pen;
        }

        public String getFstByPen() {
            return fstByPen;
        }

        public String getTdDrives_percentage() {
            return tdDrives_percentage;
        }

        public String getTurnOverDrives_percentage() {
            return turnOverDrives_percentage;
        }


        class Rushing{
            private String td;
            private String yrdsPer_A;
            private String yrdsPer_G;
            private String fst_run;

            public void setRushDef(String TD, String yrdsPer_A, String yrdsPer_G){
                this.td = TD;
                this.yrdsPer_A = yrdsPer_A;
                this.yrdsPer_G = yrdsPer_G;
            }

            public String getTd() {
                return td;
            }

            public String getYrdsPer_A() {
                return yrdsPer_A;
            }

            public String getYrdsPer_G() {
                return yrdsPer_G;
            }

            public String getFst_run() {
                return fst_run;
            }
        }

        class Passing{

            private String Att;
            private String Cmp_Percentage;
            private String Yds;
            private String TD;
            private String TD_percentage;
            private String Int;
            private String Int_percentage;
            private String yrdsPer_A;
            private String yrdsPer_C;
            private String yrdsPer_G;
            private String qbRating;
            private String Sk;
            private String QBHits;
            private String TFL;
            private String yardsGainedPerAttempt;
            private String sackPercentage;
            private String fst_pass;

            public void setPassDef(String Att,String Cmp_Percentage,String Yds,String TD,
                                   String TD_percentage, String Int, String Int_percentage,
                                   String yrdsPer_A, String yrdsPer_C, String yrdsPer_G,
                                   String qbRating, String Sk, String QBHits, String TFL,
                                   String yardsGainedPerAttempt, String sackPercentage){


                this.Att = Att;
                this.Cmp_Percentage = Cmp_Percentage;
                this.Yds = Yds;
                this.TD = TD;
                this.TD_percentage = TD_percentage;
                this.Int = Int;
                this.Int_percentage = Int_percentage;
                this.yrdsPer_A = yrdsPer_A;
                this.yrdsPer_C = yrdsPer_C;
                this.yrdsPer_G = yrdsPer_G;
                this.qbRating = qbRating;
                this.Sk = Sk;
                this.QBHits = QBHits;
                this.TFL = TFL;
                this.yardsGainedPerAttempt = yardsGainedPerAttempt;
                this.sackPercentage = sackPercentage;


            }

            public String getAtt() {
                return Att;
            }

            public String getCmp_Percentage() {
                return Cmp_Percentage;
            }

            public String getYds() {
                return Yds;
            }

            public String getTD() {
                return TD;
            }

            public String getTD_percentage() {
                return TD_percentage;
            }

            public String getInt() {
                return Int;
            }

            public String getInt_percentage() {
                return Int_percentage;
            }

            public String getYrdsPer_A() {
                return yrdsPer_A;
            }

            public String getYrdsPer_C() {
                return yrdsPer_C;
            }

            public String getYrdsPer_G() {
                return yrdsPer_G;
            }

            public String getQbRating() {
                return qbRating;
            }

            public String getSk() {
                return Sk;
            }

            public String getQBHits() {
                return QBHits;
            }

            public String getTFL() {
                return TFL;
            }

            public String getYardsGainedPerAttempt() {
                return yardsGainedPerAttempt;
            }

            public String getSackPercentage() {
                return sackPercentage;
            }

            public String getFst_pass() {
                return fst_pass;
            }
        }


    }

    class Offense{

        private Passing pass;
        private Rushing rush;
        private ArrayList<Passing> passers;
        // ----------------------------------//
        private String yardsPerPlay;
        private String turnOvers;
        private String fumbleslost;
        private String fstDtotal;
        private String passesAttempt;
        private String passingYrds;
        private String passingTD;
        private String passingINT;
        private String fstDbypass;
        private String rushingattempt;
        private String rushingyrds;
        private String td_rushing;
        private String fstdwnbyrun;
        private String penalties;
        private String fstbyPenalty;
        private String percentofscorebyoffense;
        private String percentageofdrivesendingturnover;

        Offense(){
            passers = new ArrayList<Passing>();
            this.rush = new Rushing();
        }

        public void setRushAndPass(String yardsPerPlay, String takeAways, String fumbleslost, String fstDtotal,
                                   String passesAttempt,String passingYrds, String passingTD, String passingINT, String fstDbypass,
                                   String rushingattempt, String rushingyrds, String td_rushing, String fstdwnbyrun,
                                   String penalties, String fstbyPenalty, String percentofscorebyoffense, String percentageofdrivesendingturnover){


            this.yardsPerPlay = yardsPerPlay;
            this.turnOvers = takeAways;
            this.fumbleslost = fumbleslost;
            this.fstDtotal = fstDtotal;
            this.passesAttempt = passesAttempt;
            this.passingYrds = passingYrds;
            this.passingTD = passingTD;
            this.passingINT = passingINT;
            this.fstDbypass = fstDbypass;
            this.rushingattempt = rushingattempt;
            this.rushingyrds = rushingyrds;
            this.td_rushing = td_rushing;
            this.fstdwnbyrun = fstdwnbyrun;
            this.penalties = penalties;
            this.fstbyPenalty = fstbyPenalty;
            this.percentofscorebyoffense = percentofscorebyoffense;
            this.percentageofdrivesendingturnover = percentageofdrivesendingturnover;
        }

        public void initPasser(){
            this.pass = new Passing();
        }

        public Passing getPass() {
            return pass;
        }

        public Rushing getRush() {
            return rush;
        }

        public ArrayList<Passing> getPassers() {
            return passers;
        }

        public String getYardsPerPlay() {
            return yardsPerPlay;
        }

        public String getTurnOvers() {
            return turnOvers;
        }

        public String getFumbleslost() {
            return fumbleslost;
        }

        public String getFstDtotal() {
            return fstDtotal;
        }

        public String getPassesAttempt() {
            return passesAttempt;
        }

        public String getPassingYrds() {
            return passingYrds;
        }

        public String getPassingTD() {
            return passingTD;
        }

        public String getPassingINT() {
            return passingINT;
        }

        public String getFstDbypass() {
            return fstDbypass;
        }

        public String getRushingattempt() {
            return rushingattempt;
        }

        public String getRushingyrds() {
            return rushingyrds;
        }

        public String getTd_rushing() {
            return td_rushing;
        }

        public String getFstdwnbyrun() {
            return fstdwnbyrun;
        }

        public String getPenalties() {
            return penalties;
        }

        public String getFstbyPenalty() {
            return fstbyPenalty;
        }

        public String getPercentofscorebyoffense() {
            return percentofscorebyoffense;
        }

        public String getPercentageofdrivesendingturnover() {
            return percentageofdrivesendingturnover;
        }


        class Rushing{
            private String yrds;
            private String TD;
            private String yrdsPer_A;
            private String yrdsPer_G;
            private String fmb;

            public void setRushOff(String yrds, String TD, String yrdsPer_A, String yrdsPer_G, String fmb){
                this.yrds = yrds;
                this.TD = TD;
                this.yrdsPer_A = yrdsPer_A;
                this.yrdsPer_G = yrdsPer_G;
                this.fmb = fmb;

            }


            public String getYrds() {
                return yrds;
            }

            public String getTD() {
                return TD;
            }

            public String getYrdsPer_A() {
                return yrdsPer_A;
            }

            public String getYrdsPer_G() {
                return yrdsPer_G;
            }

            public String getFmb() {
                return fmb;
            }
        }

        class Passing{
            private String Name;
            private String comp_Percentage;
            private String yards;
            private String td;
            private String td_Percentage;
            private String INT;
            private String intPercentage;
            private String Lng;
            private String yardsPerAttempt;
            private String yardsPerCompletion;
            private String yardsPerGame;
            private String qbr;
            private String sacks;
            private String comeBacks;
            private String GWD;


            public void setPasser(String Name, String comp_Percentage, String yards, String td,
                                  String td_Percentage, String INT, String intPercentage, String Lng,
                                  String yardsPerAttempt, String yardsPerCompletion, String yardsPerGame,
                                  String qbr, String sacks, String comeBacks, String GWD){
                this.Name = Name;
                this.comp_Percentage = comp_Percentage;
                this.yards = yards;
                this.td = td;
                this.td_Percentage = td_Percentage;
                this.INT = INT;
                this.intPercentage = intPercentage;
                this.Lng = Lng;
                this.yardsPerAttempt = yardsPerAttempt;
                this.yardsPerCompletion = yardsPerCompletion;
                this.yardsPerGame = yardsPerGame;
                this.qbr = qbr;
                this.sacks = sacks;
                this.comeBacks = comeBacks;
                this.GWD = GWD;

                getPassers().add(this);

            }


            public String getName() {
                return Name;
            }

            public String getComp_Percentage() {
                return comp_Percentage;
            }

            public String getYards() {
                return yards;
            }

            public String getTd() {
                return td;
            }

            public String getTd_Percentage() {
                return td_Percentage;
            }

            public String getINT() {
                return INT;
            }

            public String getIntPercentage() {
                return intPercentage;
            }

            public String getLng() {
                return Lng;
            }

            public String getYardsPerAttempt() {
                return yardsPerAttempt;
            }

            public String getYardsPerCompletion() {
                return yardsPerCompletion;
            }

            public String getYardsPerGame() {
                return yardsPerGame;
            }

            public String getQbr() {
                return qbr;
            }

            public String getSacks() {
                return sacks;
            }

            public String getComeBacks() {
                return comeBacks;
            }

            public String getGWD() {
                return GWD;
            }
        }


    }



}

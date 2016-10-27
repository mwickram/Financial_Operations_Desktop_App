
package business;

/**
 *
 * @Praminda Imaduwa-Gamage
 */
public class Annuity {
    
    public static final String AMTDESC = "Monthly Deposit";
    public static final String RESULTDESC ="Final Annuity Value";
    
    
    private double amt, rate;
    private int term;
    private String errmsg;
    private double[] bbal, iearn, ebal;
                   //begining balance, interest earn, end balance
    private boolean built;
    
    public Annuity(double amt, double rate, int term){
        this.amt = amt;
        this.rate= rate;
        this.term = term;
        this.built =false;
        
        if (isValid()){
            buildAnnuity();
        }else {
            this.amt= 0;
            this.rate = 0;
            this.term = 0;
        }
    }//end of constructor
    private boolean isValid(){
        this.errmsg = "";
        if(this.amt <= 0) {
            this.errmsg += " Bad amount (<=0)" + this.amt;
        }
        if(this.rate < 0) {
            this.errmsg += " Bad rate (<0): " + this.rate;
        }
        if (this.term <= 0){
            this.errmsg += " Bad term (<=0) :" + this.term;
        }
        return this.errmsg.isEmpty();//this means if(errmsg.isEmpty())
    }
    private void buildAnnuity() {
        try {
            this.bbal = new double[this.term];//new array (this.bbal) declaration
            this.iearn = new double[this.term];
            this.ebal = new double[this.term];
            
            double morate = this.rate/12.0;
            this.bbal[0] = 0;//starting value of begining balance
            for (int i=0; i< this.term; i++){
                
                if (i > 0){
                    //past first month
                    this.bbal[i]= this.ebal[i-1];
                }
                this.iearn[i]= (this.bbal[i] + this.amt) * morate;
                this.ebal[i]= this.bbal[i] + this.amt + this.iearn[i];
            }
            this.built= true;
            
        }catch(Exception e){
            this.errmsg += " Build error: " + e.getMessage();
            this.built = false;//internal flag
        }
    }//end of buildAnnuity
    
    public double getDeposit (){
        return this.amt; 
    } 
    public double getIntRate(){
        return this.rate; 
    }
    public int getTerm() {
        return this.term;
    }
    public String getErrorMsg (){
        return this.errmsg; 
    }
    public double getFinalValue(){
        if (!this.built){
            buildAnnuity();
        }
        if(!this.built){
            return - 1.0;
        }
        return this.ebal[this.term -1];
    }
    
    public double getBegBal(int mo){
         if (!this.built){
            buildAnnuity();
        }
        if(!this.built){
            return - 1.0;
        }
        return this.bbal[mo-1];
        }   


public double getIntEarned(int mo){

    if (!this.built){
        buildAnnuity();
    }
    if(!this.built){
        return - 1.0;
    }
    return this.iearn[mo -1];
}


public double getEndBal(int mo) {

if (!this.built){
    buildAnnuity();
    }
if(!this.built){
       return - 1.0;
    }
return this.ebal[mo-1];
}
}
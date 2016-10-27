
package business;

/**
 *
 * @author Praminda Imaduwa-Gamage
 */
public class Loan {
    public static final String AMTDESC = "Loan Amount";
    public static final String RESULTDESC = "Monthly Payment";
    
    
    private double amt, rate, mopmt; 
    private int term;
    private String errmsg;
    private double[] bbal, ichg, ebal;
    private boolean built;
    
    
    
    public Loan(double amt, double rate, int term){
        this.amt = amt;
        this.rate = rate;
        this.term = term;
        this.mopmt = 0;
        this.built = false;
        
        if (isValid()){
            buildLoan();
        }    
    }//end of constructor
    
    private boolean isValid(){
        this.errmsg="";
        //validation code goes here ... (built this code)
        
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
        return this.errmsg.isEmpty();
    }
    
    
    private void buildLoan(){
        try {
            this.bbal = new double[this.term];
            this.ichg = new double[this.term];
            this.ebal = new double[this.term];
            
            double morate = this.rate/12.0;
            this.bbal[0]=this.amt;
            this.mopmt = this.amt*morate*(1 + 
                             1/(Math.pow((1+ morate),this.term)-1));      
            
            for (int i=0; i<this.term; i++ ){
                
                if (i>0){
                this.bbal[i] = this.ebal[i-1];
                //System.out.println("bbal " + this.bbal[this.term-1]);
                }
                this.ichg[i] = this.bbal[i]*morate;
                this.ebal[i] = this.bbal[i] + this.ichg[i]- this.mopmt;
                //System.out.println("ebal " + this.ebal[this.term-1]);
                }
            this.built= true;
    
        }catch(Exception e) {
            this.errmsg = " Build error: " + e.getMessage();
            this.built = false;
        }
    }
    
    
    public String getErrorMsg(){
        return this.errmsg;
    }
    
    public double getMonthlyPmt(){
        
        if (!this.built){
            buildLoan();  
        }
        if(!this.built){
            return -1.0;
        }
        return this.mopmt;
    }


public double getPrincipal(){

return this.amt;
}

public double getIntRate(){
return this.rate;
}

public int getTerm(){
return this.term;
}

public double getBegBal(int mo){
    
    if (!this.built){
            buildLoan();  
        }
        if(!this.built){
            return -1.0;
        }
        return this.bbal[mo-1];
}
        

public double getIntChg(int mo){
    
    if (!this.built){
            buildLoan();  
        }
        if(!this.built){
            return -1.0;
        }
        return this.ichg[mo-1];
}


public double getEndBal(int mo){
    
    if (!this.built){
            buildLoan();  
        }
        if(!this.built){
            return -1.0;
        }
        return this.ebal[mo-1];
}



}
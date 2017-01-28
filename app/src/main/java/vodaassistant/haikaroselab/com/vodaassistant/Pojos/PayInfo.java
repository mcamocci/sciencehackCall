package vodaassistant.haikaroselab.com.vodaassistant.Pojos;

public class PayInfo {

    private String payer;
    private String amount;
    private String date;
    private String operator;
    private String receipt;

    public PayInfo(String payer, String amount, String date, String receipt,String operator){
        this.amount=amount;
        this.operator=operator;
        this.payer=payer;
        this.date=date;
        this.receipt=receipt;
    }

    public String getPayer(){
        return this.payer;
    }
    public String getAmount(){
        return this.amount;
    }
    public String getDate(){
        return this.date;
    }
    public String getReceipt(){
        return this.receipt;
    }
    public String getOperator(){return  this.operator;}

}

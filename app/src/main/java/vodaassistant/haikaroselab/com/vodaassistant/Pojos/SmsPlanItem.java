package vodaassistant.haikaroselab.com.vodaassistant.Pojos;

public class SmsPlanItem {

    private String name;
    private String detail;


    public SmsPlanItem(String name, String detail){
        this.name=name;
        this.detail=detail;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getDetail(){
        return this.detail;
    }

    public void setDetail(String detail){
        this.detail=detail;
    }
}

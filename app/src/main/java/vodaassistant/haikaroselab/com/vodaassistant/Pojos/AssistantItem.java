package vodaassistant.haikaroselab.com.vodaassistant.Pojos;

public class AssistantItem {

    private String name;
    private String detail;

    public AssistantItem(){

    }

    public AssistantItem(String name, String detail){
        this.name=name;
        this.detail=detail;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getPhone(){
        return this.detail;
    }

    public void setPhone(String detail){
        this.detail=detail;
    }
}

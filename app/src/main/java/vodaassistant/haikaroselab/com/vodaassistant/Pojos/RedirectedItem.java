package vodaassistant.haikaroselab.com.vodaassistant.Pojos;

public class RedirectedItem {

    private String name;

    private String phone;

    public RedirectedItem(){

    }

    public RedirectedItem(String name, String phone){

        this.name=name;
        this.phone=phone;

    }


    public String getName(){
        return this.name;
    }

    public void setName(String name){
       this.name=name;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }
}

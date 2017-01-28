package vodaassistant.haikaroselab.com.vodaassistant.Pojos;

public class FavoritesItem {

    private String name;
    private String phone;

    public FavoritesItem(){

    }

    public FavoritesItem(String name, String detail){
        this.name=name;
        this.phone=detail;
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

    public void setPhone(String detail){
        this.phone=detail;
    }
}

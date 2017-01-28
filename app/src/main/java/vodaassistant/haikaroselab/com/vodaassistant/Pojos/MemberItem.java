package vodaassistant.haikaroselab.com.vodaassistant.Pojos;

public class MemberItem {
    private int Id;
    private String name;
    private String phone;
    private String group;

    public MemberItem(){

    }


    public MemberItem(int id, String name, String phone, String group){
        this.Id=id;
        this.name=name;
        this.phone=phone;
        this.group=group;

    }
    //member item (name,phone,group)

    public MemberItem(String name, String phone, String group){

        this.name=name;
        this.phone=phone;
        this.group=group;

    }

    public String getGroup(){
        return this.group;
    }

    public void setGroup(String group){
        this.group=group;
    }

    public int getId(){
        return this.Id;
    }

    public void setId(int Id){
        this.Id=Id;
    }

    public String getName(){
        return  this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getPhone(){
        return  this.phone;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

    public String toString(){

        return this.getName()+" "+this.getGroup()+" "
                +this.getGroup()+" "+this.getId();
    }
}

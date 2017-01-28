package vodaassistant.haikaroselab.com.vodaassistant.Pojos;

public class Group {
    private int id;
    private String name;

    public Group(){}
    public Group(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){this.name=name;}

    public int getId(){
        return this.id;
    }

    public void setId(int id){this.id=id;}
}

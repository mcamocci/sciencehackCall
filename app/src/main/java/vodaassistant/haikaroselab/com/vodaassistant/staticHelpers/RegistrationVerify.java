package vodaassistant.haikaroselab.com.vodaassistant.staticHelpers;
import android.content.Context;

import java.util.ArrayList;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.DatabaseHelper;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.Group;

public class RegistrationVerify {

    public static boolean check(String group,Context context){

        ArrayList<Group> definedGroup=new DatabaseHelper(context).getAllGroupList();
        ArrayList<String> groupnames=new ArrayList<>();

        for(Group item : definedGroup){
            groupnames.add(item.getName());
        }
        if(groupnames.contains(group)){

            return true;
        }
        return false;
    }
}

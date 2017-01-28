package vodaassistant.haikaroselab.com.vodaassistant.staticHelpers;

import android.content.Context;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.DatabaseHelper;

public class Validator {

    public static String validateNumber(String number){

        String sender=number;
        String realnum="+";

        for(int i=0;i<sender.length();i++) {
                realnum+=sender.charAt(i);
        }
        return realnum;
    }

    public static boolean Userpaid(String number,Context context){
        boolean payed=false;

        DatabaseHelper databaseHelper=new DatabaseHelper(context);
        boolean found=databaseHelper.findPayer(number,context);

        if(found){
            payed=true;
        }else {
            payed=false;
        }
        return payed;
    }

}

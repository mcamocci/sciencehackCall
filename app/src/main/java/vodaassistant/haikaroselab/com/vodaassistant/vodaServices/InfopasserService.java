package vodaassistant.haikaroselab.com.vodaassistant.vodaServices;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;
import java.util.ArrayList;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.DatabaseHelper;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.MemberItem;


public class InfopasserService extends IntentService {

    DatabaseHelper database=new DatabaseHelper(this);

    private ArrayList<MemberItem> groupNumbers;
    private Context context=this;
    private String group=null;
    private String message=null;

    public InfopasserService(){

       super("InfopasserService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        groupNumbers=new ArrayList<>();
        Bundle bundle=intent.getExtras();
        message=bundle.getString("message");
        group=bundle.getString("group");


        if(group.equalsIgnoreCase("all")){

            groupNumbers=database.getAllMembers();

            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> phones=new ArrayList<>();

            for(MemberItem item : groupNumbers){

                if(!phones.contains(item.getPhone())){

                    phones.add(item.getPhone());
                }

            }
            //Iterator iterator=groupNumbers.iterator();
            int size=phones.size()-1;
            while(size>=0){

                if(message.length()>=160){

                    ArrayList<String> parts = smsManager.divideMessage(message);
                    smsManager.sendMultipartTextMessage(phones.get(size), null, parts, null, null);

                }else{
                    smsManager.sendTextMessage(phones.get(size), null, message, null, null);
                    Toast.makeText(getBaseContext(),phones.get(size).toString(),Toast.LENGTH_LONG).show();
                }

                size--;
            }

        }else{

            groupNumbers = database.getGroupMember(group);
            SmsManager smsManager = SmsManager.getDefault();

            ArrayList<String> phones=new ArrayList<>();
            Toast.makeText(getBaseContext(),"not all ",Toast.LENGTH_LONG).show();

            for(MemberItem item : groupNumbers){

                if(!phones.contains(item.getPhone())){

                    phones.add(item.getPhone());
                    Toast.makeText(getBaseContext(),item.getPhone(),Toast.LENGTH_LONG).show();
                }

            }

            int size=phones.size()-1;
            if(message.length()>160){

                ArrayList<String> parts = smsManager.divideMessage(message);
                smsManager.sendMultipartTextMessage(phones.get(size), null, parts, null, null);
                Toast.makeText(getBaseContext(),phones.get(size).toString(),Toast.LENGTH_LONG).show();

            }else{
                smsManager.sendTextMessage(phones.get(size), null, message, null, null);
                Toast.makeText(getBaseContext(),phones.get(size).toString(),Toast.LENGTH_LONG).show();
            }

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"am done mike for "+group+" message: "+message,Toast.LENGTH_LONG).show();
    }
}

package vodaassistant.haikaroselab.com.vodaassistant.vodaServices;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import java.util.ArrayList;


public class SendSmsService extends IntentService {


    private  Context superContext;

    private String[] messageArray;

    public SendSmsService() {
        super("SendSmsService");
    }

    public  void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SendSmsService.class);
        context.startService(intent);

    }
    @
Override
    protected void onHandleIntent(Intent intent) {

        String phone=intent.getStringExtra("number");
        String message=intent.getStringExtra("message");

        messageArray=message.split(" ");
        ArrayList<String> containedInSms=new ArrayList<>();

        for(int i=0;i<messageArray.length;i++){

            containedInSms.add(messageArray[i]);

        }

        /*
        if(containedInSms.contains("register")){

            //add this user;
            DatabaseHelper database=new DatabaseHelper(superContext);
            MemberItem item=new MemberItem(messageArray[1],phone,messageArray[2]);
            database.addMember(item);

        }else{

            SmsManager sendSms=SmsManager.getDefault();
            sendSms.sendTextMessage(phone, null, "This response is from android phone", null, null);

        }
*/
    }

}

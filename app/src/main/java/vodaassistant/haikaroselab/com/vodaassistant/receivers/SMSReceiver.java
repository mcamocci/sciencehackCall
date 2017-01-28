package vodaassistant.haikaroselab.com.vodaassistant.receivers;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import vodaassistant.haikaroselab.com.vodaassistant.contentStore.DatabaseHelper;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.MemberItem;
import vodaassistant.haikaroselab.com.vodaassistant.staticHelpers.RegistrationVerify;
import vodaassistant.haikaroselab.com.vodaassistant.staticHelpers.Validator;


public class SMSReceiver extends BroadcastReceiver {

    private Context context;
    private String senderNumber;
    public static final String VODACOM="MPESA".toLowerCase();
    public static final String AIRTEL="AIRTELMONEY".toLowerCase();
    public static final String TIGO="TIGOPESA".toLowerCase();

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        String receivedSms;
        DatabaseHelper database;
        Calendar calendar = Calendar.getInstance();

        Toast.makeText(context,"message received",Toast.LENGTH_SHORT).show();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,dd MMM yyyy hh:mm a");
        Date date;
        try {

            date = dateFormat.parse(calendar.getTime().toString());

        } catch (Exception ex) {
        }

        String dates = dateFormat.format(calendar.getTime());

        // Get the data (SMS data) bound to intent
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs = null;

        String str = "";

        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            // For every SMS message received
            for (int i = 0; i < msgs.length; i++) {
                // Convert Object array
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                //fetching sender number
                senderNumber = msgs[i].getOriginatingAddress();
                // Fetch the text message
                str += msgs[i].getMessageBody().toString();
                str += "\n";
            }

            receivedSms = str;
            String[] messageArray = receivedSms.split(" ");
            ArrayList<String> containedInSms = new ArrayList<>();

            for (int i = 0; i < messageArray.length; i++) {

                containedInSms.add(messageArray[i]);

            }

            String keyWord = messageArray[0].toLowerCase();


            //checking the who is the sender
            if (senderNumber.toLowerCase().equalsIgnoreCase(AIRTEL)
                    || senderNumber.toLowerCase().equalsIgnoreCase(TIGO)
                    || senderNumber.toLowerCase().equalsIgnoreCase(VODACOM)) {

                String operator=senderNumber.toLowerCase();

                if (operator.contains(AIRTEL)) {

                    boolean pass =false;

                    String ourname="";
                    String ourphone="";
                    for(String name:messageArray){
                        if(name.contains(ourname)||name.contains(ourphone)){
                            pass=true;
                            break;
                        }
                    }
                    if(pass){

                        try {
                            //NOTIFY THE USER ABOUT ACTIVATION OF THE ACCOUNT.
                            SharedPreferences sharedPreferences=context.getSharedPreferences("PAYMENTS",0);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("haspaid",true);
                            editor.commit();

                        } catch (Exception ex) {
                            Toast.makeText(context, "something is wrong " + ex.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                } else if (operator.contains(TIGO)) {

                    boolean pass =false;

                    String ourname="";
                    String ourphone="";

                    for(String name:messageArray){
                        if(name.contains(ourname)||name.contains(ourphone)){
                            pass=true;
                            break;
                        }
                    }
                    if(pass){

                        try {
                            //NOTIFY THE USER ABOUT ACTIVATION OF THE ACCOUNT.
                            SharedPreferences sharedPreferences=context.getSharedPreferences("PAYMENTS",0);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("haspaid",true);
                            editor.commit();

                        } catch (Exception ex) {
                            Toast.makeText(context, "something is wrong " + ex.toString(), Toast.LENGTH_LONG).show();
                        }
                    }

                } else if (operator.contains(VODACOM)) {
                    boolean pass =false;

                    String ourname="";
                    String ourphone="";
                    for(String name:messageArray){
                        if(name.contains(ourname)||name.contains(ourphone)){
                            pass=true;
                            break;
                        }
                    }
                    if(pass){

                        try {
                            //NOTIFY THE USER ABOUT ACTIVATION OF THE ACCOUNT.
                            SharedPreferences sharedPreferences=context.getSharedPreferences("PAYMENTS",0);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("haspaid",true);
                            editor.commit();

                        } catch (Exception ex) {
                            Toast.makeText(context, "something is wrong " + ex.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }


            if (keyWord.equalsIgnoreCase("register")) {


                    try {

                        //add this user;
                        database = new DatabaseHelper(context);

                        MemberItem item = new MemberItem(messageArray[1].toLowerCase().trim(), senderNumber, messageArray[2].toLowerCase().trim());

                        if(RegistrationVerify.check(messageArray[2].toLowerCase().trim(),context)){

                            database.addMember(item);
                            SmsManager sendSms = SmsManager.getDefault();
                            sendSms.sendTextMessage(senderNumber, null, "Thank you " +
                                    "" + senderNumber + " of " + messageArray[2], null, null);
                        }else{

                            SmsManager sendSms = SmsManager.getDefault();
                            sendSms.sendTextMessage(senderNumber, null, "you were not added " +
                                    "no such group " + messageArray[2], null, null);

                        }

                    } catch (Exception ex) {

                    }



            }

            if (keyWord.contains(new String("remove").toLowerCase())){

                database = new DatabaseHelper(context);
                database.removeSingleMember(senderNumber);
                SmsManager sendSms = SmsManager.getDefault();
                sendSms.sendTextMessage(senderNumber, null, "see you next time " +
                        "" + senderNumber, null, null);
            }
        }
    }


}

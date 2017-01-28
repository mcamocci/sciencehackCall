package vodaassistant.haikaroselab.com.vodaassistant.vodaServices;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.support.v4.app.NotificationCompat;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import vodaassistant.haikaroselab.com.vodaassistant.R;
import vodaassistant.haikaroselab.com.vodaassistant.contentStore.ContentStore;
import vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities.StartActivity;

/**
 * Created by root on 6/16/16.
 */
public class TimeHasExpiredIntentService extends IntentService {


    public TimeHasExpiredIntentService(){
        super("TimeHasExpiredIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {

        new ContentStore(getBaseContext()).deleteAllStatus();
        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE,0);

        Intent intent1=new Intent(getBaseContext(), StartActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(getBaseContext(),34,intent1,0);

        Bitmap image= BitmapFactory.decodeResource(getBaseContext().getResources(),R.drawable.vodaassistant);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(image)
                .setContentTitle("VodaAssistant")
                .setContentText("all time has expired and you are now availlable")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }

    public Bitmap bitmapOnlined(String url){
        Bitmap image=null;
        try{
            URL urls=new URL(url);
            image=BitmapFactory.decodeStream(urls.openConnection().getInputStream());
        }catch (Exception ex){ return null;  }
        return image;
    }

}

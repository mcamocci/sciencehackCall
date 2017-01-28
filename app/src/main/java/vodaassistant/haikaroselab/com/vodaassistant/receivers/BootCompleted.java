package vodaassistant.haikaroselab.com.vodaassistant.receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v4.app.NotificationCompat;
import vodaassistant.haikaroselab.com.vodaassistant.R;
import vodaassistant.haikaroselab.com.vodaassistant.contentStore.ContentStore;
import vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities.StartActivity;

/**
 * Created by root on 6/16/16.
 */
public class BootCompleted extends BroadcastReceiver {

    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        //prepareNotificationWhenNecessary();
    }

    public void prepareNotificationWhenNecessary(){

        if(!(new ContentStore(context).getStatus(context).equals("availlable"))){

            new ContentStore(context).deleteAllStatus();
            AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE,0);

            Intent intent=new Intent(context, StartActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(context)
                    .setContentTitle("VodaAssistant")
                    .setContentText("Your previous unavaillability status has been cleared")
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0,builder.build());
        }
    }
}

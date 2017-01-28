package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import vodaassistant.haikaroselab.com.vodaassistant.R;
import vodaassistant.haikaroselab.com.vodaassistant.vodaServices.TimeHasExpiredIntentService;

public class ExpireExcuseDurationActivity extends AppCompatActivity {

    private  int minutes=0;
    private RadioButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        button=(RadioButton)findViewById(R.id.option_one);
        button.setChecked(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(minutes==0){
                    Toast.makeText(getBaseContext(),"You will have to clear your excuse later",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"your excuse will expire in "+Integer.toString(minutes)+" minutes",Toast.LENGTH_LONG)
                    .show();
                    callToAlarmManager();

                }
            }
        });
    }

    public void onRadioItemClicked(View view){

        boolean checked=((RadioButton)view).isChecked();

        switch(view.getId()){
            case R.id.option_one:

                if(checked)
                    minutes=0;
                break;
            case R.id.option_two:
                if(checked)
                    minutes=30;
                break;
            case R.id.option_three:
                if(checked)
                    minutes=60;
                break;
            case R.id.option_four:
                if(checked)
                    minutes=120;
                break;
            case R.id.option_five:
                if(checked)
                    minutes=180;
                break;
            case R.id.option_six:
                if(checked)
                    minutes=240;
                break;
            case R.id.option_seven:
                 if(checked)
                    minutes=300;
                break;
        }

    }

    public void callToAlarmManager(){

        Intent intent=new Intent(getBaseContext(), TimeHasExpiredIntentService.class);
        PendingIntent pendingIntent=PendingIntent.getService(getBaseContext(),0,intent,0);
        AlarmManager alarmManager=(AlarmManager)getBaseContext().getSystemService(ALARM_SERVICE);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP){
           // alarmManager.setExact(AlarmManager.RTC_WAKEUP,seconds,pendingIntent);
            alarmManager.set(AlarmManager.RTC_WAKEUP,minutes*60000,pendingIntent);
            finish();
        } else{
            alarmManager.set(AlarmManager.RTC_WAKEUP,minutes*60000,pendingIntent);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        return true;
    }
}

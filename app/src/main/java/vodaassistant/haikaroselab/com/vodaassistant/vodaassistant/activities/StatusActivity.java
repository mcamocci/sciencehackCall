package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.ContentStore;
import vodaassistant.haikaroselab.com.vodaassistant.R;


public class StatusActivity extends AppCompatActivity {

    private LinearLayout clear;
    private Toolbar toolbar;
    private LinearLayout activate;
    protected TextView registeredExcuses;
    private ContentStore contents;
    private EditText status_value;
    private String status;

    private String excuseStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_status);
        getSupportActionBar().setTitle("Set Status");
        //start setting color of back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //end of setting color

        registeredExcuses=(TextView)findViewById(R.id.registered);
        contents = new ContentStore(getBaseContext());
        status=contents.getStatus(StatusActivity.this);

        final String excuse=status;

        if(excuse.equalsIgnoreCase("availlable")){

            registeredExcuses.setText("You dont have any excuses for not attending phone calls now . " +
                    "you are now accessed by anyone");

        }else{

            registeredExcuses.setText(excuse);
        }

        clear=(LinearLayout) findViewById(R.id.clear);
        activate=(LinearLayout) findViewById(R.id.activate);
        status_value=(EditText)findViewById(R.id.status_value);

        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activateButton(v);

            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearButton(v);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();
        String pending=status_value.getText().toString();
        contents.addPendingText(pending);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        status_value.setText("");
    }

    public void activateButton(View views){

        excuseStatus=status_value.getText().toString();

        final View view=views;
        if(!(excuseStatus.equalsIgnoreCase("") || excuseStatus.length()<5)){

                    contents = new ContentStore(getBaseContext());
                    contents.setStatus(excuseStatus);
                    status_value.setText("");

                    String stats = new ContentStore(getBaseContext()).getStatus(getBaseContext());
                    Snackbar.make(view, "Excuse registered", Snackbar.LENGTH_LONG).show();

                    registeredExcuses.setText(excuseStatus);

                    startActivity(new Intent(StatusActivity.this,AssistantActivity.class));
                    finish();


        }else{

            Snackbar.make(view, "Excuse too short", Snackbar.LENGTH_LONG).show();


        }
    }

    public void clearButton(View views){

        final View view=views;
        final String excuse=status;

        if (excuse.equalsIgnoreCase("availlable")) {

            Snackbar.make(view, "you dont have any Excuse", Snackbar.LENGTH_LONG).show();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(StatusActivity.this);
            alert.setTitle("Clear Excuse");
            alert.setMessage("This option will switch to Normal mode mode");
            alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    boolean check = new ContentStore(getBaseContext()).getVibration(getBaseContext());
                    if (check) {

                        Vibrator vibrate = (Vibrator) StatusActivity.this.getSystemService(getBaseContext().VIBRATOR_SERVICE);
                        vibrate.vibrate(500);
                    }

                    new ContentStore(getBaseContext()).deleteAllStatus();
                    AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                   // audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE,0);
                    String status = new ContentStore(getBaseContext()).getStatus(getBaseContext());
                    Snackbar.make(view,status, Snackbar.LENGTH_LONG).show();
                    registeredExcuses.setText("You dont have any excuses for not attending phone calls now . " +
                            "you are now accessed by anyone");

                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialogs = alert.create();
            dialogs.show();


        }
    }
}

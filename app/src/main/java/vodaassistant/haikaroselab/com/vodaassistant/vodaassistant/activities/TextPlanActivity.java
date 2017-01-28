package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.ContentStore;
import vodaassistant.haikaroselab.com.vodaassistant.R;

public class TextPlanActivity extends AppCompatActivity {


    private LinearLayout button;
    private TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sms_plan);
        //start setting color of back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //end of setting color

        counter=(TextView)findViewById(R.id.counter);
        button=(LinearLayout) findViewById(R.id.reset);
        updates(counter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTimers();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id== android.R.id.home){

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void updates(TextView counter){
        ContentStore contentStore=new ContentStore(this);
        int counts=contentStore.getCounterCounts();

        if(counts==0){
            String text="0000";
            counter.setText(text);
        }else if(counts<10 ){
            String text="000"+Integer.toString(counts);
            counter.setText(text);
        }else if(counts<100){
            String text="00"+Integer.toString(counts);
            counter.setText(text);
        }else if(counts<1000) {
            String text="0"+Integer.toString(counts);
            counter.setText(text);
        }else if(counts>1000 || counts == 1000){
            String text=Integer.toString(counts);
            counter.setText(text);
        }
    }


    public void clearTimers(){
        AlertDialog.Builder alert = new AlertDialog.Builder(TextPlanActivity.this);
        alert.setTitle("Clearing SMS counter");
        alert.setMessage("This option will clear counter");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean check = new ContentStore(getBaseContext()).getVibration(getBaseContext());
                if (check) {

                    Vibrator vibrate = (Vibrator) TextPlanActivity.this.getSystemService(getBaseContext().VIBRATOR_SERVICE);
                    vibrate.vibrate(500);
                }

                new ContentStore(TextPlanActivity.this).removeCounterItem();
                updates(counter);
                Toast.makeText(getBaseContext(), "Counter is cleared", Toast.LENGTH_SHORT).show();

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

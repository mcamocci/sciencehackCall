package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.ContentStore;
import vodaassistant.haikaroselab.com.vodaassistant.R;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.RedirectedItem;
import vodaassistant.haikaroselab.com.vodaassistant.adapters.RedirectedItemAdapter;

public class RedirectedActivity extends AppCompatActivity {


    private android.support.design.widget.FloatingActionButton fab;
    private TextView empty_view;
    private ListView list;
    private   List<RedirectedItem> reminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirected);

        //empty view for empty list view
         empty_view=(TextView)findViewById(R.id.empty_view);
         empty_view.setText("No caller has been redirected currently");

        //start setting color of back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //end of setting color
        fab=(android.support.design.widget.FloatingActionButton)findViewById(R.id.clear_reminder);
        list=(ListView)findViewById(R.id.reminder_list);
        list.setEmptyView(empty_view);
        updateScreen();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ContentStore(getBaseContext()).removeAllReminders();
                updateScreen();
            }
        });

        reminders=new ContentStore(getBaseContext()).getAllReminders(getBaseContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminder, menu);
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

    public void updateScreen(){

        List<RedirectedItem> reminders=new ContentStore(getBaseContext()).getAllReminders(getBaseContext());

        RedirectedItemAdapter adapter=new RedirectedItemAdapter(getBaseContext(),R.layout.reminder_item,reminders);
        list.setAdapter(adapter);
    }
}

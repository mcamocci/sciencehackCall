package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import vodaassistant.haikaroselab.com.vodaassistant.R;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.ServiceItem;
import vodaassistant.haikaroselab.com.vodaassistant.adapters.TaskAdapter;

public class StartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.nav);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),AboutActivity.class);
                startActivity(intent);
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        //populating current service
        final ArrayList<ServiceItem> serviceList=new ArrayList<>();
        serviceList.add(new ServiceItem("My excuse","Reason for not attending the call" ));
        serviceList.add(new ServiceItem("Assistant","The person to help you on incoming phones" ));
        serviceList.add(new ServiceItem("Favorites","People who you can attend their phones" ));
        serviceList.add(new ServiceItem("Redirected","assistant redirected calls" ));
        serviceList.add(new ServiceItem("SMS plan","number of response sent" ));
        serviceList.add(new ServiceItem("Info passer","sms powered components" ));
        TaskAdapter adapter=new TaskAdapter(getBaseContext(),serviceList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contents, menu);
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
            Intent intent=new Intent(getBaseContext(),SettingsActivity.class);
            startActivity(intent);
        }else if(id== R.id.action_error){
            Intent intent=new Intent(getBaseContext(),ReportProblemActivity.class);
            startActivity(intent);
        }else if(id== R.id.action_about){
            Intent intent=new Intent(getBaseContext(),AboutActivity.class);
            startActivity(intent);
        }
        else if(id== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

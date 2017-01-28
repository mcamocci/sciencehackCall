package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.DatabaseHelper;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.Group;
import vodaassistant.haikaroselab.com.vodaassistant.adapters.GroupsItemAdapter;
import vodaassistant.haikaroselab.com.vodaassistant.R;

public class OpenGroupActivity extends AppCompatActivity {


    TextView empty_view;
    private DatabaseHelper contents = new DatabaseHelper(this);
    private ListView groupList;
    private ArrayList<Group> groupsList;
    private android.support.design.widget.FloatingActionButton adder;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        groupsList = contents.getAllGroupList();

        empty_view = (TextView) findViewById(R.id.empty_view);
        groupList = (ListView) findViewById(R.id.groups_list);
        empty_view.setText("you dont have any group");
        groupList.setEmptyView(empty_view);

        adder = (android.support.design.widget.FloatingActionButton) findViewById(R.id.adder);

        adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OpenGroupActivity.this, GroupAdderActivity.class);
                startActivity(intent);
            }
        });

        //start setting color of back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //end of setting color

        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        //Loading necessary data
        favoritesLoader();

        groupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final String group = groupsList.get(position).getName();


                Snackbar.make(view, "Close registration for " + group + "group?", Snackbar.LENGTH_LONG)
                        .setAction("YES", new View.OnClickListener() {

                            public void onClick(View view) {

                                contents.removeSingleGroup(group);
                                favoritesLoader();

                            }

                        }).setActionTextColor(Color.RED)
                        .show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_assistant, menu);
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
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void favoritesLoader() {

        groupsList = contents.getAllGroupList();

        GroupsItemAdapter adapter = new GroupsItemAdapter(getBaseContext(), R.layout.g_item, groupsList);

        groupList.setEmptyView(empty_view);
        groupList.setAdapter(adapter);

    }


}

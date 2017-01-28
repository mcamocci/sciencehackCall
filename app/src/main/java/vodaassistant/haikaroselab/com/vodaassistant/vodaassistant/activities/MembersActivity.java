package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.app.AlertDialog;
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

import java.util.List;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.DatabaseHelper;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.MemberItem;
import vodaassistant.haikaroselab.com.vodaassistant.adapters.MembersItemAdapter;
import vodaassistant.haikaroselab.com.vodaassistant.R;

public class MembersActivity extends AppCompatActivity {


    TextView empty_view;

    private DatabaseHelper contents=new DatabaseHelper(this);
    private ListView peopleList;
    private List<MemberItem> myPeople;
    MemberItem member;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_member_info);
        myPeople = contents.getAllMembers();


        peopleList = (ListView) findViewById(R.id.people_list);
        empty_view=(TextView)findViewById(R.id.empty_view);
        empty_view.setText("no members and groups yet");
        //toolbar
        peopleList.setEmptyView(empty_view);

        //start setting color of back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //end of setting color

        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        //Loading necessary data
        favoritesLoader();

        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                member=new MemberItem();
                member=myPeople.get(position);

                Snackbar.make(view, "Remove " + member.getName() + "?", Snackbar.LENGTH_LONG)
                        .setAction("YES", new View.OnClickListener() {

                            public void onClick(View view) {

                                contents.removeSingleMember(member.getPhone());
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
        }else if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }




    public void favoritesLoader(){

        myPeople=contents.getAllMembers();

        MembersItemAdapter adapter=new MembersItemAdapter(getBaseContext(),R.layout.group_item,myPeople);

        peopleList.setAdapter(adapter);
        peopleList.setEmptyView(empty_view);

    }



}

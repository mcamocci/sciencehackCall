package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.ContentStore;
import vodaassistant.haikaroselab.com.vodaassistant.R;

public class InformationManagerActivity extends AppCompatActivity {

    private ContentStore contentStore;
    private LinearLayout notifier;
    private LinearLayout memberAdder;
    private LinearLayout memberInfo;
    private LinearLayout groupInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_manager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //end of setting color

        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        notifier=(LinearLayout)findViewById(R.id.notifier);
        memberAdder=(LinearLayout)findViewById(R.id.memberAdder);
        memberInfo=(LinearLayout)findViewById(R.id.memberInfo);
        groupInfo=(LinearLayout)findViewById(R.id.groupInfo);



        notifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationManagerActivity.this, InfopasserActivity.class);
                startActivity(intent);
            }
        });

         memberAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting another activity
                Intent intent=new Intent(InformationManagerActivity.this,MemberAdderActivity.class);
                startActivity(intent);
            }
        });

        memberInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationManagerActivity.this, MembersActivity.class);
                startActivity(intent);
            }
        });

        groupInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationManagerActivity.this, OpenGroupActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info_passer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id== android.R.id.home){

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

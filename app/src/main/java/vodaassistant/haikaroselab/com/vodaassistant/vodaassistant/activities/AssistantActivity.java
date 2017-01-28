package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import vodaassistant.haikaroselab.com.vodaassistant.Pojos.AssistantItem;
import vodaassistant.haikaroselab.com.vodaassistant.contentStore.ContentStore;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.FavoritesItem;
import vodaassistant.haikaroselab.com.vodaassistant.adapters.FavoritesItemAdapter;
import vodaassistant.haikaroselab.com.vodaassistant.R;

public class AssistantActivity extends AppCompatActivity {

    private String phone=null;
    private TextView textView;
    private TextView emptyView;
    private ContentStore contents;
    private ListView myfavoritesList;
    private List<FavoritesItem> myfavorites;
    private AlertDialog.Builder alert;
    private  FavoritesItem favorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant);
        contents = new ContentStore(getBaseContext());
        emptyView=(TextView)findViewById(R.id.empty_view);
        emptyView.setText("Please set Favorites first");

        myfavorites = contents.getFavorites(getBaseContext());
        textView = (TextView) findViewById(R.id.assistant_number);
        myfavoritesList = (ListView) findViewById(R.id.myfavorites_new_list);

        //start setting color of back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //end of setting color

        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        myfavoritesList.setEmptyView(emptyView);

        //Loading necessary data
        Loader();
        favoritesLoader();

        myfavoritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                favorite=new FavoritesItem();
                favorite=myfavorites.get(position);
                contents=new ContentStore(getBaseContext());

                Snackbar.make(view, "Assign" + favorite.getName() + "?", Snackbar.LENGTH_LONG)
                        .setAction("YES", new View.OnClickListener() {

                            public void onClick(View view) {
                                boolean check = new ContentStore(getBaseContext()).getVibration(getBaseContext());

                                if(check){

                                    Vibrator vibrate=(Vibrator)AssistantActivity.this.getSystemService(getBaseContext().VIBRATOR_SERVICE);
                                    vibrate.vibrate(500);
                                }

                                contents.addAssistant(new AssistantItem("assistant",favorite.getPhone()));
                                textView.setText(favorite.getPhone());

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


    public void Loader(){

        contents=new ContentStore(this);
        AssistantItem assistant=contents.getAssistant(getBaseContext());
        textView.setText(assistant.getPhone());
        TextView improved=(TextView)findViewById(R.id.improved);
        improved.setText("is your assistant");
        myfavoritesList.setEmptyView(emptyView);
    }

    public void favoritesLoader(){

        myfavorites=contents.getFavorites(getBaseContext());

        FavoritesItemAdapter adapter=new FavoritesItemAdapter(getBaseContext(),R.layout.assistant_item,myfavorites);

        myfavoritesList.setAdapter(adapter);

    }



}

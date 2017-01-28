package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

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

import java.util.List;

import vodaassistant.haikaroselab.com.vodaassistant.staticHelpers.Colors;
import vodaassistant.haikaroselab.com.vodaassistant.contentStore.ContentStore;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.FavoritesItem;
import vodaassistant.haikaroselab.com.vodaassistant.adapters.FavoritesItemAdapter;
import vodaassistant.haikaroselab.com.vodaassistant.R;


public class FavoritesActivity extends AppCompatActivity {


    ContentStore favoritesContent;

    private ListView favorites_list;
    private List<FavoritesItem> favorites;
    private TextView emptyView;
    private FavoritesItemAdapter favoritesAdapter;
    private android.support.design.widget.FloatingActionButton adder;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoritesContent=new ContentStore(getBaseContext());
        emptyView=(TextView)findViewById(R.id.empty_view);
        emptyView.setText("Click + sign to add favorites");
        favorites_list =(ListView)findViewById(R.id.favorites_list);
        favorites=favoritesContent.getFavorites(getBaseContext());
        intent=new Intent(this,ContactsActivity.class);
        favorites_list.setEmptyView(emptyView);

        //start setting color of back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //end of setting color


        updateList();


        favorites_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final FavoritesItem item = favorites.get(position);

                View.OnClickListener aclist = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        favoritesContent.removeFavorites(item);
                        updateList();
                    }
                };

                Snackbar.make(view, "Remove " + item.getName() + " as favorite?", Snackbar.LENGTH_LONG)
                        .setAction("DELETE", aclist).setActionTextColor(Color.parseColor(Colors.danger))
                        .show();
            }
        });


        adder=(android.support.design.widget.FloatingActionButton)findViewById(R.id.adder);

         adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_favorites, menu);
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
                } else if(id== android.R.id.home){

                    finish();
                }

                return super.onOptionsItemSelected(item);
            }

            @Override
            protected void onResume() {
                super.onResume();
                updateList();
            }

            public void updateList() {

                favoritesContent = new ContentStore(getBaseContext());
                favorites = favoritesContent.getFavorites(getBaseContext());
                //favorites list
                favorites_list.setEmptyView(emptyView);
                favoritesAdapter = new FavoritesItemAdapter(getBaseContext(), R.layout.favorite_item, favorites);
                favorites_list.setAdapter(favoritesAdapter);

            }
        }

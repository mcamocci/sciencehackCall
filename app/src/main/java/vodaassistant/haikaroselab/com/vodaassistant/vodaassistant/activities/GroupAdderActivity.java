package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.DatabaseHelper;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.Group;
import vodaassistant.haikaroselab.com.vodaassistant.R;


public class GroupAdderActivity extends AppCompatActivity {

    private LinearLayout register;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_adder);

        //start setting color of back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        //end of setting color

        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        name=(EditText)findViewById(R.id.name);

        register=(LinearLayout)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Group item=null;

                if(!(name.getText().toString().isEmpty())){

                    item=new Group(name.getText().toString());
                    DatabaseHelper database=new DatabaseHelper(GroupAdderActivity.this);
                    database.addGroup(item);
                    Toast.makeText(GroupAdderActivity.this, "Added", Toast.LENGTH_SHORT).show();
                    name.setText("");

                }else{
                    Snackbar.make(v,"you cant insert empty information", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_member_adder, menu);
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
}

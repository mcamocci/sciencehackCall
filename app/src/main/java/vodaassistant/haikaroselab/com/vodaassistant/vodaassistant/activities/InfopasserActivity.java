package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import vodaassistant.haikaroselab.com.vodaassistant.contentStore.DatabaseHelper;
import vodaassistant.haikaroselab.com.vodaassistant.vodaServices.InfopasserService;
import vodaassistant.haikaroselab.com.vodaassistant.R;

public class InfopasserActivity extends AppCompatActivity {


    String[] items;
    private Spinner spinner;
    EditText messageEditText=null;
    LinearLayout notify=null;
    Intent serviceIntent=null;
    private Bundle bundles=null;
    private String group;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_passer);

        DatabaseHelper data=new DatabaseHelper(this);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        items=data.getAllGroup();
        ArrayList<String> uniques=new ArrayList<>();

        for(String value:items) {
            if (uniques.contains(value)) {

            }else{
                uniques.add(value.trim());
            }
        }

        String newItems[]=new String[uniques.size()];
        newItems=uniques.toArray(newItems);

        spinner=(Spinner)findViewById(R.id.spinner);
        messageEditText=(EditText)findViewById(R.id.MessageEditText);
        notify=(LinearLayout)findViewById(R.id.notify);

        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,newItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                message=messageEditText.getText().toString();

                if (!(message.equalsIgnoreCase("")) || !(message.isEmpty())) {

                    Toast.makeText(InfopasserActivity.this, "am starting mike", Toast.LENGTH_SHORT).show();
                    group=spinner.getSelectedItem().toString();
                    bundles = new Bundle();
                    bundles.putString("group", group);
                    bundles.putString("message", message);
                    serviceIntent = new Intent(getBaseContext(), InfopasserService.class);
                    serviceIntent.putExtras(bundles);
                    getBaseContext().startService(serviceIntent);
                    Toast.makeText(InfopasserActivity.this, "am starting mike", Toast.LENGTH_SHORT).show();
                    finish();

                }
                else{

                    Snackbar.make(v, "no message mike", Snackbar.LENGTH_SHORT).show();
            }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if(id==android.R.id.home){
            finish();
        }
        return true;
    }
}

package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import vodaassistant.haikaroselab.com.vodaassistant.R;


public class ReportProblemActivity extends AppCompatActivity {

    EditText problem;
    LinearLayout submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);

        getSupportActionBar().setTitle(Html.fromHtml("CONTACT US</b"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        problem=(EditText)findViewById(R.id.text_problem);
        submit_button=(LinearLayout)findViewById(R.id.button_submit);


        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reported_problem=problem.getText().toString().trim();

                if(reported_problem.length()>=10){

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+255687374009", null,"error report: "+reported_problem, null, null);

                    Snackbar.make(v, "Operation carried", Snackbar.LENGTH_LONG).show();
                    problem.setText("");
                    // .setAction("DELETE", aclist).setActionTextColor(Color.parseColor(Colors.danger))
                    // .show();
                }else{
                    Snackbar.make(v, "Operation Failed", Snackbar.LENGTH_LONG).show();
                    problem.setText("");
                }
        }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return  true;
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

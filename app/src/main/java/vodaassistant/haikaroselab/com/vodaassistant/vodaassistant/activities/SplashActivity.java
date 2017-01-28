package vodaassistant.haikaroselab.com.vodaassistant.vodaassistant.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vodaassistant.haikaroselab.com.vodaassistant.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread splash=new Thread(){

            public void run(){

                try{
                    Thread.sleep(2000);
                    boolean haspaid=getBaseContext().getSharedPreferences("PAYMENTS",0)
                            .getBoolean("haspaid",false);
                    if(!haspaid){
                       /* Intent intent=new Intent(SplashActivity.this,PaymentDetailsActivity.class);
                        startActivity(intent);
                        finish();*/
                        Intent intent=new Intent(SplashActivity.this,StartActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent=new Intent(SplashActivity.this,StartActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }catch(InterruptedException ex){
                    ex.getMessage();
                }

            }

        };


        splash.start();

    }

}

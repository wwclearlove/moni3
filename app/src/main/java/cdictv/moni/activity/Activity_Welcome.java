package cdictv.moni.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cdictv.moni.R;
import cdictv.moni.util.Sputil;


public class Activity_Welcome extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__welcome);
        Boolean flag=Sputil.getBoolean("第一次启动");
        if(flag){
            Intent intent2=new Intent(Activity_Welcome.this,LoginActivity.class);
            startActivity(intent2);
            finish();
        }else{
            Intent intent1=new Intent(Activity_Welcome.this,Activity_Guide.class);
            startActivity(intent1);
            finish();
        }
        Sputil.putBoolean("第一次启动",true);
    }
}

package cdictv.moni.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cdictv.moni.R;


public class Activity_Guide extends AppCompatActivity implements  Runnable {
//21321n你好
    private String s="asdfsdf";
    private String s2="asdfsdf";
    private String s3="asdfsdf";
    private String s4="asdfsdf";
    private String s5="asdfsdf";
    private String s6="asdfsdf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__guide);
        new  Thread(this).start();
    }
    @Override
    public void run() {
        try {
            Thread.sleep(2300);
            startActivity(new Intent(Activity_Guide.this,LoginActivity.class));
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

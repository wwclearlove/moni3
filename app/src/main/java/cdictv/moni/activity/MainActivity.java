package cdictv.moni.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cdictv.moni.R;
import cdictv.moni.util.Sputil;

public class MainActivity extends AppCompatActivity  {

    private ImageView left_menu;
    private TextView title;
    private TextView zhuxiao;
    private RelativeLayout tool_bar;
    private ImageView top_bac;
    private LinearLayout navigation_view;
    private LinearLayout gongjiaolayout;
    private LinearLayout cz;
    private LinearLayout reddeng;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initlinsener();
    }

    private void initlinsener() {
        left_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
        zhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sputil.putBoolean("zhidong",false);
                Sputil.putBoolean("baocun",false);
                Sputil.revome("user");
                Sputil.revome("pass");
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });
        gongjiaolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        MainActivity.this,gongjiaoActivity.class));
            }
        });
        cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        MainActivity.this,ZHGLActivity.class));
            }
        });
        reddeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        MainActivity.this,HongLvDengActivity.class));
            }
        });
    }

    private void initView() {
        cz=findViewById(R.id.zhgl);
        reddeng=findViewById(R.id.reddeng);
        gongjiaolayout=findViewById(R.id.gongja_layout);
        drawerLayout=findViewById(R.id.drawer_yout);
        left_menu = (ImageView) findViewById(R.id.left_menu);
        title = (TextView) findViewById(R.id.title);
        zhuxiao = (TextView) findViewById(R.id.zhuxiao);
        tool_bar = (RelativeLayout) findViewById(R.id.tool_bar);
        top_bac = (ImageView) findViewById(R.id.top_bac);
        navigation_view = (LinearLayout) findViewById(R.id.navigation_view);

    }

}

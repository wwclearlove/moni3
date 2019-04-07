package cdictv.moni.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import cdictv.moni.R;
import cdictv.moni.bean.UserBean;
import cdictv.moni.network.Mycall;
import cdictv.moni.network.OkhttpApi;
import cdictv.moni.util.Sputil;
import cdictv.moni.util.ZhengzeUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgview;
    private TextView textLogo;
    private TextView network;
    private EditText edName;
    private EditText edPassword;
    private CheckBox checkJz;
    private CheckBox checkZd;
    private Button logo;
    private Button regist;
    private String name;
    private String password;
    UserBean mUserBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //保存密码
        initViwe();
        boolean flag = Sputil.getBoolean("baocun");
        checkJz.setChecked(flag);
        if (flag) {
            edName.setText(Sputil.getString("user"));
            edPassword.setText(Sputil.getString("pass"));
            //自动登录
            if (Sputil.getBoolean("zhidong")) {
                checkZd.setChecked(true);
//                Toast.makeText(LoginActivity.this,"自动登录",Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                Sputil.putString("name",mUserBean.data.username);
                finish();
            } else {
                checkZd.setChecked(false);
            }
        }
        initLister();

    }

    private void getStringNamePass() {
        name = edName.getText().toString().trim();
        password = edPassword.getText().toString().trim();
        Log.d("tag", "getStringNamePass: " + name);
        Log.d("tag", "getStringNamePass: " + password);
    }

    private void initLister() {

        logo.setOnClickListener(this);

    }

    private void initViwe() {
        imgview = (ImageView) findViewById(R.id.imgview);
        textLogo = (TextView) findViewById(R.id.text_logo);
        network = (TextView) findViewById(R.id.network);
        edName = (EditText) findViewById(R.id.ed_name);
        edPassword = (EditText) findViewById(R.id.ed_password);
        checkJz = (CheckBox) findViewById(R.id.check_jz);
        checkZd = (CheckBox) findViewById(R.id.check_zd);
        logo = (Button) findViewById(R.id.logo);
        regist = (Button) findViewById(R.id.regist);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logo:
                getStringNamePass();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(LoginActivity.this,
                            "账户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this,
                            "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!name.matches(ZhengzeUtil.UERZJ)) {
                    Toast.makeText(LoginActivity.this,
                            "用户名请输入为字母首位且4-8位数字字母", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.matches(ZhengzeUtil.PWDZJ)) {
                    Toast.makeText(LoginActivity.this,
                            "密码请输入不能以0开头切6-12位的数字",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                OkhttpApi.login(name, password, new Mycall() {
                    @Override
                    public void success(String json) {
                        Log.d("===", json);
                        if (json.contains("username")) {
                            Gson gson = new Gson();
                            mUserBean = gson.fromJson(json, UserBean.class);
                            if (checkJz.isChecked()) {
                                Log.d("++", "success: 保存");
                                Sputil.putString("user", name);
                                Sputil.putString("pass", password);
                                Sputil.putBoolean("baocun", true);
                                if (checkZd.isChecked()) {
                                    Sputil.putBoolean("zhidong", true);
                                } else {
                                    Sputil.revome("zhidong");
                                }
                            } else {
                                Sputil.revome("user");
                                Sputil.revome("pass");
                                Sputil.revome("baocun");
                            }
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            Sputil.putString("name", mUserBean.data.username);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "用户名或密码错", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void faild(IOException e) {

                    }
                });
        }
    }
}


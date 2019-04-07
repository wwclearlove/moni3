package cdictv.moni.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cdictv.moni.R;
import cdictv.moni.adatper.ZHGLListAdapter;
import cdictv.moni.bean.ZHGlListBean;
import cdictv.moni.network.Mycall;
import cdictv.moni.network.OkhttpApi;


public class ZHGLActivity extends AppCompatActivity {
    private ImageView back;
    private ListView listView;
    private ZHGLListAdapter zhglListAdapter;
    private List<ZHGlListBean.DataBean> datas;
    private TextView jilu;
    private TextView piliang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhgl);
        init();
        getData();
        setOnClick();
    }

    private void setOnClick() {
        piliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<ZHGlListBean.DataBean> d = new ArrayList<>();
                Boolean flag = false;
                for (ZHGlListBean.DataBean b : datas) {
                    if (b.getCheckbox()) {
                        flag = true;
                    }
                }
                if (!flag) {
                    Toast.makeText(ZHGLActivity.this, "请选择！！！", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.e("booloan", "onClick: " + datas.toString());
                View inflate = View.inflate(ZHGLActivity.this, R.layout.chongzhi_dialog_layout, null);
                TextView dialogChepai = inflate.findViewById(R.id.dialog_chepai);
                final EditText editMoney = inflate.findViewById(R.id.edit_money);
                Button chongzhiBtn = inflate.findViewById(R.id.chongzhi_btn);
                Button quxiaoBtn = inflate.findViewById(R.id.quxiao_btn);


                final StringBuilder sb = new StringBuilder();
                for (ZHGlListBean.DataBean b : datas) {
                    if (b.getCheckbox()) {
                        sb.append(b.getChepai() + ",");
                        d.add(b);
                    }
                }
                dialogChepai.setText(sb.substring(0, sb.length() - 1));

                final AlertDialog alertDialog = new AlertDialog.Builder(ZHGLActivity.this).setView(inflate).show();


                quxiaoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                chongzhiBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String trim = editMoney.getText().toString().trim();


                        if ("".equals(trim)) {
                            Toast.makeText(ZHGLActivity.this, "不能为空！！！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        final int i = Integer.parseInt(trim);
                        if (i == 0) {
                            Toast.makeText(ZHGLActivity.this, "充值范围1-999", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        final AlertDialog show = new ProgressDialog.Builder(ZHGLActivity.this).setTitle("正在加载中。。。").show();
                        for (final ZHGlListBean.DataBean b : d) {
                            int id = b.getId();
                            final int j=b.getMoney()+i;
                            OkhttpApi.chongzhi("{\"num\":" + id + ",\"money\":" + i + "}", new Mycall() {
                                @Override
                                public void success(String json) {
                                    Log.e("dialog", "success: " + json);
                                    Toast.makeText(ZHGLActivity.this, "充值成功！！！", Toast.LENGTH_SHORT).show();

                                    //int money1 = datas.get(a).getMoney() + i;
                                    for(ZHGlListBean.DataBean b2:datas){
                                        if(b2.getId()==b.getId()){
                                            b2.setMoney(j);
                                        }
                                    }

                                    zhglListAdapter.notifyDataSetChanged();

                                    show.dismiss();
                                    alertDialog.dismiss();
                                }

                                @Override
                                public void faild(IOException e) {
                                    Toast.makeText(ZHGLActivity.this, "充值失败！！！", Toast.LENGTH_SHORT).show();
                                    show.dismiss();
                                    alertDialog.dismiss();
                                }
                            });
                        }



                    }
                });


            }
        });
    }

    private void setAda() {
        zhglListAdapter = new ZHGLListAdapter(ZHGLActivity.this, datas);
        listView.setAdapter(zhglListAdapter);
    }

    private void init() {
        back = (ImageView) findViewById(R.id.back);
        jilu = (TextView) findViewById(R.id.jilu);
        piliang = (TextView) findViewById(R.id.piliang);
        listView = (ListView) findViewById(R.id.listView);
        datas = new ArrayList<>();
    }

    public void getData() {
        Log.e("tag", "getData: " + "fdsfasdffffffffffffffff");
        OkhttpApi.showOkhttp("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/carlist", new Mycall() {
            @Override
            public void success(String json) {
                Log.e("tag", "success: " + json);
                ZHGlListBean zhGlListBean = new Gson().fromJson(json, ZHGlListBean.class);
                List<ZHGlListBean.DataBean> data = zhGlListBean.getData();
                datas.addAll(data);
                Log.e("tag", "success: " + data.toString());
                setAda();
            }

            @Override
            public void faild(IOException e) {
                Log.e("tag", "success: " + e.getMessage());
            }
        });


    }
}

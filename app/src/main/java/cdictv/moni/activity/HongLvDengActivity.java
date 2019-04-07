package cdictv.moni.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cdictv.moni.R;
import cdictv.moni.adatper.HongLvDengAdapter;
import cdictv.moni.bean.HongLvDengBean;
import cdictv.moni.network.Mycall;
import cdictv.moni.network.OkhttpApi;

public class HongLvDengActivity extends AppCompatActivity {

    Gson gson =new Gson();
    List<HongLvDengBean.DataBean> mlist = new ArrayList<>();
    HongLvDengAdapter adapter;
    int index = 0;
    private Spinner hongnvdengSpinner;
    private Button dengBtFind;
    private Button dengBtShezhi;
    ListView deneListView;
    private CheckBox tvCaozuo;
    List<Integer> idlist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honglvdeng);
        initView();
        initRequset();
        initSpinner();



        hongnvdengSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("=======", "onItemSelected: "+position);
                index = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dengBtFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDeng(index);
                adapter.notifyDataSetChanged();
            }
        });

        dengBtShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1 = View.inflate(HongLvDengActivity.this,R.layout.dialog_dnegshezhi,null);
                final AlertDialog alertDialog = new AlertDialog.Builder(HongLvDengActivity.this).setView(view1).show();
                final EditText edit_red = view1.findViewById(R.id.edit_red);
                final EditText edit_yellow = view1.findViewById(R.id.edit_yellow);
                final EditText edit_gerry = view1.findViewById(R.id.edit_gerry);
                TextView textView = view1.findViewById(R.id.deng_id);
                if(idlist.size() != 0){
                    StringBuilder sb = new StringBuilder();
                    for(int i :idlist){
                        sb.append(i+",");
                    }
                    textView.setText(sb.toString());
                }

                Button quxiaoBtn = view1.findViewById(R.id.bt_exit);
                Button ntok = view1.findViewById(R.id.bt_ok);
                quxiaoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
                ntok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        String red = edit_red.getText().toString();
//                        String yellow = edit_yellow.getText().toString();
//                        String gerry = edit_gerry.getText().toString();

                    }
                });
            }
        });

    }

    private void initSpinner() {
        String [] data = {"路口升序","路口降序","红灯升序","红灯降序","绿灯升序","绿灯降序","黄灯升序","黄灯降序"};
        hongnvdengSpinner.setAdapter(new ArrayAdapter<String>(HongLvDengActivity.this,R.layout.deng_spinner_item,R.id.dneg_spinner,data));
    }

    private void initRequset() {
        //OkhttpApi okhttpApi = new OkhttpApi();
        OkhttpApi.showOkhttp("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/lamplist",
                new Mycall() {
                    @Override
                    public void success(String json) {
                        AlertDialog dialog = new ProgressDialog.Builder(HongLvDengActivity.this).setTitle("正在请求").show();
                        Log.i("111111", "success: "+json);
                        HongLvDengBean  bean = gson.fromJson(json,HongLvDengBean.class);
                        if(mlist.size() == 0){
                            mlist.addAll(bean.data);
                        }else {
                            mlist.clear();
                            mlist.addAll(bean.data);
                        }
                        if(adapter == null){
                            adapter = new HongLvDengAdapter(HongLvDengActivity.this,mlist,index);
                            deneListView.setAdapter(adapter);
                        }else {
                            adapter.notifyDataSetChanged();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void faild(IOException e) {
                        Log.i("eeeeee", "faild: "+e.getMessage());
                    }
                });
    }

    private void initView() {
        hongnvdengSpinner =  findViewById(R.id.hongnvdeng_spinner);
        dengBtFind = findViewById(R.id.deng_bt_find);
        dengBtShezhi =  findViewById(R.id.deng_bt_shezhi);
        deneListView = findViewById(R.id.deng_listview);
    }

    private void selectDeng(int i){
        switch (i){
            case  0:
                Collections.sort(mlist, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o1.id-o2.id;
                    }
                });
                break;
            case  1:
                Collections.sort(mlist, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o2.id-o1.id;
                    }
                });
                break;
            case  2:
                Collections.sort(mlist, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o2.red-o1.red;
                    }
                });

                break;
            case  3:
                Collections.sort(mlist, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o1.red-o2.red;
                    }
                });
                break;
            case  4:
                Collections.sort(mlist, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o2.green-o1.green;
                    }
                });

                break;
            case  5:
                Collections.sort(mlist, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o1.green-o2.green;
                    }
                });
                break;
            case  6:
                Collections.sort(mlist, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o2.yellow-o1.yellow;
                    }
                });
                break;
            case  7:
                Collections.sort(mlist, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o1.yellow-o2.yellow;
                    }
                });
                break;
        }
        adapter = new HongLvDengAdapter(HongLvDengActivity.this, mlist, index);
        deneListView.setAdapter(adapter);
    }

}

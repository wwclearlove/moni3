package cdictv.moni.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cdictv.moni.R;
import cdictv.moni.adatper.ListviewAdatper;
import cdictv.moni.adatper.MyBusAateper;
import cdictv.moni.bean.RenshuBean;
import cdictv.moni.bean.ZhantaiBean;
import cdictv.moni.network.Mycall;
import cdictv.moni.network.OkhttpApi;

public class gongjiaoActivity extends AppCompatActivity  {

    private ImageView bus_img;
    private TextView bus_time;
    private TextView bus_renshu;
    private TextView bus_name;
    private Button bus_desc;
    private ExpandableListView expand_listview;
    private Handler mHandler=new Handler();
    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this,3000);
            okhttpJUli();
            okhtttpRenshu();
        }
    };
    private List<ZhantaiBean.DataBean.BusBean> mBus;
    private List<RenshuBean.DataBean> mData;
    private MyBusAateper mMyBusAateper;

    private void okhttpJUli() {
        OkhttpApi.showOkhttp("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/gognjiaochaxun"
                , new Mycall() {
                    @Override
                    public void success(String json) {
//                        Log.d("tag1", json);
                        Gson gson=new Gson();
                        ZhantaiBean zhantaiBean= null;
                        try {
                            zhantaiBean = gson.fromJson(json,ZhantaiBean.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                        ZhantaiBean.DataBean dataBean = zhantaiBean.data;
                        xuanyantou(dataBean);
                        List<ZhantaiBean.DataBean.BusBean> lists = zhantaiBean.data.bus;
                        for (ZhantaiBean.DataBean.BusBean bus:
                                lists) {
                            listpaixu(bus.num);
                        }
                        mBus.clear();
                        mBus.addAll(lists);
                        //排序后
                        mMyBusAateper.notifyDataSetChanged();
                    }

                    @Override
                    public void faild(IOException e) {

                    }
                });
    }

    private void listpaixu(List<ZhantaiBean.DataBean.BusBean.NumBean> num) {
        Collections.sort(num, new Comparator<ZhantaiBean.DataBean.BusBean.NumBean>() {
            @Override
            public int compare(ZhantaiBean.DataBean.BusBean.NumBean o1, ZhantaiBean.DataBean.BusBean.NumBean o2) {
                return o1.distance-o2.distance;
            }
        });
    }

    private void xuanyantou(ZhantaiBean.DataBean dataBean) {
        bus_name.setText(dataBean.name);
        bus_renshu.setText(dataBean.num);
        bus_time.setText(dataBean.time);
    }


    private void okhtttpRenshu() {
        OkhttpApi.showOkhttp("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/zaikerenshu"
                , new Mycall() {
                    @Override
                    public void success(String json) {
                        Gson gson=new Gson();
                        RenshuBean renshuBean=null;
                        try {
                            renshuBean = gson.fromJson(json,RenshuBean.class);
                            List<RenshuBean.DataBean> lists = renshuBean.data;
                            mData.clear();
                            mData.addAll(lists);
                            mMyBusAateper.notifyDataSetChanged();
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void faild(IOException e) {

                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongjiao);
        mBus=new ArrayList<>();
        mData=new ArrayList<>();
        mMyBusAateper = new MyBusAateper(mBus,mData,gongjiaoActivity.this);
        initView();

        initlist();
        okhttpJUli();
        okhtttpRenshu();
        expand_listview.setAdapter(mMyBusAateper);
        mMyBusAateper.notifyDataSetChanged();

    }

    private void initlist() {
      bus_desc.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              View inflate = View.inflate(gongjiaoActivity.this, R.layout.dialog_bus_details, null);
              final AlertDialog alertDialog = new AlertDialog.Builder(gongjiaoActivity.this).setView(inflate).show();
              ListView listview;
              final Button back;
              TextView hj;
              int renshu=0;
              hj=inflate.findViewById(R.id.heji);
              listview = (ListView) inflate. findViewById(R.id.listview);

              back = (Button) inflate. findViewById(R.id.back);
              ListviewAdatper listAdapter=new ListviewAdatper(mData,gongjiaoActivity.this);
              listview.setAdapter(listAdapter);
              for (RenshuBean.DataBean bean:
                      mData) {
                  renshu+=bean.renshu;
              }
              hj.setText(renshu+"");
              back.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      alertDialog.dismiss();
                  }
              });
          }
      });
    }

    @Override
    protected void onStop() {
        mHandler.removeCallbacks(mRunnable);
        super.onStop();
    }

    @Override
    protected void onStart() {
        mHandler.postDelayed(mRunnable,3000);
        super.onStart();
    }

    private void initView() {
        bus_img = (ImageView) findViewById(R.id.bus_img);
        bus_name = (TextView) findViewById(R.id.bus_name);
        bus_renshu = (TextView) findViewById(R.id.bus_renshu);
       bus_time = (TextView) findViewById(R.id.bus_time);
        bus_desc = (Button) findViewById(R.id.bus_desc);
        expand_listview = (ExpandableListView) findViewById(R.id.expand_listview);
    }

}

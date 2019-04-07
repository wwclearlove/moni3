package cdictv.moni.adatper;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cdictv.moni.R;
import cdictv.moni.bean.HongLvDengBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HongLvDengAdapter extends BaseAdapter {

    private TextView tvLukou;
    private TextView tvRed;
    private TextView tvYellow;
    private TextView tvGreen;
    private CheckBox tvCaozuo;
    private Button tvShezhi;
    List<Integer> idlist = new ArrayList<>();
    Handler handler = new Handler();


    Context context;
    List<HongLvDengBean.DataBean> list;
    int index;

    public HongLvDengAdapter(Context context, List list, int index) {
        this.context = context;
        this.list = list;
        this.index = index;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        selectDeng(index);
        HongLvDengBean.DataBean bean = list.get(position);
        View view;
        if(convertView == null){
            view  = LayoutInflater.from(context).inflate(R.layout.honglvdneg_item,parent,false);
        }else {
            view =  convertView;
        }
        tvLukou =  view.findViewById(R.id.tv_lukou);
        tvRed =  view.findViewById(R.id.tv_red);
        tvYellow =  view.findViewById(R.id.tv_yellow);
        tvGreen = view.findViewById(R.id.tv_green);
        tvCaozuo = view.findViewById(R.id.tv_caozuo);
        tvShezhi = view.findViewById(R.id.tv_shezhi);

        if(tvCaozuo.isChecked()){
            tvCaozuo.setChecked(true);
            idlist.add(bean.id);
        }else {
            tvCaozuo.setChecked(false);
            List<Integer> newlsit = new ArrayList<>();
            for(int i : idlist){
                if (bean.id == i) {
                    newlsit.add(i);
                }
            }
            if(idlist.size() != 0){
                idlist.clear();
                idlist.addAll(newlsit);
            }
        }

        tvShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1 = View.inflate(context,R.layout.dialog_dnegshezhi,null);
                final AlertDialog alertDialog = new AlertDialog.Builder(context).setView(view1).show();
                final EditText edit_red = view1.findViewById(R.id.edit_red);
                final EditText edit_yellow = view1.findViewById(R.id.edit_yellow);
                final EditText edit_gerry = view1.findViewById(R.id.edit_gerry);
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

        tvLukou.setText(bean.id+"");
        tvRed.setText(bean.red+"");
        tvYellow.setText(bean.green+"");
        tvGreen.setText(bean.green+"");
        return view;
    }

    private void selectDeng(int i){
        switch (i){
            case  0:
                Collections.sort(list, new Comparator<HongLvDengBean.DataBean>() {
                @Override
                public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                    return o1.id-o2.id;
                }
            });
                break;
            case  1:
                Collections.sort(list, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o2.id-o1.id;
                    }
                });
                break;
            case  2:
                Collections.sort(list, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o2.red-o1.red;
                    }
                });

                break;
            case  3:
                Collections.sort(list, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o1.red-o2.red;
                    }
                });
                break;
            case  4:
                Collections.sort(list, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o2.green-o1.green;
                    }
                });

                break;
            case  5:
                Collections.sort(list, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o1.green-o2.green;
                    }
                });
                break;
            case  6:
                Collections.sort(list, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o2.yellow-o1.yellow;
                    }
                });
                break;
            case  7:
                Collections.sort(list, new Comparator<HongLvDengBean.DataBean>() {
                    @Override
                    public int compare(HongLvDengBean.DataBean o1, HongLvDengBean.DataBean o2) {
                        return o1.yellow-o2.yellow;
                    }
                });

                break;
        }
    }

     private void getRequest(String red,String yellow,String grend){
         RequestBody body = new FormBody.Builder()
                 .add("red",red)
                 .add("String",yellow)
                 .add("grend",grend)
                .build();
                 OkHttpClient client = new OkHttpClient();
                 Request request = new Request.Builder()
                         .url("https://www.easy-mock.com/mock/5c8f3515c42b1c0235654282/jiaotong/recharge")
                         .post(body)
                         .build();

                 client.newCall(request).enqueue(new Callback() {
                     @Override
                     public void onFailure(Call call, IOException e) {

                     }

                     @Override
                     public void onResponse(Call call, Response response) throws IOException {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                     }
                 });

    }

}

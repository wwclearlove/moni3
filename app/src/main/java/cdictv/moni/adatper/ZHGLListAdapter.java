package cdictv.moni.adatper;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import cdictv.moni.App;
import cdictv.moni.R;
import cdictv.moni.bean.ZHGlListBean;
import cdictv.moni.network.Mycall;
import cdictv.moni.network.OkhttpApi;


public class ZHGLListAdapter extends BaseAdapter{
    private List<ZHGlListBean.DataBean> datas;
    private Context context;

    public ZHGLListAdapter(Context context, List<ZHGlListBean.DataBean> datas) {
        this.context=context;
        this.datas=datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(App.instance, R.layout.zhgl_listview_itemlayout,null);
        }
        TextView id = convertView.findViewById(R.id.id);
        ImageView iimg = convertView.findViewById(R.id.img);
        TextView cp = convertView.findViewById(R.id.cp);
        TextView name = convertView.findViewById(R.id.name);
        TextView money = convertView.findViewById(R.id.money);
        CheckBox checkbox = convertView.findViewById(R.id.checkbox);
        final Button chongzhi = convertView.findViewById(R.id.chongzhi);



        id.setText(datas.get(position).getId()+"");
        cp.setText(datas.get(position).getChepai()+"");
        name.setText(datas.get(position).getChezhu()+"");
        money.setText(datas.get(position).getMoney()+"");
        //checkbox.setEnabled(datas.get(position).getCheckbox());
        chongzhi.setTag(position);




        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a= (int) chongzhi.getTag();
                datas.get(a).setCheckbox(!datas.get(a).getCheckbox());
            }
        });


        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a= (int) chongzhi.getTag();

                View inflate = View.inflate(context, R.layout.chongzhi_dialog_layout, null);

                TextView dialogChepai = inflate.findViewById(R.id.dialog_chepai);
                final EditText editMoney = inflate.findViewById(R.id.edit_money);
                Button chongzhiBtn = inflate.findViewById(R.id.chongzhi_btn);
                Button quxiaoBtn = inflate.findViewById(R.id.quxiao_btn);
                dialogChepai.setText(datas.get(a).getChepai()+"");

                final AlertDialog alertDialog = new AlertDialog.Builder(context).setView(inflate).show();


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


                        if("".equals(trim)){
                            Toast.makeText(context, "不能为空！！！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        final int i = Integer.parseInt(trim);
                        if(i==0){
                            Toast.makeText(context, "充值范围1-999", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        final int a= (int) chongzhi.getTag();
                        Log.e("tttttttttttttt", "onClick: "+a );
                        ZHGlListBean.DataBean dataBean = datas.get(a);


                        final AlertDialog show = new ProgressDialog.Builder(context).setTitle("正在加载中。。。").show();
                        OkhttpApi.chongzhi("{\"num\":"+(a+1)+",\"money\":"+i+"}", new Mycall() {
                            @Override
                            public void success(String json) {
                                Log.e("dialog", "success: "+json);
                                Toast.makeText(context, "充值成功！！！", Toast.LENGTH_SHORT).show();

                                int money1 = datas.get(a).getMoney()+i;
                                datas.get(a).setMoney(money1);

                                ZHGLListAdapter.this.notifyDataSetChanged();

                                show.dismiss();
                                alertDialog.dismiss();
                            }

                            @Override
                            public void faild(IOException e) {
                                Toast.makeText(context, "充值失败！！！", Toast.LENGTH_SHORT).show();
                                show.dismiss();
                                alertDialog.dismiss();
                            }
                        });





                    }
                });

            }
        });





        return convertView;
    }
}

package cdictv.moni.adatper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cdictv.moni.R;
import cdictv.moni.bean.RenshuBean;

public class ListviewAdatper extends BaseAdapter {
    private List<RenshuBean.DataBean> mData;
    private Context mContext;
    public ListviewAdatper(List<RenshuBean.DataBean> data,Context context) {
        mData=data;
        mContext=context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        RenshuBean.DataBean mdata=mData.get(position);
        if(convertView==null){
            view=View.inflate(mContext, R.layout.item_busdetails,null);
        }else {
            view=convertView;
        }
        TextView id=view.findViewById(R.id.dia_id);
        TextView name=view.findViewById(R.id.dia_name);
        TextView renshu=view.findViewById(R.id.dia_renshu);
       id.setText(mdata.id+"");
       name.setText(mdata.num+"");
        renshu.setText(mdata.renshu+"");
        return view;
    }
}

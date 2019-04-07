package cdictv.moni.adatper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cdictv.moni.R;
import cdictv.moni.bean.RenshuBean;
import cdictv.moni.bean.ZhantaiBean;

public class MyBusAateper extends BaseExpandableListAdapter {
    private List<ZhantaiBean.DataBean.BusBean> mBus;
    private List<RenshuBean.DataBean> mData;
    private Context mContext;
    public MyBusAateper(List<ZhantaiBean.DataBean.BusBean> Bus,List<RenshuBean.DataBean> data,Context context) {
        mBus=Bus;
        mData=data;
        mContext=context;
    }

    @Override
    public int getGroupCount() {
        return mBus.size();
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return mBus.get(groupPosition).num.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mBus.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mBus.get(groupPosition).num.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       View view;
        ZhantaiBean.DataBean.BusBean busBean=mBus.get(groupPosition);
       if(convertView==null){
          view=View.inflate(mContext, R.layout.item_busquery_group,null);
       }else {
          view=convertView;
       }
        TextView textView=view.findViewById(R.id.station);
       textView.setText(busBean.zhantai);
        ImageView img = view.findViewById(R.id.img);
        if(isExpanded){
            img.setImageResource(R.mipmap.triangle_bottom);
        }else {
            img.setImageResource(R.mipmap.triangle_right);
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        View view;
        ZhantaiBean.DataBean.BusBean.NumBean numBean=mBus.get(groupPosition).num.get(childPosition);
        RenshuBean.DataBean dataBean=mData.get(childPosition);
        if(convertView==null){
            view=View.inflate(mContext, R.layout.item_busquery_child,null);
        }else {
            view=convertView;
        }
        TextView tv_icon=view.findViewById(R.id.bus_id);
        TextView tv_id=view.findViewById(R.id.distance);
        TextView tv_renshu=view.findViewById(R.id.bus_renshu);
        tv_icon.setText(numBean.name+"");
        tv_id.setText("距离站台"+numBean.distance+"米");
        tv_renshu.setText(dataBean.renshu+"人");
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

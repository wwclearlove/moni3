package cdictv.moni.fagement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cdictv.moni.R;

public class biefragment extends Fragment  {
    private View view;
    private ImageView left_menu;
    private TextView title;
    private RelativeLayout tool_bar;
    private TextView xiao_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contentfragment, container, false);
        initView(view);
//        showChart(getPieData());
        return view;
    }

    private void initView(View view) {
        left_menu = (ImageView) view.findViewById(R.id.left_menu);
        title = (TextView) view.findViewById(R.id.title);
        tool_bar = (RelativeLayout) view.findViewById(R.id.tool_bar);
        xiao_title = (TextView) view.findViewById(R.id.xiao_title);


    }


}

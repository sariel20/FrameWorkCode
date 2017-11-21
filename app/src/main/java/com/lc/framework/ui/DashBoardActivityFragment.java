package com.lc.framework.ui;

import android.support.v7.widget.Toolbar;

import com.lc.core.BaseRxFragment;
import com.lc.framework.R;

import butterknife.BindView;

/**
 * Created by LiangCheng on 2017/8/22.
 */

public class DashBoardActivityFragment extends BaseRxFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("DashBoard");

    }
}

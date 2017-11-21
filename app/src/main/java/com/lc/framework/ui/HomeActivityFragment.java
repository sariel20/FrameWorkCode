package com.lc.framework.ui;

import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.lc.core.BaseRxFragment;
import com.lc.core.view.PtrRecyclerView;
import com.lc.framework.R;

import butterknife.BindView;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by LiangCheng on 2017/8/22.
 */

public class HomeActivityFragment extends BaseRxFragment {

    private static final String TAG = "HomeActivityFragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recy_home)
    PtrRecyclerView recyclerView;

    private MultiTypeAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    private ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

    @Override
    protected void initView() {
        toolbar.setTitle("Home");

        threadLocal.set(true);
        Log.e(TAG, "ThreadLocal(main): " + threadLocal.get() + "");

        new Thread("Thread#1") {
            @Override
            public void run() {
                threadLocal.set(false);
                Log.e(TAG, "Thread#1: " + threadLocal.get() + "");
            }
        }.start();

        new Thread("Thread#2") {
            @Override
            public void run() {
                Log.e(TAG, "Thread#2: " + threadLocal.get() + "");
            }
        }.start();

        new Thread("Thread#Loop") {
            @Override
            public void run() {
                Looper.prepare();

                Looper.loop();
            }
        };
    }
}

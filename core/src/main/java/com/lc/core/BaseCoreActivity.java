package com.lc.core;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lc.core.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * Created by LiangCheng on 2017/8/21.
 */

public abstract class BaseCoreActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayoutId());
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);//点击其他位置progressDialog消失

        initView();
    }

    public void showToast(String msg) {
        ToastUtils.show(msg);
    }

    public void showProgress(String msg) {
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    public void dismissProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();
}

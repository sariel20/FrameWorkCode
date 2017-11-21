package com.lc.framework.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.lc.core.BaseCoreActivity;
import com.lc.framework.R;
import com.lc.framework.adapter.ViewPagerAdapter;
import com.lc.framework.weight.NoScrollViewPager;

import butterknife.BindView;

public class MainActivity extends BaseCoreActivity {

    @BindView(R.id.viewpager)
    NoScrollViewPager viewPager;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setupViewPager(viewPager);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeActivityFragment());
        adapter.addFragment(new DashBoardActivityFragment());
        adapter.addFragment(new NotificationsActivityFragment());
        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(3);//缓存个数

    }

}

package ping.com.happy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import ping.com.happy.base.BaseActivity;
import ping.com.happy.fragment.NewsFragment;
import ping.com.happy.utils.JLog;
import ping.com.happy.utils.ToastUtil;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();

    }

    private void initData() {
        JLog.i("执行了");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_center,new NewsFragment()).commit();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.menu_tb);
        //通过这个方式设置之后,就会将所有属性设置到actionbar上,就inflateMenu就失效了,要通过meun的方式进行
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_draw);

        fl= (FrameLayout) findViewById(R.id.main_center);
    }

    private void initListener() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                mAnimationDrawable.stop();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                mAnimationDrawable.start();
            }
        };
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_base_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                ToastUtil.showToast(this, item.getTitle().toString() + "被点击了");
                break;
            case R.id.item2:
                ToastUtil.showToast(this, item.getTitle().toString() + "被点击了");
                break;
            case R.id.item3:
                ToastUtil.showToast(this, item.getTitle().toString() + "被点击了");
                break;
            case R.id.item4:
                ToastUtil.showToast(this, item.getTitle().toString() + "被点击了");
                break;
        }
        return true;
    }
}

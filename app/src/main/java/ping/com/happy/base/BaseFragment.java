package ping.com.happy.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description：所有Fragment的基类,用来进行一些共同的操作
 *
 * @Author：桑小年
 * @Data：2016/6/23 17:13
 */
public abstract class BaseFragment extends Fragment {

    public View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view = initView(inflater,container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

    /**
     * 初始化控件
     * @param inflater
     * @param container
     * @return
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

}

package ping.com.happy.activity.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/6/24 14:36
 */
public class VpFgAdapter<T> extends FragmentStatePagerAdapter {

    private List<T> mDatas;

    public VpFgAdapter(FragmentManager fm, List<T> mDatas) {
        super(fm);
        this.mDatas=mDatas;
    }

    @Override
    public Fragment getItem(int position) {

        return (Fragment) mDatas.get(position);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
}

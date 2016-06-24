package ping.com.happy.activity.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/6/23 18:00
 */
public abstract class ViewPagerAdapter<T> extends PagerAdapter {

    private List<T> mDatas;
    private Context context;

    public ViewPagerAdapter(List<T> mDatas ,Context context){
        if (mDatas==null){
            throw new RuntimeException("传入的数据不能为空");
        }
        this.mDatas=mDatas;
        this.context=context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view =conver( position,mDatas.get(position));
        container.addView(view);

        return  view ;
    }

    public abstract View conver(int position, T t) ;
}




















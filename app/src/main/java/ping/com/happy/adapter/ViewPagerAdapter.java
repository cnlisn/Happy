package ping.com.happy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import ping.com.happy.HttpView;
import ping.com.happy.R;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2016/6/23 18:00
 */
public class ViewPagerAdapter<T> extends PagerAdapter {

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
        HttpView view = (HttpView) LayoutInflater.from(context).inflate(R.layout.http_layout,container,false);
        container.addView(view);
        return  view ;
    }
}




















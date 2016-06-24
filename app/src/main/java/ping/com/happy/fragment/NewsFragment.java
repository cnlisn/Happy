package ping.com.happy.fragment;

import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import ping.com.happy.R;
import ping.com.happy.adapter.CommonAdapter;
import ping.com.happy.adapter.ViewHolder;
import ping.com.happy.adapter.ViewPagerAdapter;
import ping.com.happy.base.BaseFragment;
import ping.com.happy.bean.TianXingGirs;
import ping.com.happy.config.Config;
import ping.com.happy.http.GirlsService;
import ping.com.happy.http.HttpMethods;
import ping.com.happy.utils.JLog;
import ping.com.happy.utils.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description：新闻信息的Fragment类
 *
 * @Author：桑小年
 * @Data：2016/6/23 17:17
 */
public class NewsFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private ViewPager mViewPager;

    private int item;//被点击的位置

    private List<String> mDatas;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recy);
        mViewPager = (ViewPager) view.findViewById(R.id.news_vp);

        return view;
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDatas.add("测试"+i);
        }

        LinearLayoutManager manager =new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new CommonAdapter<String>(getContext(),R.layout.news_title_item,mDatas) {
            @Override
            public void convert(final ViewHolder holder, final String o, int position) {
                Button bt = holder.getView(R.id.news_item_tv);
                bt.setText(o);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(),o);
                        item=holder.getAdapterPosition();
                        mViewPager.setCurrentItem(holder.getAdapterPosition());
                    }
                });
            }
        });



        mViewPager.setAdapter(new ViewPagerAdapter<String>(mDatas,getContext()));
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                mRecyclerView.scrollToPosition(position);

            }
        });

    }



}

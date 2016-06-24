package ping.com.happy.activity.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ping.com.happy.R;
import ping.com.happy.activity.adapter.CommonAdapter;
import ping.com.happy.activity.adapter.ViewHolder;
import ping.com.happy.activity.adapter.ViewPagerAdapter;
import ping.com.happy.base.BaseFragment;
import ping.com.happy.bean.NewsTitle;
import ping.com.happy.operation.GirsOp;
import ping.com.happy.utils.ToastUtil;

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

    private List<NewsTitle> mDatas;
    private GirsOp girsOp;

    private String[] titles = {
            "精选文章", "精选美女",
            "精选笑话",
            "社会新闻",
            "科技新闻"
    };
    private String[] urls = {"wxnew", "meinv", "huabian", "social", "keji"};

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
        for (int i = 0; i < 5; i++) {
            NewsTitle title = new NewsTitle();
            title.tittle = titles[i];
            title.url = urls[i];
            mDatas.add(title);
        }


        girsOp = GirsOp.getInstance(getContext());

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new CommonAdapter<NewsTitle>(getContext(), R.layout.news_title_item, mDatas) {
            @Override
            public void convert(final ViewHolder holder, final NewsTitle o, int position) {
                Button bt = holder.getView(R.id.news_item_tv);
                bt.setText(o.tittle);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showToast(getContext(), o.tittle);
                        item = holder.getAdapterPosition();
                        mViewPager.setCurrentItem(holder.getAdapterPosition());
                    }
                });
            }
        });

        mViewPager.setAdapter(new ViewPagerAdapter<NewsTitle>(mDatas, getContext()) {
            @Override
            public View conver(int position, NewsTitle t) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.http_layout, null);
                TextView load = (TextView) view.findViewById(R.id.loding);
                TextView fail = (TextView) view.findViewById(R.id.fail);
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.success);
                girsOp.getData(0, t.url, load, fail, recyclerView);
                return view;
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mRecyclerView.scrollToPosition(position);

            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        girsOp.unsubcride();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

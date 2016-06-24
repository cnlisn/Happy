package ping.com.happy.operation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.Map;

import ping.com.happy.R;
import ping.com.happy.activity.WebActivity;
import ping.com.happy.activity.adapter.CommonAdapter;
import ping.com.happy.activity.adapter.ViewHolder;
import ping.com.happy.bean.TianXingGirs;
import ping.com.happy.config.Config;
import ping.com.happy.utils.JLog;
import ping.com.happy.utils.ToastUtil;
import ping.com.happy.utils.http.HttpMethods;
import rx.Subscriber;

/**
 * Description：针对图片界面的操作
 *
 * @Author：桑小年
 * @Data：2016/6/24 18:17
 */
public class GirsOp {

    private HttpMethods httpMethods;

    private GirsOp(Context context) {
        this.context=context;
    }

    private static GirsOp girsOp;
    private Context context;

    public static GirsOp getInstance(Context context) {
        if (girsOp==null){
            girsOp=new GirsOp(context);
        }
        return girsOp;
    };


    public void getData(int page, String url, final TextView load, final TextView fail, final RecyclerView recyclerView) {

        Map<String, String> params = new ArrayMap<>();
        params.put("key", Config.tian_key);
        params.put("num", "20");
        params.put("rand", "0");
        params.put("word", "清纯");
        params.put("page", String.valueOf(page));

        refushState(0, load, fail, recyclerView, null);
        httpMethods = new HttpMethods();
        httpMethods.setRetrofit(url, params, new Subscriber<TianXingGirs>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                refushState(0, load, fail, recyclerView, null);
            }

            @Override
            public void onNext(TianXingGirs o) {

                if (o.getCode() == 200) {
                    refushState(2, load, fail, recyclerView, o);
                } else {
                    refushState(1, load, fail, recyclerView, o);
                }
            }
        });

    }

    public void refushState(int state, TextView load, TextView fail, RecyclerView recyclerView, TianXingGirs o) {
        switch (state) {
            case 0:
                load.setVisibility(View.VISIBLE);
                fail.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
            case 1:
                fail.setVisibility(View.VISIBLE);
                load.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
            //成功
            case 2:
                if (o == null) {
                    throw new RuntimeException("传入数据是null");
                }
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(new CommonAdapter<TianXingGirs.NewslistBean>(context, R.layout.news_content_item, o.getNewslist()) {
                    @Override
                    public void convert(ViewHolder holder, final TianXingGirs.NewslistBean o, int position) {
                        ToastUtil.showToast(context, holder.getLayoutPosition() + "");
                        ((TextView) holder.getView(R.id.new_content_des)).setText(o.getDescription());
                        ImageView img = holder.getView(R.id.news_content_img);
                        if (context!=null) {
                            with = Glide.with(context.getApplicationContext());
                            with.load(o.getPicUrl())
                                    .fitCenter()
                                    .error(R.drawable.fail)
                                    .placeholder(R.drawable.loading)
                                    .into(img);
                        }
                        final CardView cardView = holder.getView(R.id.new_title_card);

                        cardView.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                switch (event.getAction()) {
                                    case MotionEvent.ACTION_DOWN:
//                                        cardView.setElevation(10);
                                }
                                return false;
                            }
                        });
                        cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, WebActivity.class);
                                intent.putExtra("news", o.getUrl());
                                context.startActivity(intent);
                            }
                        });

                    }
                });
                fail.setVisibility(View.GONE);
                load.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
        }
    }

    private RequestManager with;
    public void unsubcride(){
        httpMethods.cancleRetrofit();

    }
}
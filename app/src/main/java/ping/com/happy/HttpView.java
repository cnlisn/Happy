package ping.com.happy;

import android.content.Context;
import android.hardware.Camera;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.Map;

import ping.com.happy.adapter.CommonAdapter;
import ping.com.happy.adapter.ViewHolder;
import ping.com.happy.bean.TianXingGirs;
import ping.com.happy.config.Config;
import ping.com.happy.http.HttpMethods;
import ping.com.happy.utils.JLog;
import rx.Subscriber;

/**
 * 这是一个自定义的 RelativeLayout自定义布局文件，会首先进行加载，然后更新数据
 * 作者： ${桑小年} on 2016/6/23.
 * 努力，为梦长留
 */
public class HttpView extends RelativeLayout {

    private View loading, success, fail;

    public HttpView(Context context) {
        super(context);
        initView();
    }

    public HttpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public HttpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //初始化控件

    }

    /**
//     * 加载中。。
//     */
    public void loading() {
        loading.setVisibility(View.VISIBLE);
        if (View.GONE != fail.getVisibility()) {
            fail.setVisibility(View.GONE);
        }
        if (View.GONE != success.getVisibility()) {
            success.setVisibility(View.GONE);
        }
        if (listener != null) {
            listener.onLoading();
        }
        getData(1);
    }

    /**
     * 加载失败
     */

    public void fail() {
        if (View.GONE == fail.getVisibility()) {
            fail.setVisibility(View.VISIBLE);
        }
        if (View.GONE != loading.getVisibility()) {
            loading.setVisibility(View.GONE);
        }
        if (View.GONE != success.getVisibility()) {
            success.setVisibility(View.GONE);
        }
        if (listener != null) {
            listener.onFail();
        }

    }

    /**
     * 加载成功时候调用
     */
    public void successed() {

            success.setVisibility(View.VISIBLE);

            loading.setVisibility(View.GONE);

            fail.setVisibility(View.GONE);


        if (listener != null) {
            listener.onSuccessed();
        }
    }

    public void setOnLoadListener(OnLoadListener listener) {
        this.listener = listener;
    }


    private OnLoadListener listener;

    public interface OnLoadListener {

        /**
         * 初始化加载时候调用
         */
        void onLoading();

        /**
         * 加载失败后调用
         */
        void onFail();

        /**
         * 在加载成功后，调用，用来
         */
        void onSuccessed();


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            switch (v.getId()) {
                case R.id.loding:
                    loading = v;
                    break;
                case R.id.fail:
                    fail = v;
                    break;
                case R.id.success:
                    success = v;
                    break;
            }
        }
        loading();
        JLog.i(loading.toString());
    }

    private void getData(int page) {

        JLog.i("-----------------------------知悉了-------------------------------------");
        Map<String, String> params = new ArrayMap<>();
        params.put("key", Config.tian_key);
        params.put("num", "20");
        params.put("rand", "0");
        params.put("word", "上海");
        params.put("page", String.valueOf(page));


        new HttpMethods ().setRetrofit("meinv", params, new Subscriber<TianXingGirs>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                fail();
            }

            @Override
            public void onNext(TianXingGirs o) {
                JLog.i(o.toString());
                successed();
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                JLog.i(o.getNewslist().size()+"");
            }
        });

    }
}

package ping.com.happy.http;

import android.support.v4.util.ArrayMap;

import java.util.Map;

import ping.com.happy.bean.TianXingGirs;
import ping.com.happy.config.Config;
import ping.com.happy.utils.JLog;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description：联网的封装类
 *
 * @Author：桑小年
 * @Data：2016/6/24 10:04
 */
public class HttpMethods {

    private final Retrofit retrofit;
    private final GirlsService girlsService;


    private Subscriber<TianXingGirs> subscriber;

    public HttpMethods (){
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.tian_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        girlsService = retrofit.create(GirlsService .class);
    }




    /**
     * 通过对应的参数进行联网
     * @param load 路径改变参数
     * @param params 参数
     * @param subscriber 订阅
     */
    public void setRetrofit (String load,Map<String, String> params,Subscriber<TianXingGirs> subscriber){

        this.subscriber=subscriber;

        Observable<TianXingGirs> repos = girlsService.getRepos(load, params);
        repos.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);


    }


    /**
     * 取消网络请求
     */
    public void  cancleRetrofit(){
        subscriber.unsubscribe();
    }

}

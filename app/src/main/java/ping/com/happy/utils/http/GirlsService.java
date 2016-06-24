package ping.com.happy.utils.http;

import java.util.Map;

import ping.com.happy.bean.TianXingGirs;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * refti网络加载接口
 * 作者： ${桑小年} on 2016/6/23.
 * 努力，为梦长留
 */
public interface GirlsService  {

    @GET("{meinv}")
    Observable<TianXingGirs> getRepos(@Path("meinv") String sort, @QueryMap Map<String,String> map);

    @GET("{meinv}")
    Observable<TianXingGirs> getRepos1(@Path("meinv") String sort, @Query("key") String key,@Query("num") String num);
}

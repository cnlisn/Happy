package ping.com.happy.http;

import java.util.Map;

import okhttp3.ResponseBody;
import ping.com.happy.bean.TianXingGirs;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * refti网络加载接口
 * 作者： ${桑小年} on 2016/6/23.
 * 努力，为梦长留
 */
public interface GirlsService {

    @GET("{meinv}")
    Call<TianXingGirs> getRepos(@Path("meinv") String sort, @QueryMap Map<String,String> map);
}

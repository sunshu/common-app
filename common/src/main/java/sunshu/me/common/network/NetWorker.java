package sunshu.me.common.network;

import io.reactivex.internal.schedulers.NewThreadWorker;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @Describe:
 * @Package: sunshu.me.common.network
 * @Author: sunshu
 * @Date: 2019/4/29/ 17:25
 * @Copyright: jingyou
 */

public class NetWorker {

  private static NetWorker netWorker;
  private Retrofit mRetrofit;

  /**
   * 单例
   */
  private NetWorker(){}
  public static NetWorker getInstance(){
    if (netWorker == null){
      synchronized (NetWorker.class){
        if (netWorker == null){
          netWorker = new NetWorker();
        }
      }
    }
    return  netWorker;
  }

  public void asyncNetWorkJson(final int requestCode, Call<ResponseBody> requestCall, final NetworkListener listener){
      if (listener == null){
          return;
      }

    requestCall.enqueue(new Callback<ResponseBody>() {
      @Override
      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        call.enqueue(new Callback<ResponseBody>() {
          @Override
          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()){
              ResponseBody result = response.body();
              if (result == null) {
                listener.onDataError(requestCode, response.code(), "未获得数据");
                return;
              }
              listener.onDataSuccess(result);
            }
          }

          @Override
          public void onFailure(Call<ResponseBody> call, Throwable t) {
            listener.onDataError(requestCode, -1, "未获得数据");
          }
        });
      }

      @Override
      public void onFailure(Call<ResponseBody> call, Throwable t) {

      }
    });

  }

}

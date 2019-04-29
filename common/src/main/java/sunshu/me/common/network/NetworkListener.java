package sunshu.me.common.network;

import okhttp3.ResponseBody;

/**
 * @Describe:
 * @Package: sunshu.me.common.network
 * @Author: sunshu
 * @Date: 2019/4/29/ 17:35
 * @Copyright: jingyou
 */

public interface NetworkListener {


  /**
   * 服务器返回成功回调
   * @param response
   */
  void onDataSuccess(ResponseBody response);

  /**
   * 调用失败回调
   */
  void onDataError(int requestCode, int responseCode, String message);
}

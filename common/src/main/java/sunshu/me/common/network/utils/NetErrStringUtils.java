package sunshu.me.common.network.utils;

import android.content.Context;


/**
 * @Describe:网络错误提示工具类
 * @Package: com.jy.core.lib.network.util
 * @Author: wl
 * @Date: 2018/1/17 0017 上午 10:46
 * @Copyright: jingyou
 */
public class NetErrStringUtils {

    public static final int ERR_404 = 404;

    public static final int ERR_500 = 500;

    public static final int ERR_502 = 502;

    public static String getErrString(Context context, int code) {
        String result;
        switch (code) {
            case ERR_404:
                result = "无法找到指定位置的资源";
                break;
            case ERR_500:
                result = "内部服务器错误";
                break;
            case ERR_502:
                result = "网关错误";
                break;
            default:
                result = "网络错误";
                break;
        }
        return result;
    }

    public static String getErrString(Context context, Throwable t) {
        String result;
        if (t instanceof java.net.SocketTimeoutException) {
            result = "网络超时";
        } else if (t != null && t.getMessage() != null && t.getMessage().equalsIgnoreCase("canceled")) {
            result = "请求已经取消";
        } else {
            result = "网络错误" + t.getMessage();
        }
        return result;
    }
}

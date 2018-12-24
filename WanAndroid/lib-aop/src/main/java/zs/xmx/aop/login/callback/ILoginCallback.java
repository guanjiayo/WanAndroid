package zs.xmx.aop.login.callback;

import android.content.Context;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/8 11:23
 * @本类描述	  登录状态回调接口
 * @内容说明
 *  todo 参考下takephoto库,能不能也能类实现这个接口就能拿到数据
 */
public interface ILoginCallback {

    /**
     * 判断是否登录
     *
     * @param context
     */
    boolean isLogin(Context context);

    /**
     * 未登录的事件处理
     *
     * @param context
     */
    void unLogin(Context context, int tipType);
}

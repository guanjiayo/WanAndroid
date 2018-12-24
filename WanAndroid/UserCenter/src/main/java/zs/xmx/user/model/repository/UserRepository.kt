package zs.xmx.user.model.repository

import io.reactivex.Observable
import zs.xmx.baselibrary.net.retrofit_rx.RxRestClient
import java.util.*
import javax.inject.Inject

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/15 14:45
 * @本类描述	  网络请求真正的实现类
 * @内容说明  请求服务器返回json
 *        todo 如果后面迭代开发还是这种形式,封装成网络请求,本地数据等一些数据的真正实现类
 */
class UserRepository @Inject constructor() {

    /**
     * 注册
     */
    fun register(lastUrl: String, params: WeakHashMap<String, Any>): Observable<String> {
        return RxRestClient.create().lastUrl(lastUrl).params(params).build().post()
    }

    /**
     * 登录
     */
    fun login(lastUrl: String, params: WeakHashMap<String, Any>): Observable<String> {
        return RxRestClient.create().lastUrl(lastUrl).params(params).build().post()
    }

    /**
     * 退出登录
     */
    fun logout(lastUrl: String): Observable<String> {
        return RxRestClient.create().lastUrl(lastUrl).build().get()
    }

}
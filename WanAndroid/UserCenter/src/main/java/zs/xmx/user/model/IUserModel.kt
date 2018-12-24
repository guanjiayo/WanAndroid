package zs.xmx.user.model

import io.reactivex.Observable
import zs.xmx.user.model.domain.RegisterInfo
import zs.xmx.user.model.domain.UserInfo
import java.util.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/29 20:02
 * @本类描述	  定义用户模块的请求服务器接口方法
 * @内容说明
 * @补充说明
 *
 *
 */
interface IUserModel {

    //注册
    fun register(lastUrl: String, params: WeakHashMap<String, Any>): Observable<RegisterInfo>

    //登录
    fun login(lastUrl: String, params: WeakHashMap<String, Any>): Observable<UserInfo>

    //退出登录
    fun logout(lastUrl: String): Observable<String>

}
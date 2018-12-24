package zs.xmx.user.model.impl

import com.google.gson.Gson
import io.reactivex.Observable
import zs.xmx.baselibrary.base.parse.BaseResp
import zs.xmx.baselibrary.base.parse.ParameterizedTypeImpl
import zs.xmx.baselibrary.ext.convert
import zs.xmx.user.model.IUserModel
import zs.xmx.user.model.domain.RegisterInfo
import zs.xmx.user.model.domain.UserInfo
import zs.xmx.user.model.repository.UserRepository
import java.util.*
import javax.inject.Inject

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  数据解析实现类
 * @内容说明   解析Json数据并转换成我们想要的bean类(在这个类我们看情况使用Gson,FastJson,JsonObject)
 *
 */
/**
 * 经过几天测试所得,TypeToken一定要传一个固定的实体类,传泛型会找不到类型,因此这里需要这样实现
 * (Retrofit/okHttp需要1这样定义请求接口,可能也是因为这个原因吧)
 */
class UserModelImpl @Inject constructor() : IUserModel {

    @Inject
    lateinit var repository: UserRepository

    /**
     * 注册
     */
    override fun register(lastUrl: String, params: WeakHashMap<String, Any>): Observable<RegisterInfo> {
        return repository.register(lastUrl, params).flatMap { t ->
            Observable.just(Gson().fromJson<BaseResp<RegisterInfo>>(t,
                    object : ParameterizedTypeImpl<RegisterInfo>() {}.getType()))
        }.convert()
    }

    /**
     * 登录
     */
    override fun login(lastUrl: String, params: WeakHashMap<String, Any>): Observable<UserInfo> {
        return repository.login(lastUrl, params).flatMap { t ->
            Observable.just(Gson().fromJson<BaseResp<UserInfo>>(t,
                    object : ParameterizedTypeImpl<UserInfo>() {}.getType()))
        }.convert()
    }

    /**
     * 退出登录
     */
    override fun logout(lastUrl: String): Observable<String> {
        return repository.logout(lastUrl)
    }


}
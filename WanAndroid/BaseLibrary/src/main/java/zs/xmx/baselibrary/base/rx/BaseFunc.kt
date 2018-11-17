package zs.xmx.baselibrary.base.rx

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.functions.Function
import zs.xmx.baselibrary.constant.ResultCode
import zs.xmx.baselibrary.utils.Logger

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/5 8:27
 * @本类描述
 * @内容说明
 *
 */

// 在 kotlin中扩展 Java 类的 Gson.fromJson 方法
// 不在传入 T 的class，inline 的作用就是将函数插入到被调用处，配合 reified 就可以获取到 T 真正的类型
inline fun <reified T : Any> Gson.fromJson(json: String): T {
    return this.fromJson(json, T::class.java)
}

/**
 * 将BaseBean类转为自定义Bean类
 */
class ConvertFunc<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        if (t.errorCode != ResultCode.Success) {
            return Observable.error(BaseException(t.errorMsg, t.errorCode))
        }
        Logger.e("测试","${t}")
        return Observable.just(t.data)
    }

}

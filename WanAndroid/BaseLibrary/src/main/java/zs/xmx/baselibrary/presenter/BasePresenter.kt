package zs.xmx.baselibrary.presenter

import android.content.Context
import com.hwangjr.rxbus.RxBus
import com.trello.rxlifecycle3.LifecycleProvider
import zs.xmx.baselibrary.base.view.IBaseView
import zs.xmx.baselibrary.utils.NetWorkUtils
import javax.inject.Inject


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/9/5 18:16
 * @本类描述	  BasePresenter
 * @内容说明  1.这里直接使用泛型的方式直接拿到View层回调
 *
 *            2. 子类Presenter  M层数据 回调给 V层的方式:(目前用第二种)
 *              1.M层实现类,用 Bus 传到Presenter的接受器(这种方式要写tag,不然不知道传给谁)
 *              2.直接在子类Presenter接口回调数据(可能有内存泄漏问题)
 *              3.RxJava监听对象(参考慕课Kotlin商城项目)
 *              4.省略M层接口,,直接在Presenter层处理数据
 *
 *            3. 由于请求网络返回的都是Json字符串(防止数据格式类型引起错误),都交由BasePresenter处理
 *               1. 解析字符串
 *               2. 转BaseResponse对象
 *               3. 转自定义Bean类对象
 *
 */

/**
 * 1. 使用泛型方式拿到 View层的引用(子类传IBaseView的子类)
 * 2. 子类Presenter 使用实现的方式拿到各自M层的引用(目前用了Dagger)
 * 3. 子类Presenter 进行 M层 和 V层 的数据绑定(1.接口回调方式,2.BusEvent方式)
 */
open class BasePresenter<T : IBaseView> {

    protected val tag: String = this@BasePresenter.javaClass.simpleName
    //当前显示的View用弱引用,防止用户不小心点到退出的时候,内存泄漏
    //BasePresenter<T : IBaseView>(view:T)
    //lateinit var mView: WeakReference<T>(view)
    lateinit var mView: T

    //Dagger注入，Rx生命周期管理
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

//    init {
//        //如果是使用Bus方式返回数据给子类Presenter,恢复此处代码
//        registerBus()
//    }

    //注册RxBus
    private fun registerBus() {
        RxBus.get().register(this)
    }

    //反注册RxBus
    /**
     * 如果是使用Bus方式返回数据给子类Presenter,需要在每个Activity/Fragment
     *
     */
    //todo 这里需要研究下怎么把这个移到每个子类的默认OnDestroy()方法里面使用
    open fun unRegisterBus() {
        RxBus.get().unregister(this)
    }

    /**
     * 检查网络是否可用
     * <p>
     * 业务逻辑:
     * 1. 检查网络连通情况
     * 2. 请求网络数据
     */
    fun checkNetWork(): Boolean {
        if (NetWorkUtils.isConnected(context)) {
            return true
        }
        mView.onError("网络不可用")
        return false
    }

}
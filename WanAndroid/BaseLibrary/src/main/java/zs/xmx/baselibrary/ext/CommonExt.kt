package zs.xmx.baselibrary.ext


import android.view.View
import android.widget.Button
import android.widget.EditText
import com.trello.rxlifecycle3.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import zs.xmx.baselibrary.base.rx.BaseResp
import zs.xmx.baselibrary.base.rx.BaseSubscriber
import zs.xmx.baselibrary.base.rx.ConvertFunc
import zs.xmx.baselibrary.utils.watcher.DefaultTextWatcher

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 2:56
 * @本类描述	  Kotlin 的通用扩展
 * @内容说明
 *
 */

/*
    扩展Observable执行
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}

/**
 * 扩展数据转换
 * <p>
 * BaseBean<T> 转自定义Bean
 *
 */
fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(ConvertFunc())
}


/*
    扩展点击事件
    使用: btn_regiest.onClick(this)
 */
fun View.onClick(listener: View.OnClickListener): View {
    setOnClickListener(listener)
    return this
}

/*
    扩展点击事件，参数为方法(lambda形式)
    使用: btn_regiest.onClick{ 点击后续操作  }
 */
fun View.onClick(method: () -> Unit): View {
    setOnClickListener { method() }
    return this
}


/**
 * 扩展Button是否可以点击事件
 * @params et EditText控件
 * @params method 某个void方法的返回值
 */
fun Button.enableClick(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}


/**
 * 扩展视图可见性
 */
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

package zs.xmx.baselibrary.base.rx


import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import zs.xmx.baselibrary.base.view.IBaseView


/**
 * Rx订阅者默认实现
 *
 */
open class BaseSubscriber<T>(private val mBaseView: IBaseView) : Observer<T> {

    override fun onNext(t: T) {
        mBaseView.hideLoading()
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onError(e: Throwable) {
        mBaseView.hideLoading()
        if (e is BaseException) {
            mBaseView.onError(e.msg)
        }
    }

    override fun onComplete() {
        mBaseView.hideLoading()
    }


}

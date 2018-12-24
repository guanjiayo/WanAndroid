package zs.xmx.user.presenter


import zs.xmx.baselibrary.base.rx.BaseSubscriber
import zs.xmx.baselibrary.ext.execute
import zs.xmx.baselibrary.presenter.BasePresenter
import zs.xmx.user.constant.UserConstant
import zs.xmx.user.model.IUserModel
import zs.xmx.user.view.activity.ISettingView
import javax.inject.Inject


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/15 23:23
 * @本类描述   MePresenter
 * @内容说明
 *
 */
class SettingPresenter @Inject constructor() : BasePresenter<ISettingView>() {

    @Inject
    lateinit var mModel: IUserModel

    fun logout() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mModel.logout(UserConstant.URL_LOGOUT).execute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onLogoutResult()
            }
        }, lifecycleProvider)


    }


}
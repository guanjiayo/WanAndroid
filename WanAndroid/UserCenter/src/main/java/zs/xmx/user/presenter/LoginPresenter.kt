package zs.xmx.user.presenter


import zs.xmx.baselibrary.base.rx.BaseSubscriber
import zs.xmx.baselibrary.ext.execute
import zs.xmx.baselibrary.presenter.BasePresenter
import zs.xmx.baselibrary.utils.Logger
import zs.xmx.user.constant.UserConstant
import zs.xmx.user.model.IUserModel
import zs.xmx.user.model.domain.UserInfo
import zs.xmx.user.view.activity.ILoginView
import java.util.*
import javax.inject.Inject


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/28 23:23
 * @本类描述
 * @内容说明
 *
 */
class LoginPresenter @Inject constructor() : BasePresenter<ILoginView>() {

    //Dagger注入, M 层的引用
    //val mModel: IUserModel = UserModelImpl()
    @Inject
    lateinit var mModel: IUserModel //Dagger注入的方式(常规方式)

    /**
     *
     * @param pushId 推送类型(普通用户/会员)
     */
    fun login(params: WeakHashMap<String, Any>, pushId: String) {
        //todo 这个pushId要和服务器联动的
        Logger.e(tag, "推送对象消息ID $pushId")
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mModel.login(UserConstant.URL_LOGIN, params).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onLoginResult(t)
            }
        }, lifecycleProvider)


    }

}
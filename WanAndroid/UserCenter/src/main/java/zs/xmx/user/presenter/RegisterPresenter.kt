package zs.xmx.user.presenter

import zs.xmx.baselibrary.base.rx.BaseSubscriber
import zs.xmx.baselibrary.ext.execute
import zs.xmx.baselibrary.presenter.BasePresenter
import zs.xmx.user.constant.UserConstant
import zs.xmx.user.model.IUserModel
import zs.xmx.user.model.domain.RegisterInfo
import zs.xmx.user.view.activity.IRegisterView
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

class RegisterPresenter @Inject constructor() : BasePresenter<IRegisterView>() {

    //Dagger注入, M 层的引用
    //val mModel: IUserModel = UserModelImpl()
    @Inject
    lateinit var mModel: IUserModel //Dagger注入的方式(常规方式)

    fun register(params: WeakHashMap<String, Any>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mModel.register(UserConstant.URL_REGISTER, params).execute(object : BaseSubscriber<RegisterInfo>(mView) {
            override fun onNext(t: RegisterInfo) {
                //todo mobApi 注册成功的result是一个uid,我们这里直接返回"注册成功" 即可
                mView.onRegisterResult("注册成功")
            }
        }, lifecycleProvider)


    }


}


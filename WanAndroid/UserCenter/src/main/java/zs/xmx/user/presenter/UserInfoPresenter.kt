package zs.xmx.user.presenter

import zs.xmx.baselibrary.presenter.BasePresenter
import zs.xmx.user.model.IUserModel
import zs.xmx.user.view.activity.IUserInfoView
import javax.inject.Inject

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/28 23:23
 * @本类描述
 * @内容说明
 *
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<IUserInfoView>() {

    //Dagger注入, M 层的引用
    //val mModel: IUserModel = UserModelImpl()
    @Inject
    lateinit var mModel: IUserModel //Dagger注入的方式(常规方式)

    fun editUserInfo(params: HashMap<String, Any>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
//        mModel.loadDataFromNet(BaseConstant.URL_LOGIN, params).execute(object : BaseSubscriber<String>(mView) {
//            override fun onNext(t: String) {
//                mView.onLoginResult(t)
//
//            }
//        }, lifecycleProvider)


    }


}
package zs.xmx.user.presenter

import zs.xmx.baselibrary.presenter.BasePresenter
import zs.xmx.user.model.IUserModel
import zs.xmx.user.view.activity.IForgetPwdView
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

class ForgetPwdPresenter @Inject constructor() : BasePresenter<IForgetPwdView>() {

    //Dagger注入, M 层的引用
    //val mModel: IUserModel = UserModelImpl()
    @Inject
    lateinit var mModel: IUserModel //Dagger注入的方式(常规方式)

    fun forgetPwd(params: HashMap<String, Any>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        //todo 网络请求的数据无论如果都是String类型的Json
        //todo 先实现其他的,后面再研究下数据怎么处理
//        mModel.loadDataFromNet(BaseConstant.URL_REGISTER, params).execute(object : BaseSubscriber<BaseResp<String>>(mView) {
//
//            override fun onNext(t: BaseResp<String>) {
//
//                mView.onForgetPwdResult(t.msg)
//
//            }
//
//
//        }, lifecycleProvider)


    }


}


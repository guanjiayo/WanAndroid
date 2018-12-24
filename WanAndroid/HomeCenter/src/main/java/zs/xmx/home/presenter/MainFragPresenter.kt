package zs.xmx.home.presenter

import zs.xmx.baselibrary.presenter.BasePresenter
import zs.xmx.home.fragment.IMainView
import javax.inject.Inject

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/28 23:23
 * @本类描述
 * @内容说明
 *
 */
class MainFragPresenter @Inject constructor() : BasePresenter<IMainView>() {

    //Dagger注入, M 层的引用
    //val mModel: IUserModel = UserModelImpl()
    @Inject
   // lateinit var mModel: IUserModel //Dagger注入的方式(常规方式)

    fun showData( params: HashMap<String, Any>) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
//       mModel.loadDataFromNet(BaseConstant.URL_REGISTER, params).execute(object : BaseSubscriber<String>(mView) {
//            override fun onNext(t: String) {
//                mView.onShowData(t)
//
//            }
//        }, lifecycleProvider)

    }


}
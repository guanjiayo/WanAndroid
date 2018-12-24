package zs.xmx.user.view.activity

import zs.xmx.baselibrary.base.view.IBaseView
import zs.xmx.user.model.domain.UserInfo

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/28 22:53
 * @本类描述	  LoginActivity的 IView接口
 * @内容说明
 *
 */
interface ILoginView : IBaseView {
    /**
     * 登录结果回调
     */
    fun onLoginResult(userInfo: UserInfo)

}
package zs.xmx.user.view.activity

import zs.xmx.baselibrary.base.view.IBaseView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/28 22:53
 * @本类描述	  RegisterActivity的 IView接口
 * @内容说明
 *
 */
interface IRegisterView : IBaseView {
    /**
     * 注册结果回调
     */
    fun onRegisterResult(result: String)
}
package zs.xmx.home.fragment

import zs.xmx.baselibrary.base.view.IBaseView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  首页Fragment的 IView接口
 * @内容说明
 *
 */
interface IMainView : IBaseView {
    /**
     * 登录结果回调
     */
    fun onShowData(result: String)
}
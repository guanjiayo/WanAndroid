package zs.xmx.user.view.activity

import zs.xmx.baselibrary.base.view.IBaseView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  退出登录接口回调
 * @内容说明
 *
 */
interface ISettingView :IBaseView{
    /**
     * 退出登录结果回调
     */
    fun onLogoutResult()
}
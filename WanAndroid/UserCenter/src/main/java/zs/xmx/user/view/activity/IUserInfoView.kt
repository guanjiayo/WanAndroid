package zs.xmx.user.view.activity

import zs.xmx.baselibrary.base.view.IBaseView
import zs.xmx.user.model.domain.UserInfo

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/28 22:53
 * @本类描述	  UserInfoActivity的 IView接口
 * @内容说明
 *
 */
interface IUserInfoView : IBaseView {
    /*
        获取上传凭证回调(todo 这里先不做,有服务器再补充)
     */
    //fun onGetUploadTokenResult(result:String)

    /*
        编辑用户资料回调
     */
    fun onEditUserResult(result: UserInfo)
}
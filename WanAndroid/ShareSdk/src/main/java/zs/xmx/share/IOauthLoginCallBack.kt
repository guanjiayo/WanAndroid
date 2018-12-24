package zs.xmx.share

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  登录结果回调
 * @内容说明
 *
 */
interface IOauthLoginCallBack {

    fun onSuccess(respond: Any)

    fun onError(errorCode: Int, errorMsg: String)

}
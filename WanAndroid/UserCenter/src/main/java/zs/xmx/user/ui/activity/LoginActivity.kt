package zs.xmx.user.ui.activity

import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_login.*
import zs.xmx.baselibrary.base.activity.BaseMvpActivity
import zs.xmx.baselibrary.ext.enableClick
import zs.xmx.baselibrary.ext.onClick
import zs.xmx.baselibrary.utils.Logger
import zs.xmx.provider.PushProvider
import zs.xmx.provider.router.RouterPath
import zs.xmx.share.IShareCallBack
import zs.xmx.share.qq.QQRequest
import zs.xmx.share.wechat.WeChatRequest
import zs.xmx.user.R
import zs.xmx.user.injection.component.DaggerUserComponent
import zs.xmx.user.model.domain.UserInfo
import zs.xmx.user.presenter.LoginPresenter
import zs.xmx.user.utils.UserPrefsUtils
import zs.xmx.user.view.activity.ILoginView
import java.util.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  登录界面
 * @内容说明
 *
 */

//配置路由要跳转的目标Act (需要配置两级路径)
@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), ILoginView {

    @Autowired(name = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
    @JvmField
    var mPushProvider: PushProvider? = null

    override fun setContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun initComponentInject() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun initEvent() {

        btn_login.apply {
            enableClick(et_userName) { isBtnEnable() }
            enableClick(et_pwd) { isBtnEnable() }
        }

        btn_register.onClick(this)
        btn_login.onClick(this)
        btn_forget_pwd.onClick(this)
        btn_qq_login.onClick(this)
        btn_wechat_login.onClick(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {//普通登录
                val params = WeakHashMap<String, Any>()
                params["username"] = et_userName.text.toString()
                params["password"] = et_pwd.text.toString()
                mPresenter.login(params, mPushProvider?.getPushId() ?: "")

            }

            R.id.btn_wechat_login -> {//微信登录

                WeChatRequest.getInstance().login(this) { userInfo ->
                    //todo 测试结果回调
                    Logger.e(tag, userInfo.toString())
                }
            }

            R.id.btn_qq_login -> {//QQ登录

//                QQRequest.getInstance(this).login(object : IOauthLoginCallBack {
//
//                    override fun onError(errorCode: Int, errorMsg: String) {
//                        showToast(errorMsg + "$errorCode")
//                    }
//
//                    override fun onSuccess(respond: Any) {
//                        Logger.e("测试", "$respond")
//                        //返回的是JsonObject 用户信息
//                        val jsonObject = respond as JSONObject
//                        val figureurl = jsonObject.optString("figureurl")
//                        val nickname = jsonObject.optString("nickname")
//                        val gender = jsonObject.optString("gender")
//                        val msg = jsonObject.optString("msg")
//                    }
//
//                })

                QQRequest.getInstance(this).shareUrlToQQ(object : IShareCallBack {
                    override fun onSuccess(respond: Any) {
                        Logger.e("测试", "$respond")
                    }

                    override fun onError(errorCode: Int, errorMsg: String) {
                        showToast(errorMsg + "$errorCode")
                    }

                })

            }
            R.id.btn_register -> {//注册
                startActivity(RegisterActivity::class.java)
            }
            R.id.btn_forget_pwd -> {//忘记密码
                startActivity(ForgetPwdActivity::class.java)

            }
        }
    }


    override fun onLoginResult(userInfo: UserInfo) {
        showToast("登录成功")
        UserPrefsUtils.putUserInfo(userInfo)
        //由于是商城的逻辑,我们直接finish()掉页面即可
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        QQRequest.getInstance(this).onActivityResultData(requestCode, resultCode, data)
    }

    /**
     * 判断输入框数据不为空,然后"注册按钮"才为可用状态
     */
    private fun isBtnEnable(): Boolean {
        return et_userName.text.isNullOrEmpty().not() &&
                et_pwd.text.isNullOrEmpty().not()

    }


}

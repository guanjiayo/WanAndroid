package zs.xmx.user.ui.activity

import android.view.View
import kotlinx.android.synthetic.main.activity_register.*
import zs.xmx.baselibrary.base.activity.BaseMvpActivity
import zs.xmx.baselibrary.ext.enableClick
import zs.xmx.baselibrary.ext.onClick
import zs.xmx.user.R
import zs.xmx.user.injection.component.DaggerUserComponent
import zs.xmx.user.presenter.RegisterPresenter
import zs.xmx.user.view.activity.IRegisterView
import java.util.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/29 16:55
 * @本类描述	  注册页
 * @内容说明
 *
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), IRegisterView {


    override fun initComponentInject() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .build()
                .inject(this)
        mPresenter.mView = this
    }


    override fun setContentViewId(): Int {
        return R.layout.activity_register
    }


    override fun initEvent() {

        btn_register.apply {
            enableClick(et_userName) { isBtnEnable() }
            enableClick(et_pwd) { isBtnEnable() }
        }

        btn_register.onClick(this)
        mVerifyCodeBtn.onClick(this)

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_register -> {
                val params = WeakHashMap<String, Any>()
                params["username"] = et_userName.text.toString()
                params["password"] = et_pwd.text.toString()
                params["repassword"] = et_pwd.text.toString()
                mPresenter.register(params)
            }
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                showToast("发送验证码成功")
            }
        }
    }


    override fun onRegisterResult(result: String) {
        showToast(result)
        finishToActivity(LoginActivity::class.java)
    }

    /**
     * 判断输入框数据不为空,然后"注册按钮"才为可用状态
     */
    private fun isBtnEnable(): Boolean {
        return et_userName.text.isNullOrEmpty().not() &&
                et_pwd.text.isNullOrEmpty().not()

    }

}

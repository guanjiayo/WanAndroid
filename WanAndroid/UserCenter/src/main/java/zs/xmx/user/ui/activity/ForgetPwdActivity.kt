package zs.xmx.user.ui.activity

import android.view.View
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import zs.xmx.baselibrary.base.activity.BaseMvpActivity
import zs.xmx.baselibrary.ext.enableClick
import zs.xmx.baselibrary.ext.onClick
import zs.xmx.user.R
import zs.xmx.user.injection.component.DaggerUserComponent
import zs.xmx.user.presenter.ForgetPwdPresenter
import zs.xmx.user.view.activity.IForgetPwdView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/2 2:15
 * @本类描述	  忘记密码界面
 * @内容说明
 *
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), IForgetPwdView {

    override fun setContentViewId(): Int {
        return R.layout.activity_forget_pwd
    }

    override fun onForgetPwdResult(result: String) {

    }

    override fun initComponentInject() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_getVerifyCode -> {//获取验证码
                btn_getVerifyCode.requestSendVerifyNumber()
                showToast("发送验证码成功")
            }
            R.id.btn_next -> {//下一步
                //mPresenter.forgetPwd()
                startActivity(ResetPwdActivity::class.java)
            }
        }

    }

    override fun initEvent() {
        btn_next.apply {
            enableClick(et_phone) { isBtnEnable() }
            enableClick(et_verifyCode) { isBtnEnable() }
        }
        btn_getVerifyCode.onClick(this)
        btn_next.onClick(this)
    }

    /**
     * 判断输入框数据不为空,然后"按钮"才为可用状态
     */
    private fun isBtnEnable(): Boolean {
        return et_phone.text.isNullOrEmpty().not() &&
                et_verifyCode.text.isNullOrEmpty().not()

    }
}
package zs.xmx.user.ui.activity

import android.view.View
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import zs.xmx.baselibrary.base.activity.BaseMvpActivity
import zs.xmx.baselibrary.ext.enableClick
import zs.xmx.baselibrary.ext.onClick
import zs.xmx.user.R
import zs.xmx.user.injection.component.DaggerUserComponent
import zs.xmx.user.presenter.ResetPwdPresenter
import zs.xmx.user.view.activity.IResetPwdView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/2 2:59
 * @本类描述	  重置密码界面
 * @内容说明
 *
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), IResetPwdView {

    override fun initComponentInject() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun setContentViewId(): Int {
        return R.layout.activity_reset_pwd
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_confirm -> {
                if (et_pwd.text.toString() != et_confirmPwd.text.toString()) {
                    showToast("密码不一致")
                    return
                }
                // mPresenter.resetPwd()
                finishToActivity(LoginActivity::class.java)

            }
        }

    }

    override fun onResetPwdResult(result: String) {

    }

    override fun initEvent() {
        btn_confirm.apply {
            enableClick(et_pwd) { isBtnEnable() }
            enableClick(et_confirmPwd) { isBtnEnable() }
        }
        btn_confirm.onClick(this)
    }

    /**
     * 判断输入框数据不为空,然后"按钮"才为可用状态
     * todo 可以加个手机号码验证之类的
     */
    private fun isBtnEnable(): Boolean {
        return et_pwd.text.isNullOrEmpty().not() &&
                et_confirmPwd.text.isNullOrEmpty().not()

    }
}
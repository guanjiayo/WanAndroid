package zs.xmx.user.ui.activity

import android.view.View
import kotlinx.android.synthetic.main.activity_user_info.*
import zs.xmx.baselibrary.base.activity.BaseTakePhotoActivity
import zs.xmx.baselibrary.ext.onClick
import zs.xmx.user.R
import zs.xmx.user.injection.component.DaggerUserComponent
import zs.xmx.user.model.domain.UserInfo
import zs.xmx.user.presenter.UserInfoPresenter
import zs.xmx.user.view.activity.IUserInfoView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/2 17:08
 * @本类描述	  用户信息界面
 * @内容说明
 *
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), IUserInfoView {

    override fun initComponentInject() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun setContentViewId(): Int {
        return R.layout.activity_user_info
    }

    override fun initData() {

        //todo 初始化用户信息,展示
        //SPUtils.getParam()
    }

    override fun initEvent() {

        rl_avatar.onClick(this)

        mHeaderBar.getRightView().onClick {

        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.rl_avatar -> {
                showAlertView()
            }
        }
    }


    override fun onEditUserResult(result: UserInfo) {

    }
}
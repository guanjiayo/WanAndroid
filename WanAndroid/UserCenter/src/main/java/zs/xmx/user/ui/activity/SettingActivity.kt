package zs.xmx.user.ui.activity

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_setting.*
import zs.xmx.baselibrary.base.activity.BaseMvpActivity
import zs.xmx.baselibrary.constant.BaseConstant
import zs.xmx.baselibrary.ext.onClick
import zs.xmx.baselibrary.utils.SPUtils
import zs.xmx.baselibrary.utils.StatusBar
import zs.xmx.provider.router.RouterPath
import zs.xmx.user.R
import zs.xmx.user.injection.component.DaggerUserComponent
import zs.xmx.user.presenter.SettingPresenter
import zs.xmx.user.utils.UserPrefsUtils
import zs.xmx.user.view.activity.ISettingView

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  "设置" 页面
 * @内容说明
 *
 */
class SettingActivity : BaseMvpActivity<SettingPresenter>(), ISettingView {

    override fun initComponentInject() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun setContentViewId(): Int {
        return R.layout.activity_setting
    }

    override fun initView() {
        StatusBar.setTransparentStatusBar(this, mHeadBar)
    }

    override fun initEvent() {
        mUserProtocolTv.onClick(this)
        mFeedBackTv.onClick(this)
        mAboutTv.onClick(this)
        mLogoutBtn.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mUserProtocolTv -> {
                //todo 测试WebView封装框架
                startActivity(WebViewActivity::class.java)
            }
            R.id.mFeedBackTv -> {//反馈意见
                //todo 测试商品详情
                ARouter.getInstance().build(RouterPath.GoodsCenter.PATH_GOODS_DETAIL)
                        .navigation()
            }
            R.id.mAboutTv -> {//关于
                //todo 测试支付页面
                ARouter.getInstance().build(RouterPath.PaySDK.PATH_PAY)
                        //.withInt(ProviderConstant.KEY_ORDER_ID,mCurrentOrder!!.id)
                        //.withLong(ProviderConstant.KEY_ORDER_PRICE,mCurrentOrder!!.totalPrice)
                        .navigation()
            }
            R.id.mLogoutBtn -> {//退出登录
                mPresenter.logout()
            }
        }
    }

    override fun onLogoutResult() {
        showToast("退出登录")
        //todo 交互:弹窗,设置回默认值
        UserPrefsUtils.putUserInfo(null)
        SPUtils.remove(BaseConstant.KEY_SP_COOKIES)
    }


}
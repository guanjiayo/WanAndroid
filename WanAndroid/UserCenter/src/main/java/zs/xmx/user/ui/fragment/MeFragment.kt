package zs.xmx.user.ui.fragment

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.fragment_me.*
import zs.xmx.aop.login.annotation.CheckLogin
import zs.xmx.baselibrary.base.fragment.BaseFragment
import zs.xmx.baselibrary.constant.BaseConstant
import zs.xmx.baselibrary.ext.onClick
import zs.xmx.baselibrary.utils.SPUtils
import zs.xmx.baselibrary.utils.StatusBar
import zs.xmx.baselibrary.weight.dialog.ShareDialog
import zs.xmx.provider.constant.ProviderConstant
import zs.xmx.provider.router.RouterPath
import zs.xmx.user.R
import zs.xmx.user.ui.activity.SettingActivity
import zs.xmx.user.ui.activity.UserInfoActivity


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  MeFragment
 * @内容说明
 * @补充内容
 *
 *
 */
@Route(path = RouterPath.UserCenter.PATH_ME)
class MeFragment : BaseFragment() {

    override fun setLayout(): Any {
        return R.layout.fragment_me
    }

    override fun initView(rootView: View) {
        //沉浸式状态栏
        StatusBar.setTransparentStatusBar(mContext, ll_top)
    }

    override fun initLoad() {

    }

    override fun onResume() {
        super.onResume()
        //这里的数据显示不需要懒加载,这里在生命周期方法实现
        loadData()
    }

    private fun loadData() {
        if (isLogin()) {
            val userName = SPUtils.getParam(BaseConstant.KEY_SP_USERNAME, "").toString()
            tv_unLogin.text = userName
        } else {
            tv_unLogin.text = "登录/注册"
        }
    }

    /**
     * 判断Cookies是否存在来确定SP是否有用户数据
     */
    private fun isLogin(): Boolean {
        return SPUtils.getParam(BaseConstant.KEY_SP_COOKIES, "")
                .toString().isNotEmpty()
    }

    override fun initEvent() {
        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
        mAllOrderTv.onClick(this)
        mAddressTv.onClick(this)

        ll_top.onClick(this)
        mShareTv.onClick(this)
        mSettingTv.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mWaitPayOrderTv -> {//待付款
                toOrderStatus(ProviderConstant.ORDER_WAIT_PAY)
            }
            R.id.mWaitConfirmOrderTv -> {//待收货
                toOrderStatus(ProviderConstant.ORDER_WAIT_CONFIRM)
            }
            R.id.mCompleteOrderTv -> {//已完成
                toOrderStatus(ProviderConstant.ORDER_COMPLETED)
            }
            R.id.mAllOrderTv -> {//我的订单
                toOrderStatus(ProviderConstant.ORDER_ALL)
            }
            R.id.mAddressTv -> {//收货管理
                toShipAddress()
            }
            R.id.ll_top -> { //顶部布局
                toUserInfo()
            }
            R.id.mShareTv -> {//分享
                ShareDialog.newInstance()
                        .show(mContext.supportFragmentManager)
            }
            R.id.mSettingTv -> {//设置
                startActivity(SettingActivity::class.java)
            }

        }
    }

    /**
     * 跳转到"地址管理"页面
     */
    @CheckLogin
    private fun toShipAddress() {
        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_SHIP_ADDRESS)
                .navigation()
    }

    @CheckLogin
    private fun toUserInfo() { //已经登录的后续操作
        startActivity(UserInfoActivity::class.java)
    }


    /**
     * 跳转到"订单状态"页面
     * todo 改下用,默认不传参的写法
     */
    @CheckLogin
    private fun toOrderStatus(orderStatus: Int) {
        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_STATUS)
                .withInt(ProviderConstant.KEY_ORDER_STATUS, orderStatus)
                .navigation()
    }

}

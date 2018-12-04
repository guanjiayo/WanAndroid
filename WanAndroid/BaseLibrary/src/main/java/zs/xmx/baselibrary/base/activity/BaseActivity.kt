package zs.xmx.baselibrary.base.activity

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import zs.xmx.baselibrary.R
import zs.xmx.baselibrary.base.view.IBaseView
import zs.xmx.baselibrary.receiver.NetWorkReceiver
import zs.xmx.baselibrary.utils.ActivityUtils
import zs.xmx.baselibrary.utils.ToastUtils
import zs.xmx.baselibrary.weight.ProgressLoadingDialog


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2016/10/28 16:57
 * @本类描述	  基类Activity
 * @内容说明   1.单Activity继承BaseActivity(不含Dagger)
 *            2.单Activity+多Fragment用HomeActivity
 *
 * @一般要写   1.ToolBar
 *            2.颜色统一
 *            3.统一配置(屏幕,状态栏)
 *            4.Fragment管理
 *            5.Toast,dialog,progress,snakeBar写在这里,其他类调用更方便
 *            6.定义TAG,后面继承的Activity能直接使用
 *            7.第三方库(友盟统计使用,极光推送,百度地图...)
 *            8.生命周期的log/管理
 *            9.软键盘管理
 *            10.网络监听广播注册
 *            11.定义局部上下文(Activity Context)
 *
 *
 *            在该Activity的onCreate添加入栈,onDestroy出栈,这里使用 AppManager,可以不处理
 *
 */
abstract class BaseActivity : RxAppCompatActivity(), View.OnClickListener,
    NetWorkReceiver.onNetWorkStateChangedListener, IBaseView {

    //TAG 这里定义TAG,后面继承的Activity能直接使用
    open val tag: String = this@BaseActivity.javaClass.simpleName
    //上下文
    lateinit var mContext: Context

    //网络监听广播
    private lateinit var mMyReceiver: NetWorkReceiver
    //加载进度的ProgressDialog
    private lateinit var mProgressLoadingDialog: ProgressLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(setContentViewId())
        setBaseConfig()
        mContext = this@BaseActivity
        initData()
        initView()
        initEvent()
        registerReceiver()

    }

    /**
     * 代码注册广播
     */
    private fun registerReceiver() {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        mMyReceiver = NetWorkReceiver()
        mMyReceiver.setOnNetWorkStateChangedListener(this)//绑定广播
        this.registerReceiver(mMyReceiver, intentFilter)
    }


    /**
     * 设置一些基本属性
     */
    private fun setBaseConfig() {
        mProgressLoadingDialog = ProgressLoadingDialog.create()

        /**
         * 注册ARouter
         * <p>
         * 然后子类就可以拿到ARouter注解传递的参数
         */
        ARouter.getInstance().inject(this)

    }


    /**
     * 布局文件ID
     */
    abstract fun setContentViewId(): Int


    /**
     * 让子类覆盖此方法来完成页面的初始化
     *
     *
     * 初始化布局,界面方法
     */
    open fun initView() {

    }

    /**
     * 让子类覆盖此方法来完成数据的初始化
     */
    open fun initData() {}


    /**
     * 绑定要设置点击事件的组件,让子类覆盖此方法来完成事件的处理
     *
     *
     * 如果使用了View注解框架,可以不重写
     */
    open fun initEvent() {}


    /**
     * 让子类覆盖此方法来完成事件的处理
     */
    override fun onClick(v: View) {}

    //    protected void startActivity(Class<?> activity) {
    //        ActivityUtils.startActivity(activity);
    //    }
    //
    //    protected void startActivity(Bundle bundle, Class<?> activity) {
    //        ActivityUtils.startActivity(bundle, activity);
    //    }

    /**
     * 吐司,在基类写,方便后面子类调用
     *
     * @param msg
     */
    protected fun showToast(msg: String) {
        ToastUtils.showToast(this, msg)
    }

    open fun showLongToast(msg: String) {
        ToastUtils.showToast(this, msg, Toast.LENGTH_LONG)
    }

    override fun onPause() {
        super.onPause()
    }

    /**
     * 可以在这里做判断token的有效期是否过期
     * //todo 自己的方案,把token放在请求头,我们就不需要管了
     */
    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        // 注销广播
        unregisterReceiver(mMyReceiver)

    }


    fun startActivity(activity: Class<*>) {
        ActivityUtils.startActivity(activity)
    }

    fun startActivity(bundle: Bundle, activity: Class<*>) {
        ActivityUtils.startActivity(bundle, activity)
    }

    fun finishToActivity(activity: Class<*>) {
        ActivityUtils.finishToActivity(activity)
    }

    /**
     * 网络监听广播回调
     *
     * @param mNetWorkTypeName 网络状态
     */
    override fun onNetWorkStateChanged(mNetWorkTypeName: String) {
        //todo 拿到当前网络类型后续操作
    }


    override fun showLoading() {
        mProgressLoadingDialog.show(supportFragmentManager)
    }

    override fun hideLoading() {
        mProgressLoadingDialog.dismiss()
    }

    override fun onError(errorInfo: String) {
        showToast(errorInfo)
    }


}
package zs.xmx.baselibrary.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trello.rxlifecycle3.components.support.RxFragment
import zs.xmx.baselibrary.base.activity.BaseActivity
import zs.xmx.baselibrary.base.view.IBaseView
import zs.xmx.baselibrary.utils.ActivityUtils
import zs.xmx.baselibrary.utils.ToastUtils
import zs.xmx.baselibrary.weight.ProgressLoadingDialog

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	   Fragment 基类
 * @内容说明   1.Fragment管理交由依赖的Activity管理
 *            2.这个类,一般没有大概动,不会修改
 *           //TODO Fragment的增删改操作写一个工具类FragmentUtils操作
 *           //todo 包括栈问题
 * @补充内容
 *
 * ---------------------------------
 * @新增内容
 *
 */
abstract class RootFragment : RxFragment(), IBaseView, View.OnClickListener {

    /**
     * TAG 这里定义TAG,后面继承的类都能直接使用
     */
    open val TAG = this@RootFragment.javaClass.simpleName

    /**
     * 上下文
     */
    lateinit var mContext: BaseActivity

    //加载进度的ProgressDialog
    private lateinit var mProgressLoadingDialog: ProgressLoadingDialog

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        //获取上下文
        mContext = context as BaseActivity
    }

    /**
     * Fragment中,用onCreateView()拿到布局xml
     * <p>
     * ButterKnife.bind(this, rootView);
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return when {
            setLayoutID() is Int -> inflater.inflate(setLayoutID() as Int, container, false)
            setLayoutID() is View -> setLayoutID() as View
            else -> throw ClassCastException("type of setLayout() must be int or View!")
        }
    }

    /**
     * Fragment 布局文件ID
     *
     * @return
     */
    protected abstract fun setLayoutID(): Any

    /**
     * Fragment中,onViewCreated()初始化View对象
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }


    /**
     * 初始化View对象
     *
     *
     * 如果在onCreateView()实现了ButterKnife.bind(),可不重写该方法
     *
     * @param rootView onViewCreated()传过来的rootView,用于rootView.findViewById()
     */
    open fun initView(rootView: View) {}

    /**
     * 首次加载页面被调用
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // 只要Activity 没有销毁   该方法不会重复调用
        setBaseConfig()
        initData()
        initEvent()
        super.onActivityCreated(savedInstanceState)
    }

    /**
     * 设置一些基本属性
     */
    private fun setBaseConfig() {
        mProgressLoadingDialog = ProgressLoadingDialog.create()
    }

    /**
     * 子类覆盖此方法来完成数据的初始化
     */
    open fun initData() {}

    /**
     * 子类覆盖此方法来完成事件的设置
     */
    open fun initEvent() {}

    open fun showToast(msg: String) {
        ToastUtils.showToast(mContext, msg)
    }

    open fun showLongToast(msg: String) {
        ToastUtils.showToast(mContext, msg, Toast.LENGTH_LONG)
    }

    fun startActivity(activity: Class<*>) {
        ActivityUtils.startActivity(activity)
    }

    fun startActivity(bundle: Bundle, activity: Class<*>) {
        ActivityUtils.startActivity(bundle, activity)
    }

    override fun onClick(v: View) {}

    override fun showLoading() {
        mProgressLoadingDialog.show(mContext.supportFragmentManager)
    }

    override fun hideLoading() {
        mProgressLoadingDialog.dismiss()
    }

    override fun onError(errorInfo: String) {
        showToast(errorInfo)
    }

}

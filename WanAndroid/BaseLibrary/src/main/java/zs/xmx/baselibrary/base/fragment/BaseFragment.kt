package zs.xmx.baselibrary.base.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  BaseFragment(带懒加载)
 * @内容说明   Fragment预加载问题的解决方案：
 *            1.可以懒加载的Fragment(Fragment (show/hide) / Fragment + ViewPager)
 *            2.首次打开app,点击才加载页面,第二次点击不加载页面
 *            3.重写 isNeedReload() 方法可以重新设置是否需要加载数据状态
 *            4.把Fragment嵌套Fragment也考虑进去了
 *
 * todo 测试单Fragment show()/hidden()
 */
abstract class BaseFragment : RootFragment() {

    private var isViewCreated = false //页面视图是否创建完
    private var isVisibleToUser = false //页面视图是否已经对用户可见
    private var isDataLoad = false //数据是否已请求, isNeedReload()返回false的时起作用
    private var isFragmentHidden: Boolean = true // 记录当前fragment的是否隐藏
    private val userHintState = 1
    private val hiddenState = 2

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewCreated = true
        /**初始化的时候去加载数据 */
        isCanLoadData(userHintState)
    }


    /**
     * 视图是否已经对用户可见，系统的方法
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        isCanLoadData(userHintState)
    }

    /**
     * Fragment使用show()/hide()方法会被调用
     *
     * @param hidden true隐藏 : false显示
     *
     * Fragment生命周期跟随Activity
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        this.isFragmentHidden = hidden
        if (!hidden) {//Fragment.show() 加载数据
            isCanLoadData(hiddenState)
        }
    }


    /**
     * fragment再次可见时，是否重新请求数据，默认为false则只请求一次数据
     * <p>
     * 重写方法,可以实现是否需要ReadLoad
     */
    protected open fun isNeedReload(): Boolean = false

    /**
     * ViewPager + Fragment的场景,请求数据:
     */
    private fun isCanLoadData(state: Int) {
        when (state) {
            userHintState -> {
                //页面已经创建并且对用户可见和首次加载,且属于BaseFragment
                if (isViewCreated && isVisibleToUser
                        && isParentVisible() && (isNeedReload() || !isDataLoad)) {
                    initLoad()
                    isDataLoad = true
                    dispatchParentVisibleState()
                }
            }

            hiddenState -> {
                if (!isParentHidden() && (isNeedReload() || !isDataLoad)) {
                    initLoad()
                    isDataLoad = true
                    dispatchParentHiddenState()

                }
            }
        }

    }

    /**
     * show()、hide()场景下，当前fragment没隐藏，如果其子fragment也没隐藏，则尝试让子fragment请求数据
     */
    private fun dispatchParentHiddenState() {
        val fragmentManager: FragmentManager = childFragmentManager
        val fragments: List<Fragment> = fragmentManager.fragments
        if (fragments.isEmpty()) {
            return
        }
        for (child in fragments) {
            if (child is BaseFragment && !child.isHidden) {
                child.isCanLoadData(hiddenState)
            }
        }
    }


    /**
     * ViewPager场景下，判断父fragment是否可见
     */
    private fun isParentVisible(): Boolean {
        val fragment: Fragment? = parentFragment
        return fragment == null || (fragment is BaseFragment && fragment.isVisibleToUser)
    }

    /**
     * ViewPager场景下，当前fragment可见，
     * 如果其子fragment也可见，则尝试让子fragment加载请求
     */
    private fun dispatchParentVisibleState() {
        val fragmentManager: FragmentManager = childFragmentManager
        val fragments: List<Fragment> = fragmentManager.fragments
        if (fragments.isEmpty()) {
            return
        }
        for (child in fragments) {
            if (child is BaseFragment && child.isVisibleToUser) {
                child.isCanLoadData(userHintState)
            }
        }
    }

    /**
     * show()、hide()场景下，父fragment是否隐藏
     */
    private fun isParentHidden(): Boolean {
        val fragment: Fragment? = parentFragment
        if (fragment == null) {
            return false
        } else if (fragment is BaseFragment && !fragment.isHidden) {
            return false
        }
        return true
    }

    /**
     * 视图销毁的时候讲Fragment是否初始化的状态变为false
     */
    override fun onDestroyView() {
        isViewCreated = false
        isVisibleToUser = false
        isDataLoad = false
        super.onDestroyView()
    }


    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    abstract fun initLoad()

}

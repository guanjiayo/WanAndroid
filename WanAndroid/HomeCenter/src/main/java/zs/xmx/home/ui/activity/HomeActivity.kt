package zs.xmx.home.ui.activity

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.launcher.ARouter
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import kotlinx.android.synthetic.main.activity_home.*
import zs.xmx.baselibrary.base.activity.BaseActivity
import zs.xmx.baselibrary.base.fragment.BaseFragment
import zs.xmx.baselibrary.utils.AppManager
import zs.xmx.home.R
import zs.xmx.home.ui.adapter.HomeVPAdapter
import zs.xmx.home.ui.fragment.HomeFragment
import zs.xmx.provider.constant.ProviderConstant
import zs.xmx.provider.event.MessageBadgeEvent
import zs.xmx.provider.router.RouterPath

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  单Activity+多Fragment整合类
 * @内容说明   1.ViewPager+Fragment / Fragment 切换
 *
 * TODO 待研究,参考一下慕课课程
 * todo 如果这个类整合成功,把Lazy库直接放到这个库里面
 * todo 思路:写一个接口,把Fragment回调进来,数组存储,切换实现方式,具体的一些跳转啥的操作,用工具类实现
 *
 * todo 拓展一下ARoute,写一个工具类,方便我们跳转Activity,Fragment等
 */
class HomeActivity : BaseActivity() {

    //点击时间
    private var mPreClickTime: Long = 0

    private var fragmentManager: FragmentManager? = null

    private val titleId = intArrayOf(R.string.HomeFragment, R.string.TwoFragment,
            R.string.ThreeFragment, R.string.FourFragment, R.string.FiveFragment)
    private val fragments = arrayListOf<Fragment>()

    //主界面Fragment
    private val mMainFragment by lazy { HomeFragment() }
    //商品分类Fragment
    private val mCategoryFragment by lazy { ARouter.getInstance().build(RouterPath.GoodsCenter.PATH_CATEGORY).navigation() }
    //商品列表Fragment
    private val mGoodsListFragment by lazy { ARouter.getInstance().build(RouterPath.GoodsCenter.PATH_GOODS_LIST).navigation() }
    //购物车Fragment
    private val mCartFragment by lazy { ARouter.getInstance().build(RouterPath.GoodsCenter.PATH_CART).navigation() }
    //"我的"Fragment
    private val mMeFragment by lazy { ARouter.getInstance().build(RouterPath.UserCenter.PATH_ME).navigation() }

    override fun setContentViewId(): Int {
        return R.layout.activity_home
    }

    override fun initView() {
        //initFragment()
        initViewPager()
        initBottomNavigationBar()
    }

    /**
     * BottomNavigationBar配置
     */
    private fun initBottomNavigationBar() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {

            override fun onTabSelected(position: Int) {
                //未选中->选中
                viewPager.currentItem = position

            }

            override fun onTabUnselected(position: Int) {
                //选中->未选中
            }

            override fun onTabReselected(position: Int) {
                //选中->选中

            }

        })

        //todo 购物车数字还没做
        //购物车Tag
        mBottomNavBar.setCartBadgeCount(10)
        mBottomNavBar.checkFindBadge(false)
    }


    @Subscribe(tags = [Tag(ProviderConstant.KEY_MESSAGE_TAG)])
    fun initMessageBadgeItem(event: MessageBadgeEvent) {
        //发现Tag默认不显示Badge
        mBottomNavBar.checkFindBadge(event.isVisible)
    }

    private fun initViewPager() {
        fragments.add(mMainFragment)
        fragments.add(mCategoryFragment as BaseFragment)
        fragments.add(mGoodsListFragment as BaseFragment)
        fragments.add(mCartFragment as BaseFragment)
        fragments.add(mMeFragment as BaseFragment)

        viewPager.adapter = HomeVPAdapter(supportFragmentManager, fragments)
        /**
         *  让ViewPager直接缓存所有页面,
         *  ViewPager就不存在预加载的情况了
         */
        viewPager.offscreenPageLimit = titleId.size
    }


    override fun initData() {
        //注册RxBus
        RxBus.get().register(this)
    }


    /**
     * 1.清空数组里所有的Fragment,防止Fragment重叠问题
     * 2.再设置初始化时显示的Fragment
     * 注一: 需要预加载的话可以直接add()全部,show()或hide()用来显示或隐藏
     * 注二: 根据点击状态几个RadioButton的index,add对应的Fragment
     *
     *
     * 然后在子Fragment里使用fragment.isAdded()方法判断Fragment是否已经添加到Activity
     * //todo 看抽到哪个位置可一起判断
     * true:显示Fragment
     * false:添加Fragment
     *
     *
     * 使用hide()或show()方法时会触发onHiddenChanged(),可用作刷新数据
     *
     * @Override public void onHiddenChanged(boolean hidden) {
     *    super.onHiddenChanged(hidden);
     *    if (hidden){
     *       //Fragment隐藏时调用
     *    }else {
     *        //Fragment显示时调用
     *    }
     * }
     */
    //todo 直接改造成一次性全部加载,在FragmentFactory判断isAdded()
    //todo 这里是不结合ViewPager的写法
    private fun initFragment() {
        //清空数组里所有的Fragment,防止Fragment重叠问题
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        for (i in titleId.indices) { //遍历所有Fragment
            val fragment = fragmentManager.findFragmentByTag("$i")
            if (fragment != null) { //有fragment才清空
                fragmentTransaction.remove(fragment)
            }

            // 设置初始化时显示的Fragment
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.add(R.id.fl_main, MainFragment(), i.toString())
//            transaction.add(R.id.fl_main, TwoFragment(), i.toString())
//            transaction.commit()

        }

    }


    /**
     * DrawerLayout 开关事件
     */
    override fun initEvent() {


    }

    override fun onClick(v: View) {

    }

    /**
     * 后面用来处理Fragment事件的
     *
     * @param fragmentTag
     * @return
     */
    private fun getFragment(fragmentTag: String): BaseFragment {
        return fragmentManager!!.findFragmentByTag(fragmentTag) as BaseFragment
    }

    /**
     * 移除fragment
     * popBackStack() 回退一个Fragment,当等于1时,finish()
     */
    fun removeFragment() {
        if (fragmentManager!!.backStackEntryCount > 1) {
            fragmentManager!!.popBackStack()
        } else {
            finish()
        }

    }

    /**
     * 移除Fragment(保留左侧Fragment)
     *
     *
     * 原来的方法是回退一个栈,但是这种方法会让LeftFragment 也移除掉
     *
     *
     * 解决:
     * 1.新建一个事务创建左侧的Fragment
     * 2.保留两个Fragment
     *
     * @param leftFragmentTag 左侧FragmentTag
     */
    fun removeFragment(leftFragmentTag: String) {
        //当大于2个Fragment且这两个Fragment中包含LeftFragment时,才回退一个Fragment
        if (fragmentManager!!.backStackEntryCount > 2 && fragmentManager!!.fragments.contains(getFragment(leftFragmentTag))) {
            fragmentManager!!.popBackStack()
        } else {
            finish()
        }

    }

    /**
     *
     * 处于主页面,点击两次才能退出程序
     */
    override fun onBackPressed() {
        if (System.currentTimeMillis() - mPreClickTime > 2000) {// 两次点击的间隔大于2s中
            showToast("再按一次,退出APP")
            mPreClickTime = System.currentTimeMillis()
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //反注册RxBus
        RxBus.get().unregister(this)
    }

}

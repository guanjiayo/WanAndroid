package zs.xmx.home.ui.fragment

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_main.*
import zs.xmx.baselibrary.base.fragment.BaseFragment
import zs.xmx.baselibrary.base.global.ProjectInit.getApplicationContext
import zs.xmx.baselibrary.ext.onClick
import zs.xmx.baselibrary.utils.StatusBar
import zs.xmx.home.R
import zs.xmx.home.ui.adapter.vlayout.BannerVLayoutAdapter
import zs.xmx.home.ui.adapter.vlayout.BaseVLayoutAdapter
import zs.xmx.home.ui.adapter.vlayout.MenuVLayoutAdapter
import zs.xmx.home.ui.adapter.vlayout.NewsVLayoutAdapter
import zs.xmx.provider.router.RouterPath
import java.util.*


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  商城首页
 * @内容说明
 * @补充内容
 *
 * ---------------------------------
 * @新增内容
 *
 */
class HomeFragment : BaseFragment() {


    //不同item必须不同的viewtype
    internal var BANNER_VIEW_TYPE = 1
    internal var MENU_VIEW_TYPE = 2
    internal var NEWS_VIEW_TYPE = 3
    internal var TITLE_VIEW_TYPE = 4
    internal var GRID_VIEW_TYPE = 5
    //广告位
    internal var ITEM_URL = intArrayOf(R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5)
    //    高颜值商品位
    internal var GRID_URL = intArrayOf(R.mipmap.flashsale1, R.mipmap.flashsale2, R.mipmap.flashsale3, R.mipmap.flashsale4)
    // private var mAdapters :MutableCollection<DelegateAdapter.Adapter<*>> //存放各个模块的适配器
    private var mAdapters: LinkedList<DelegateAdapter.Adapter<*>>? = null

//    override fun initComponentInject() {
//        DaggerMarketComponent.builder()
//                .activityComponent(mActivityComponent)
//                .build()
//                .inject(this)
//        mPresenter.mView = this
//    }

    override fun setLayoutID(): Any {
        return R.layout.fragment_main
    }

    override fun initData() {
        //  RxBus.get().register(this)
    }

    override fun initView(rootView: View) {
        StatusBar.setTransparentStatusBar(mContext, mHeadBar)

        mAdapters = LinkedList()

        //初始化VLayoutManager
        val layoutManager = VirtualLayoutManager(activity!!)
        recycleView.layoutManager = layoutManager

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        val viewPool = RecyclerView.RecycledViewPool()
        recycleView.setRecycledViewPool(viewPool)
        viewPool.setMaxRecycledViews(0, 10)

        //DelegateAdapter 可以让一个recycleView setAdapter多个适配器
        val delegateAdapter = DelegateAdapter(layoutManager, true)
        recycleView.adapter = delegateAdapter

        //banner
        val bannerAdapter = BannerVLayoutAdapter(activity, LinearLayoutHelper(),
                R.layout.vlayout_banner, 1, BANNER_VIEW_TYPE)


        //menu
        // 在构造函数设置每行的网格个数
        val gridLayoutHelper = GridLayoutHelper(5)
        gridLayoutHelper.setPadding(0, 16, 0, 16)
        gridLayoutHelper.setVGap(16)// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(0)// 控制子元素之间的水平间距
        gridLayoutHelper.bgColor = Color.WHITE

        val menuAdapter = MenuVLayoutAdapter(activity, gridLayoutHelper, R.layout.vlayout_menu, 10, MENU_VIEW_TYPE)


        //news
        val newsAdapter = NewsVLayoutAdapter(activity, LinearLayoutHelper(), R.layout.vlayout_news, 1, NEWS_VIEW_TYPE)

        mAdapters!!.apply {
            add(bannerAdapter)
            add(menuAdapter)
            add(newsAdapter)
        }

        //todo 这里目前是本地显示的图片,后面要全部改成网络获取的
        //这里我就循环item 实际项目中不同的ITEM 继续往下走就行
        for (i in ITEM_URL.indices) {

            //item1 title
            val titleAdapter = object : BaseVLayoutAdapter(activity, LinearLayoutHelper(), R.layout.vlayout_title, 1, TITLE_VIEW_TYPE) {
                override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                    super.onBindViewHolder(holder, position)
                    holder.setImageResource(R.id.iv, ITEM_URL[i])
                }
            }
            mAdapters!!.add(titleAdapter)
            //item1 gird
            val gridLayoutHelper1 = GridLayoutHelper(2)
            gridLayoutHelper.setMargin(0, 0, 0, 0)
            gridLayoutHelper.setPadding(0, 0, 0, 0)
            gridLayoutHelper.setVGap(0)// 控制子元素之间的垂直间距
            gridLayoutHelper.setHGap(0)// 控制子元素之间的水平间距
            gridLayoutHelper.bgColor = Color.WHITE
            gridLayoutHelper.setAutoExpand(true)//是否自动填充空白区域
            val girdAdapter = object : BaseVLayoutAdapter(activity, gridLayoutHelper1, R.layout.vlayout_grid, 4, GRID_VIEW_TYPE) {
                override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                    super.onBindViewHolder(holder, position)
                    val item = GRID_URL[position]
                    val iv = holder.getView(R.id.iv) as ImageView
                    Glide.with(getApplicationContext()).load(item).into(iv)

                    iv.setOnClickListener {
                        Toast.makeText(getApplicationContext(), "item$position", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            mAdapters!!.add(girdAdapter)
        }


        delegateAdapter.setAdapters(mAdapters)

    }

    override fun initLoad() {
        val message = "已经初始并已经显示给用户可以加载数据(首次) }"
        Log.d(TAG, message)
        //   mPresenter.showData("mainFrag", params)
    }

//    override fun onShowData(result: String) {
//        //todo 拿到数据后的后续操作
//    }

    override fun initEvent() {
        mMsgIv.onClick(this)
        mScanIv.onClick(this)
        mSearchEt.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mMsgIv -> {//消息中心
                ARouter.getInstance().build(RouterPath.MessageCenter.PATH_MESSAGE).navigation()
            }
            R.id.mScanIv -> {//扫一扫
                showToast("扫一扫")
            }
            R.id.mSearchEt -> {//搜索
                //mContext.startActivity(SearchGoodsActivity::class.java)
                ARouter.getInstance().build(RouterPath.GoodsCenter.PATH_SEARCH).navigation()
            }

        }
    }


}

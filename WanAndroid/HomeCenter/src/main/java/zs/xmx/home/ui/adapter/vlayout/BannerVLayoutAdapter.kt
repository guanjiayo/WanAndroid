package zs.xmx.home.ui.adapter.vlayout

import android.app.Activity
import android.content.Context
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import zs.xmx.baselibrary.utils.ToastUtils
import zs.xmx.home.R
import zs.xmx.home.utils.TempGlideImageLoader
import java.util.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/4 16:01
 * @本类描述	  Banner 适配器
 * @内容说明
 *
 */
class BannerVLayoutAdapter(context: Context?, layoutHelper: LayoutHelper?, layoutId: Int, count: Int, viewTypeItem: Int)
    : BaseVLayoutAdapter(context, layoutHelper, layoutId, count, viewTypeItem) {


    private val mContext = context


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        //todo 这里是临时数据,后面改成网络数据
        val arrayList = ArrayList<String>()
        arrayList.add("http://bpic.wotucdn.com/11/66/23/55bOOOPIC3c_1024.jpg!/fw/780/quality/90/unsharp/true/compress/true/watermark/url/L2xvZ28ud2F0ZXIudjIucG5n/repeat/true")
        arrayList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505470629546&di=194a9a92bfcb7754c5e4d19ff1515355&imgtype=0&src=http%3A%2F%2Fpics.jiancai.com%2Fimgextra%2Fimg01%2F656928666%2Fi1%2FT2_IffXdxaXXXXXXXX_%2521%2521656928666.jpg")
        // 绑定数据
        val mBanner = holder.getView(R.id.banner) as Banner
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        //设置图片加载器
        mBanner.setImageLoader(TempGlideImageLoader())
        //设置图片集合
        mBanner.setImages(arrayList)
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage)
        //设置标题集合（当banner样式有显示title时）
        //        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true)
        //设置轮播时间
        mBanner.setDelayTime(5000)
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER)
        //banner设置方法全部调用完毕时最后调用
        mBanner.start()

        mBanner.setOnBannerListener {
            ToastUtils.showToast(mContext as Activity, "banner点击了${arrayList[position]}")
        }
    }
}
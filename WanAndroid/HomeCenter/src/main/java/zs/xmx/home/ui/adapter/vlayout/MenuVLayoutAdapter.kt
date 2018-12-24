package zs.xmx.home.ui.adapter.vlayout

import android.app.Activity
import android.content.Context
import android.widget.LinearLayout
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import zs.xmx.baselibrary.utils.ToastUtils
import zs.xmx.home.R

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/4 16:01
 * @本类描述	  菜单 VLayout 适配器
 * @内容说明   
 *
 */
class MenuVLayoutAdapter(context: Context?, layoutHelper: LayoutHelper?, layoutId: Int, count: Int, viewTypeItem: Int)
    : BaseVLayoutAdapter(context, layoutHelper, layoutId, count, viewTypeItem) {

    private val mContext = context
    //todo 这里是临时数据,后面改成网络数据
    //    应用位
    private var ITEM_NAMES = arrayOf("天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "拍卖", "分类")
    private var IMG_URLS = intArrayOf(R.mipmap.ic_tian_mao, R.mipmap.ic_ju_hua_suan, R.mipmap.ic_tian_mao_guoji, R.mipmap.ic_waimai, R.mipmap.ic_chaoshi, R.mipmap.ic_voucher_center, R.mipmap.ic_travel, R.mipmap.ic_tao_gold, R.mipmap.ic_auction, R.mipmap.ic_classify)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setText(R.id.tv_menu_title_home, ITEM_NAMES[position] + "")
        holder.setImageResource(R.id.iv_menu_home, IMG_URLS[position])
        (holder.getView(R.id.ll_menu_home) as LinearLayout).setOnClickListener {
            ToastUtils.showToast(mContext as Activity, ITEM_NAMES[position])
        }
    }
}
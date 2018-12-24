package zs.xmx.home.ui.adapter.vlayout

import android.content.Context
import android.widget.Toast
import com.alibaba.android.vlayout.LayoutHelper
import com.chad.library.adapter.base.BaseViewHolder
import com.sunfusheng.marqueeview.MarqueeView
import zs.xmx.baselibrary.base.global.ProjectInit
import zs.xmx.home.R
import java.util.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/4 16:01
 * @本类描述	  新闻 VLayout适配器
 * @内容说明   
 *
 */
class NewsVLayoutAdapter(context: Context?, layoutHelper: LayoutHelper?, layoutId: Int, count: Int, viewTypeItem: Int)
    : BaseVLayoutAdapter(context, layoutHelper, layoutId, count, viewTypeItem) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        //todo 这里是临时数据,后面改成网络数据
        val marqueeView1 = holder.getView(R.id.marqueeView1) as MarqueeView
        val marqueeView2 = holder.getView(R.id.marqueeView2) as MarqueeView

        val info1 = ArrayList<String>()
        info1.add("天猫超市最近发大活动啦，快来抢")
        info1.add("没有最便宜，只有更便宜！")

        val info2 = ArrayList<String>()
        info2.add("这个是用来搞笑的，不要在意这写小细节！")
        info2.add("啦啦啦啦，我就是来搞笑的！")

        marqueeView1.startWithList(info1)
        marqueeView2.startWithList(info2)
        // 在代码里设置自己的动画
        marqueeView1.startWithList(info1, R.anim.anim_bottom_in, R.anim.anim_top_out)
        marqueeView2.startWithList(info2, R.anim.anim_bottom_in, R.anim.anim_top_out)

        marqueeView1.setOnItemClickListener { position, textView ->
            Toast.makeText(ProjectInit.getApplicationContext(), textView.text.toString(), Toast.LENGTH_SHORT).show()
        }
        marqueeView2.setOnItemClickListener { position, textView ->
            Toast.makeText(ProjectInit.getApplicationContext(), textView.text.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
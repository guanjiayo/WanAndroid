package zs.xmx.baselibrary.weight

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import zs.xmx.baselibrary.R
import zs.xmx.baselibrary.base.dialog.base.BaseDialog
import zs.xmx.baselibrary.base.dialog.base.BaseDialogVH

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/24 0:46
 * @本类描述	  加载进度的Dialog
 * @内容说明
 *
 */

//改成私有构造,不让其他类以构造方式创建
class ProgressLoadingDialog : BaseDialog() {
    private lateinit var loadingView: ImageView
    private lateinit var animDrawable: AnimationDrawable

    override fun setLayout(): Int {
        return R.layout.dialog_progress
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.LightProgressDialog)
    }

    override fun convertView(holder: BaseDialogVH, dialog: BaseDialog) {
        //和获取动画视图
        loadingView = holder.getView<ImageView>(R.id.iv_loading)
        animDrawable = loadingView.background as AnimationDrawable
        animDrawable.start()
    }

    companion object {

        fun create(): ProgressLoadingDialog {
            val dialog = ProgressLoadingDialog()
            dialog.setSize(120, 120)
            dialog.setDimAmount(0.5f)//背景暗度
            dialog.setGravity(Gravity.CENTER)//显示位置
            dialog.setAnimStyle(R.style.ConfirmDialogAnim)//动画样式
            return dialog
        }


    }


    /**
     * 隐藏加载对话框，动画停止
     */
    override fun dismiss() {
        super.dismiss()
        animDrawable.stop()
    }
}
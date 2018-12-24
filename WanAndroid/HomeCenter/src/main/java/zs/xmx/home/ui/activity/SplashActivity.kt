package zs.xmx.home.ui.activity

import android.Manifest
import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*
import zs.xmx.aop.permission.annotation.NeedPermission
import zs.xmx.aop.permission.annotation.PermissionCanceled
import zs.xmx.aop.permission.annotation.PermissionDenied
import zs.xmx.aop.permission.bean.DenyBean
import zs.xmx.aop.permission.utils.SettingUtils
import zs.xmx.baselibrary.base.activity.BaseActivity
import zs.xmx.home.R


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @本类描述	  启动页
 * @内容说明
 *
 */
class SplashActivity : BaseActivity() {

    override fun setContentViewId(): Int {
        return R.layout.activity_splash
    }


    override fun initData() {
        permissionGranted()
    }

    override fun initEvent() {
        iv_lottieAnim.setAnimation("hello-world.json")
        //设置动画轮播次数,-1为无限
        iv_lottieAnim.repeatCount = -1
        //设置动画播放进度,要float型
        iv_lottieAnim.progress = 0.5f

        //播放Lottie动画
        iv_lottieAnim.playAnimation()

    }

    override fun onClick(v: View) {

    }

    @NeedPermission(value = [
        Manifest.permission.CAMERA,
        Manifest.permission.CALL_PHONE])
    fun permissionGranted() {
        //todo 换一种倒计时方式
        Thread(Runnable {
            try {
                Thread.sleep(2000)
                startActivity(HomeActivity::class.java)
                finish()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }).start()
    }

    /**
     * 权限被取消
     */
    @PermissionCanceled
    fun dealCancelPermission() {
        showToast("权限被取消")
    }

    /**
     * 权限被拒绝
     */
    @PermissionDenied
    fun dealPermission(bean: DenyBean) {
        val denyList = bean.denyList
        val sb = StringBuilder()
        for (s in denyList) {
            when (s) {
                Manifest.permission.CALL_PHONE -> sb.append("电话,")
                Manifest.permission.CAMERA -> sb.append("相机,")
            }
        }
        Toast.makeText(this, sb.toString() + "权限被拒绝:", Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage(sb.toString() + "权限被禁止，需要手动打开")
                .setPositiveButton("去设置") { dialog, _ ->
                    dialog.dismiss()
                    SettingUtils.go2Setting(this@SplashActivity)
                }
                .setNegativeButton("取消") { dialog, _ -> dialog.dismiss() }
                .create().show()
    }

}
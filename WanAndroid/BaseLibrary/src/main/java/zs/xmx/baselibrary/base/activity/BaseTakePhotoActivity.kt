package zs.xmx.baselibrary.base.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import zs.xmx.baselibrary.base.BaseApplication
import zs.xmx.baselibrary.injection.component.ActivityComponent
import zs.xmx.baselibrary.injection.component.DaggerActivityComponent
import zs.xmx.baselibrary.injection.module.ActivityModule
import zs.xmx.baselibrary.injection.module.LifecycleProviderModule
import zs.xmx.baselibrary.presenter.BasePresenter
import java.io.File
import javax.inject.Inject

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/2 17:03
 * @本类描述	  存在打开相册图片界面的BaseActivity
 * @内容说明   泛型T这样使用,就能拿到其他子类传过来的XXPresenter
 *
 */
abstract class BaseTakePhotoActivity<T : BasePresenter<*>> : BaseActivity(), TakePhoto.TakeResultListener {

    //presenter层的引用,用于后面子类的直接调用
    //Dagger 的一种注入方式
    //这里 @Inject 变量名
    //BasePresenter的子类: class XXPresenter @Inject constructor() : BasePresenter<IXXView>() 相当于module的provide声明
    // 然后 Component 声明要@Inject的类
    //最后就相当于 XXActivity里面实现了 mPresenter=XXPresenter()
    //注入方式跟随Activity生命周期,不然需要在onDestroy()将presenter=null
    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent

    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        initComponentInject()

        //todo 第三方打开图片库 后面改选择图片界面的UI
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
    }

    /**
     * 构建ActivityComponent和activityModule联系
     * <p>
     * 其中ActivityComponent依赖AppComponent
     */
    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent
                .builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }


    /**
     * 让子类实现Dagger2的绑定,方便子类inject其他组件
     *
     * DaggerMarketComponent.builder()
    .activityComponent(mActivityComponent)
    // .marketModule(MarketModule()) 过时
    .build()
    .inject(this)
     */
    protected abstract fun initComponentInject()


    /*
      弹出选择框，默认实现
      可根据实际情况，自行修改
      todo 改成自己的
   */
    protected fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, OnItemClickListener { o, position ->
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            when (position) {
                0 -> {
                    createTempFile()
                    mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                }
                1 -> mTakePhoto.onPickFromGallery()
            }
        }

        ).show()

    }

    /*
        获取图片，成功回调
     */
    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result?.image?.originalPath)
        Log.d("TakePhoto", result?.image?.compressPath)
    }

    /*
        获取图片，取消回调
     */
    override fun takeCancel() {
    }

    /*
        获取图片，失败回调
     */
    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("takePhoto", msg)
    }

    /*
        TakePhoto默认实现
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    /*
        新建临时文件
     */
    fun createTempFile() {
        val tempFileName = "${SystemClock.currentThreadTimeMillis()}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }

        this.mTempFile = File(filesDir, tempFileName)
    }

}
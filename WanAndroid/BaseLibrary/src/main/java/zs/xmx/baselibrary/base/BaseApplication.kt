package zs.xmx.baselibrary.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.Logger
import okhttp3.logging.HttpLoggingInterceptor
import zs.xmx.baselibrary.base.global.ProjectInit
import zs.xmx.baselibrary.constant.BaseConstant
import zs.xmx.baselibrary.injection.component.AppComponent
import zs.xmx.baselibrary.injection.component.DaggerAppComponent
import zs.xmx.baselibrary.injection.module.AppModule
import zs.xmx.baselibrary.net.HttpLoggerInterceptor
import zs.xmx.baselibrary.utils.AppManager


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/22 19:49
 * @本类描述	  Application基类
 * @内容说明
 *
 */
open class BaseApplication : Application() {

    open lateinit var appComponent: AppComponent
    //todo 研究下这个属性怎么放在build.gradle配置
    private val isDebug = true

    override fun onCreate() {
        super.onCreate()
        //基础配置 todo 后面traceView测试启动时间
        ProjectInit.init(this)
            .withNativeApiHost(BaseConstant.BASE_URL)//配置主机名
            .withInterceptor(initLogInterceptor())
            .configure()

        initAppInjection()
        initConfig()
        initSDK()
    }

    /**
     * 初始化日志拦截器
     */
    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggerInterceptor())
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * 初始化项目中要用到的SDK
     */
    private fun initSDK() {
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)

        //todo 参考这个Logger库优化自己的
        Logger.init("LogHttpInfo")
            .hideThreadInfo()
            .methodOffset(2)

    }

    private fun initConfig() {
        //注册生命周期管理
        registerActivityLifecycleCallbacks(AppManager.instance.mCallback)

    }

    private fun initAppInjection() {

        /**
         * 构建AppComponent和AppModule联系
         */
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}
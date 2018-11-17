package zs.xmx.baselibrary.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import java.lang.ref.WeakReference
import java.util.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/6
 * @本类描述	  整个App的生命周期回调类
 * @内容说明   1.使用LinkList对项目中所有Activity进行管理
 *
 * @使用: 1.在Application类的registerActivityLifecycleCallbacks(AppManager.mCallbacks);传入mCallBack
 *        2.使用对应的ActivityUtils
 *
 *  todo 放到我们的 ProjectUtils里面
 *  todo 注册到生命周期有问题
 * fun init(app: Application?) {
    if (sApplication == null) {
        sApplication = app ?: getApplicationByReflect()
        sApplication!!.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE)
    }
}
 *
 */

class AppManager private constructor() {

    private var sTopActivityWeakRef: WeakReference<Activity>? = null
    private val sActivityList: LinkedList<Activity> = LinkedList()

    companion object {
        val instance: AppManager by lazy { AppManager() }
    }

    val mCallback = object : Application.ActivityLifecycleCallbacks {

        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityResumed(activity: Activity?) {
            setTopActivityWeakRef(activity!!)
        }

        override fun onActivityStarted(activity: Activity?) {
            setTopActivityWeakRef(activity!!)
        }

        override fun onActivityDestroyed(activity: Activity?) {
            sActivityList.remove(activity)
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            /**添加Activity到我们的LinkList 管理**/
            sActivityList.add(activity!!)
            setTopActivityWeakRef(activity)
        }
    }


    private fun setTopActivityWeakRef(activity: Activity) {
        if (sTopActivityWeakRef == null || activity != sTopActivityWeakRef!!.get()) {
            sTopActivityWeakRef = WeakReference(activity)
        }
    }

    /**
     * 结束所有activity
     */
    private fun finishAllActivities() {
        for (i in sActivityList.indices.reversed()) {
            sActivityList[i].finish()
            sActivityList.removeAt(i)
        }
    }

    /**
     * 退出应用程序
     *
     *
     * 需要加权限:
     *
     *
     * <uses-permission android:name="android.permission.KILL_BACKGROUND_PROCESSES"></uses-permission>
     */
    @SuppressLint("ObsoleteSdkInt")
    fun exitApp(context: Context) {
        try {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ECLAIR_MR1) {
                finishAllActivities()
                android.os.Process.killProcess(android.os.Process.myPid())    //获取PID
                System.exit(0)
            } else {
                finishAllActivities()
                val activityMgr = context
                        .getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
                activityMgr.killBackgroundProcesses(context.packageName)
                System.exit(0)
            }
            //退出应用,顺便做些gc优化
            System.gc()
            System.runFinalization()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}


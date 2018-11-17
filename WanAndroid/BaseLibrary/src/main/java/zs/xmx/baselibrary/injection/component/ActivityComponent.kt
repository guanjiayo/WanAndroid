package zs.xmx.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.trello.rxlifecycle3.LifecycleProvider
import dagger.Component
import zs.xmx.baselibrary.injection.module.ActivityModule
import zs.xmx.baselibrary.injection.module.LifecycleProviderModule
import zs.xmx.baselibrary.injection.scope.ActivityScope

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/8/25 17:07
 * @本类描述	  Activity Component基类
 * @内容说明  这里对应所有继承了BasesActivity的Component
 *
 * todo 如果不做成组件化,或者分包直接在这里inject(xxActivity)
 * todo 然后再创建一个Fragment inject(xxFragment)
 *
 *
 *
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class, LifecycleProviderModule::class))
interface ActivityComponent {
    /**
     * 由于ActivityComponent的子类不能找到以下方法,那么需要我们在这把方法暴露出去
     *<p>
     * 如UserComponent 对应我们的xxPresenter
     */
    fun activity(): Activity

    fun context(): Context

    fun lifecycleProvider(): LifecycleProvider<*>

}

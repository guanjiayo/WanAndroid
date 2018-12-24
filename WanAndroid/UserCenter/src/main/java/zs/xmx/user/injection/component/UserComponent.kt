package zs.xmx.user.injection.component

import dagger.Component
import zs.xmx.baselibrary.injection.component.ActivityComponent
import zs.xmx.baselibrary.injection.scope.ProvideComponentScope
import zs.xmx.user.injection.module.UserModule
import zs.xmx.user.ui.activity.*

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/28 22:47
 * @本类描述	  Component
 * @内容说明   将mPresenter和model注入到整个项目
 *
 */
@ProvideComponentScope
@Component(dependencies = [ActivityComponent::class],
        modules = [UserModule::class])
interface UserComponent {
    /**
     * 可以有多个Inject,需要Inject对象的Activity或Fragment都需要有对应的Dagger绑定
     *
     */
    fun inject(loginActivity: LoginActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(forgetPwdActivity: ForgetPwdActivity)
    fun inject(restPwdActivity: ResetPwdActivity)
    fun inject(userInfoActivity: UserInfoActivity)
    fun inject(settingActivity: SettingActivity)


}
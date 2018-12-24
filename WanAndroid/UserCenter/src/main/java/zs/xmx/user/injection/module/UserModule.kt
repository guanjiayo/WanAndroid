package zs.xmx.user.injection.module

import dagger.Module
import dagger.Provides
import zs.xmx.user.model.IUserModel
import zs.xmx.user.model.impl.UserModelImpl

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/28 22:51
 * @本类描述
 * @内容说明
 *
 */
@Module
class UserModule {

    /**
     * Presenter 层的
     * 相当于 var mMarketModel : IUserCenterModel = UserCenterModelImpl()
     */
    @Provides
    fun provideUserModel(mUserModel: UserModelImpl): IUserModel {
        return mUserModel
    }

}
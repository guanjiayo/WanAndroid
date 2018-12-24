package zs.xmx.user.utils

import zs.xmx.baselibrary.constant.BaseConstant
import zs.xmx.baselibrary.utils.SPUtils
import zs.xmx.user.model.domain.UserInfo

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/2 19:16
 * @本类描述	  SP存储本地用户信息
 * @内容说明   结合BaseLibrary 的 SPUtils使用
 *
 */
object UserPrefsUtils {


    /**
     * 退出登录时，传入null,清空存储
     */
    fun putUserInfo(userInfo: UserInfo?) {
        //todo 改造: 将UserInfo转Json字符串保存到SP
        SPUtils.putParam(BaseConstant.KEY_SP_TOKEN, userInfo?.token ?: "")
        SPUtils.putParam(BaseConstant.KEY_SP_USERNAME, userInfo?.username ?: "")
        SPUtils.putParam(BaseConstant.KEY_SP_PASSWORD, userInfo?.password ?: "")
        SPUtils.putParam(BaseConstant.KEY_SP_ICON, userInfo?.icon ?: "")
        SPUtils.putParam(BaseConstant.KEY_SP_COLLECTIDS, userInfo?.collectIds ?: "")
        SPUtils.putParam(BaseConstant.KEY_SP_CHAPTERTOPS, userInfo?.chapterTops ?: "")
        SPUtils.putParam(BaseConstant.KEY_SP_ID, userInfo?.id ?: "")
        SPUtils.putParam(BaseConstant.KEY_SP_TYPE, userInfo?.type ?: "")
        SPUtils.putParam(BaseConstant.KEY_SP_EMAIL, userInfo?.email ?: "")
    }


}